grammar Quiz;

program : block+ EOF;

block   : (function|main);

// MAIN
main    : 'main' '=>' content+ '>>';

// FUNCTIONS
function : 'function' '(' (('text'|'number'|'boolean'|'question') ID ',')* (('text'|'number'|'boolean'|'question') ID)? ')' name=ID '=>' (content)* ('return' expr ';')? '>>';

// LISTS
list    : 'list' 'question' ID '=>' 'get' '(' TEXT ')' ';'  # listQuestion
        | 'list' 'number' ID ('=>' ListFormatNumber)? ';'   # listNums
        | 'list' 'text' ID ('=>' ListFormatText)? ';'       # listText
        | 'list' 'boolean' ID ('=>' ListFormatBool)? ';'    # listBoolean
        ;

// MAPS
map     : 'map' 'question' ID '=>' 'get' '(' TEXT ')' ';'  # mapQuestion
        | 'map' 'number' ID ';'                            # mapNums
        | 'map' 'text' ID ';'                              # mapText
        | 'map' 'boolean' ID ';'                           # mapBoolean
        ;

// VARIABLES
var returns[String varx = null]: 
           'text'? ID ('=>' (TEXT|questionFetchTitle|questionFetchDiff|questionFetchType))? ';'                            # varText
         | 'text'? ID ('=>' 'read' '(' (TEXT|'CONSOLE') ')')? ';'                                                          # varTextRead
         | 'number'? ID ('=>' (expr|ID '[' (ID|NUMBER) ']'|questionFetchTries|questionFetchTime|questionFetchPoints))? ';' # varNumber
         | 'boolean'? ID ('=>' ('TRUE'|'FALSE'))? ';'                                                                      # varBoolean
         | 'question'? ID ';'                                                                                              # varQuestion
         | ID '=>' add                                                                                                     # varListAdd
         | ID '=>' REMOVE                                                                                                  # varListRemove
         | ID '=>' 'get' '(' (TEXT|questionFetch) ')' ';'                                                                  # varMapGet
         | ID '=>' 'put' '(' (TEXT|questionFetch) ',' (NUMBER|TEXT|ID|BOOLEAN) ')' ';'                                     # varMapPut
         | ID '=>' 'remove' '(' (TEXT|questionFetch) ')' ';'                                                               # varMapRemove
         | ID '=>' 'clear' '(' ')' ';'                                                                                     # varMapClear
         ;

REMOVE   : 'remove' '(' NUMBER ')' ';'          
         | 'remove' '(' TEXT ')' ';' 
         | 'remove' '(' ID ')' ';'
         | 'remove' '(' '[' NUMBER ']' ')' ';'
         ;
add      : 'add' '(' (ListFormatNumber|ListFormatBool|ListFormatText) ')' ';'
         | 'add' '(' questionFetch ')' ';'
         | 'add' '(' TEXT ')' ';'
         | 'add' '(' ID ')' ';'
         | 'add' '(' NUMBER ')' ';'
         ;

// QUESTION OBJECT CREATION
question : questionFetchTitle '=>' TEXT ';'                    # questionTitle                         
         | questionFetchAnsRight '=>' ListFormatText ';'       # questionAnsRight
         | questionFetchAnsWrong '=>' ListFormatText ';'       # questionAnsWrong
         | questionFetchDiff '=>' ('easy'|'medium'|'hard') ';' # questionDifficulty
         | questionFetchType '=>' ('multiple'|'open') ';'      # questionType
         | questionFetchTries '=>' (ID|NUMBER) ';'             # questionTries
         | questionFetchTime '=>' (ID|NUMBER) ';'              # questionTime
         | questionFetchPoints '=>' (ID|NUMBER) ';'            # questionPoints
         ;

ListFormatNumber : '{' (NUMBER ', ')* NUMBER '}';
ListFormatText   : '{' (TEXT ',')* TEXT '}';
ListFormatBool   : '{' (('TRUE'|'FALSE') ',')* ('TRUE'|'FALSE') '}';

// QUESTION OBJECT FETCHING
questionFetch    : questionFetchTitle|questionFetchAnsRight|questionFetchAnsWrong|questionFetchDiff|questionFetchType|questionFetchTries|questionFetchTime|questionFetchPoints;

questionFetchTitle    :  ID '.title';
questionFetchAnsRight :  ID '.answers.right';              
questionFetchAnsWrong :  ID '.answers.wrong';         
questionFetchDiff     :  ID '.difficulty';        
questionFetchType     :  ID '.type';                  
questionFetchTries    :  ID '.tries';                              
questionFetchTime     :  ID '.time';                             
questionFetchPoints   :  ID '.points';

// WRITE
write    : 'write' '(' 'CONSOLE' ')' '=>' (TEXT|expr|questionFetch) ';' # writeConsole
         | 'write' '(' TEXT ')' '=>' (TEXT|expr|questionFetch) ';'      # writeText
         ;

// LOOPS
forLoop  : 'for' '(' ('text'|'number'|'boolean'|'question'|'map') ID 'in' ID ')' '=>' content+ '>>'
         | 'for' '(' NUMBER 'to' NUMBER ')' ('['('+'|'-') NUMBER ']')? '=>' content+ '>>'
         ;

doaslong : 'do' '=>' content+ '>>' 'aslong' '(' conditional ')' ';';

aslong   : 'aslong' '(' conditional ')' '=>' content+ '>>';

// IF
ifCond   : 'if' '(' conditional ')' '=>' content+ '>>' finalCond?
         | 'if' '(' conditional ')' '=>' content+ '>>' elsif+ finalCond
         ;

elsif    : 'elsif' '(' conditional ')' '=>' content+ '>>';

finalCond : 'final' '=>' content+ '>>';

// CALL FUNCTION
callfunction : 'call' ID '(' ((TEXT|NUMBER|ID) ',')* (TEXT|NUMBER|ID)? ')' ';';

// CONDITIONAL STATEMENTS
conditional : 'NOT'? ID
            | (expr ('AND'|'OR'|'=='|'>'|'<'))+ expr
            | TEXT '==' TEXT
            ;

// Manipulation var
varmanipulation returns[String varx = null]: 
                  ID '++'               # varManipPlus
                | ID '--'               # varManipMinus
                | '++' ID               # varManipPrePlus
                | '--' ID               # varManipPreMinus
                | ID '+=' (NUMBER|ID)   # varManipPlusEquals
                | ID '-=' (NUMBER|ID)   # varManipMinusEquals        
                | ID '*=' (NUMBER|ID)   # varManipTimesEquals
                | ID '/=' (NUMBER|ID)   # varManipDivideEquals
                ;

// Aritmetics
expr returns[String varx = null]:  
            op=('-'|'+') expr           # ExprUnary
        |   expr op=('*'|'/'|'%') expr  # ExprMultDivMod
        |   expr op=('+'|'-') expr      # ExprAddSub
        |   NUMBER                      # ExprNumber
        |   '(' expr ')'                # ExprParens
        |   ID                          # ExprId
        ;

content  : map|list|var|write|question|forLoop|ifCond|doaslong|aslong|callfunction|(varmanipulation ';');

TEXT     : '"'.*?'"' ;
NUMBER   : [0-9]+;
BOOLEAN  : ('TRUE'|'FALSE');
ID       : [a-zA-Z0-9]+;
Comment  : '!!'.*? '\n' -> skip;
WS       : [ \n\t\r]+ -> skip;