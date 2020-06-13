grammar Quiz;

program : block+ EOF;

block   : (function|main);

// MAIN
main    : 'main' '=>' content+ '>>';

// FUNCTIONS
function : 'function' '(' (params)* (type=('text'|'number'|'boolean'|'question') ID)? ')' name=ID '=>' (content)* ('return' (expr|bool) ';')? '>>';

// auxiliar to function to give all possible parameters
params : type=('text'|'number'|'boolean'|'question') ID ', ';

// LISTS
list    : (init='list' 'question')? ID '=>' 'get' '(' TEXT ')' ';'  # listQuestion
        | (init='list' 'number')? ID ('=>' listFormatNumber)? ';'   # listNums
        | (init='list' 'text')? ID ('=>' listFormatText)? ';'       # listText
        | (init='list' 'boolean')? ID ('=>' listFormatBool)? ';'    # listBoolean
        ;

// MAPS
map     : 'map' type='question' ID '=>' 'get' '(' TEXT ')' ';'                          # mapQuestion
        | 'map' type='number' ID ';'                            # mapNums
        | 'map' type='text' ID ';'                              # mapText
        | 'map' type='boolean' ID ';'                           # mapBoolean
        ;

// VARIABLES
var returns[String varx = null]: 
           'text'? ID ('=>' (((strings)* finalstring=(TEXT|ID) ';')|ID '[' index=(ID|NUMBER) ']' ';'|callfunction|singlestring=TEXT ';'|questionFetchTitle';'|questionFetchDiff';'|questionFetchType';'))?  # varText
         | 'text'? ID ('=>' 'read' '(' (TEXT|'CONSOLE') ')')? ';'                                                          # varTextRead
         | 'number'? ID ('=>' (callfunction|expr ';'|ID '[' index=(ID|NUMBER) ']' ';'|questionFetchTries';'|questionFetchTime';'|questionFetchPoints';'))? # varNumber
         | 'boolean'? ID ('=>' ((callfunction|bool ';')|ID '[' index=(ID|NUMBER) ']' ';'|))?                                                                      # varBoolean
         | 'question'? ID ';'                                                                                              # varQuestion
         | ID '=>' add                                                                                                     # varListAdd
         | ID '=>' remove                                                                                                  # varListRemove
         | ID '=>' 'split' '(' (TEXT|ID) ', ' TEXT ')' ';'                                                                  # varListSplit
         | ID '=>' 'get' '(' (TEXT|questionFetch|ID) ', ' ID ')' ';'                                                                  # varMapGet
         | ID '=>' 'put' '(' (TEXT|questionFetch|ID) ', ' (NUMBER|TEXT|ID|bool) ')' ';'                                     # varMapPut
         | ID '=>' 'remove' '(' (TEXT|questionFetch|ID) ')' ';'                                                               # varMapRemove
         | ID '=>' 'clear' '(' ')' ';'                                                                                     # varMapClear
         ;

strings  : (TEXT|ID) '+';

remove   : 'remove' '(' NUMBER ')' ';'                                                   #removeNumber
         | 'remove' '(' TEXT ')' ';'                                                     #removeText
         | 'remove' '(' ID ')' ';'                                                       #removeID       
         | 'remove' '(' '[' NUMBER ']' ')' ';'                                          #removeExpNumber
         ;
add      : 'add' '(' (listFormatNumber|listFormatBool|listFormatText) ')' ';'            #addList
         | 'add' '(' questionFetch ')' ';'                                               #addQuestion
         | 'add' '(' TEXT ')' ';'                                                        #addText
         | 'add' '(' ID ')' ';'                                                          #addID
         | 'add' '(' NUMBER ')' ';'                                                      #addNumber
         ;

// QUESTION OBJECT CREATION
question : questionFetchTitle '=>' TEXT ';'                    # questionTitle                         
         | questionFetchAnsRight '=>' listFormatText ';'       # questionAnsRight
         | questionFetchAnsWrong '=>' listFormatText ';'       # questionAnsWrong
         | questionFetchDiff '=>' ('easy'|'medium'|'hard') ';' # questionDifficulty
         | questionFetchType '=>' ('multiple'|'open') ';'      # questionType
         | questionFetchTries '=>' (ID|NUMBER) ';'             # questionTries
         | questionFetchTime '=>' (ID|NUMBER) ';'              # questionTime
         | questionFetchPoints '=>' (ID|NUMBER) ';'            # questionPoints
         ;

listFormatNumber : '{' (NUMBER ', ')* NUMBER '}';
listFormatText   : '{' (TEXT ', ')* TEXT '}';
listFormatBool   : '{' (bool ', ')* bool '}';

bool  : 'TRUE'|'FALSE';

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
write    : 'write' '(' 'CONSOLE' ')' '=>' ((strings)* finalstring=(TEXT|ID)|TEXT|expr|questionFetch) ';' # writeConsole
         | 'write' '(' TEXT ')' '=>' ((strings)* finalstring=(TEXT|ID)|TEXT|expr|questionFetch) ';'      # writeFile
         ;

// LOOPS
forLoop returns[String varx = null]:  
           'for' '(' type=('text'|'number'|'boolean'|'question') ID 'in' ID ')' '=>' content+ '>>'                # forIn
         | 'for' ID'(' start=(NUMBER|ID) 'to' end=(NUMBER|ID) ')' ('['op=('+'|'-') incr=NUMBER ']')? '=>' content+ '>>' # forTo
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
callfunction : 'call' ID '(' (callParams)* last=(TEXT|NUMBER|ID)? ')' ';';

callParams : val=(TEXT|NUMBER|ID) ', ';

// CONDITIONAL STATEMENTS
conditional : 'NOT'? id=ID
            | (field1=(ID|NUMBER) op=('AND'|'OR'|'=='|'!='|'>'|'<'))+ field2=(ID|NUMBER)
            | field3=(ID|TEXT) op=('=='|'!=') field4=(ID|TEXT)
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
expr returns[String varx = null, String type = null]:  
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
ID       : [a-zA-Z0-9]+;
Comment  : '!!'.*? '\n' -> skip;
WS       : [ \n\t\r]+ -> skip;