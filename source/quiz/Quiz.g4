grammar Quiz;

program : block+ EOF;

block   : function* main function*;

// MAIN
main    : 'main' '=>' content+ '>>';

// FUNCTIONS
function : 'function' '(' (('text'|'number'|'boolean'|'question') ID ',')* (('text'|'number'|'boolean'|'question') ID)? ')' ID '=>' (content)* 'return' ID ';' '>>';

// LISTS
list    : 'list' 'question' ID '=>' 'get' '(' TEXT ')' ';'  # listQuestion
        | 'list' 'number' ID ('=>' ListFormatNumber)? ';'   # listNums
        | 'list' 'text' ID ('=>' ListFormatText)? ';'       # listText
        | 'list' 'boolean' ID ('=>' ListFormatBool)? ';'    # listBoolean
        ;

// VARIABLES
var      : 'text'? ID ('=>' (TEXT|questionFetchTitle|questionFetchDiff|questionFetchType))? ';'                         # varText
         | 'text'? ID ('=>' 'read' '(' (TEXT|'CONSOLE') ')')? ';'                                                       # varTextRead
         | 'number'? ID ('=>' (NUMBER|ID '[' NUMBER ']'|questionFetchTries|questionFetchTime|questionFetchPoints))? ';' # varNumber
         | 'boolean'? ID ('=>' ('TRUE'|'FALSE'))? ';'                                                                   # varBoolean
         | 'question'? ID ';'                                                                                           # varQuestion
         | ID '=>' add                                                                                                  # varListAdd
         | ID '=>' REMOVE                                                                                               # varListRemove
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
question : questionFetchTitle '=>' TEXT ';'                   # questionTitle                         
         | questionFetchAnsRight '=>' ListFormatText ';'      # questionAnsRight
         | questionFetchAnsWrong '=>' ListFormatText ';'      # questionAnsWrong
         | questionFetchDiff '=>' ('easy'|'medium'|'hard') ';'# questionDifficulty
         | questionFetchType '=>' ('multiple'|'open') ';'     # questionType
         | questionFetchTries '=>' NUMBER ';'                 # questionTries
         | questionFetchTime '=>' NUMBER ';'                  # questionTime
         | questionFetchPoints '=>' NUMBER ';'                # questionPoints
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
write    : 'write' '(' (TEXT|'CONSOLE') ')' '=>' (ID|TEXT|NUMBER|questionFetch) ';';

// LOOPS
forLoop  : 'for' '(' ('text'|'number'|'boolean'|'question') ID 'in' ID ')' '=>' content+ '>>'
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
callfunction : 'call' ID '(' ((TEXT|NUMBER|ID) ',')* (TEXT|NUMBER|ID) ')' ';';

// CONDITIONAL STATEMENTS
conditional : 'NOT'? ID
            | ((ID|NUMBER) ('AND'|'OR'|'=='|'>'|'<'))+ (ID|NUMBER)
            | TEXT '==' TEXT
            ;

// Manipulation var
varmanipulation : ID '++'               # varManipPlus
                | ID '--'               # varManipMinus
                | '++' ID               # varManipPrePlus
                | '--' ID               # varManipPreMinus
                | ID '+=' NUMBER        # varManipPlusEquals
                | ID '-=' NUMBER        # varManipMinusEquals        
                | ID '*=' NUMBER        # varManipTimesEquals
                | ID '/=' NUMBER        # varManipDivideEquals
                ;

content  : list|var|write|question|forLoop|ifCond|doaslong|aslong|callfunction|(varmanipulation ';');

TEXT     : '"'.*?'"' ;
NUMBER   : [0-9]+;
ID       : [a-zA-Z0-9]+;
Comment  : '!!'.*? '\n' -> skip;
WS       : [ \n\t\r]+ -> skip;