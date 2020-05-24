grammar Quiz;

program : block*;

block   : main;

// MAIN
main    : MAIN '=>' content '>>';

// FUNCTIONS
function : FUNCTION '(' (TYPE ID ',')* (TYPE ID)? ')' ID '=>' (content)+ RETURN ID '>>';

// LISTS
list    : LIST 'question' ID '=>' GET '(' TEXT ');'       # listQuestion
        | LIST 'number' ID InitList                       # listNums
        | LIST 'text' ID InitList                         # listText
        | LIST 'boolean' ID InitList                      # listBoolean
        ;

InitList : ';'                  
         | '=>' ListFormatNumber ';'
         | '=>' ListFormatText ';'
         | '=>' ListFormatBool ';'
         ; 

REMOVE   : 'remove('NUMBER');'          
         | 'remove('TEXT');' 
         | 'remove('ID');'
         | 'remove(['NUMBER']);'
         ;
add      : 'add('(ListFormatNumber|ListFormatBool|ListFormatText)');'
         | 'add('TEXT');'
         | 'add('ID');'
         | 'add('NUMBER');'
         ;

// VARIABLES
var      : ID '=>' add                                   # varListAdd
         | 'text'? ID ('=>' TEXT)? ';'                   # varText
         | 'text'? ID ('=>' READ)? ';'                   # varTextRead
         | 'number'? ID ('=>' NUMBER)? ';'               # varNumber
         | 'boolean'? ID ('=>' (TRUE|FALSE))? ';'        # varBoolean
         | 'question'? ID ';'                            # varQuestion
         ;

// QUESTION OBJECT CREATION
question : ID '.title =>' TEXT ';'                              # questionTitle                         
         | ID '.answers.right =>' ListFormatText ';'            # questionAnsRight
         | ID '.answers.wrong =>' ListFormatText ';'            # questionAnsWrong
         | ID '.difficulty =>' ('easy'|'medium'|'hard') ';'     # questionDifficulty
         | ID '.type =>' ('multiple'|'open') ';'                # questionType
         | ID '.tries =>' NUMBER ';'                            # questionTries
         | ID '.time =>' NUMBER ';'                             # questionTime
         | ID '.points =>' NUMBER ';'                           # questionPoints
         ;

ListFormatNumber : '{' (NUMBER ',')* NUMBER '}';
ListFormatText   : '{' (TEXT ',')* TEXT '}';
ListFormatBool   : '{' ((TRUE|FALSE) ',')* TRUE|FALSE '}';

// QUESTION OBJECT FETCHING
questionFetchTitle    :  ID '.title';
questionFetchAnsRight :  ID '.answers.right';              
questionFetchAnsWrong :  ID '.answers.wrong';            
questionFetchDiff     :  ID '.difficulty';        
questionFetchType     :  ID '.type';                  
questionFetchTries    :  ID '.tries';                              
questionFetchTime     :  ID '.time';                             
questionFetchPoints   :  ID '.points';

// WRITE
write    : WRITE;

// LOOPS
forLoop  : FOR '(' TYPE ID IN ID ') =>' (content)+ '>>'
         | FOR '(' NUMBER TO NUMBER ')' ('['('+'|'-') NUMBER ']')? '=>' (content)+ '>>'
         ;

doaslong : DO '=>' (content)+ '>>' ASLONG '(' conditional ');';

aslong   : ASLONG '(' conditional ') =>' content '>>';

// IF
ifCond   : IF '(' conditional ') =>' content '>>' finalCond?
         | IF '(' conditional ') =>' content '>>' elsif+ finalCond
         ;

elsif    : ELSIF '(' conditional ') =>' content '>>';

finalCond : FINAL '=>' content '>>';

// CALL FUNCTION
callfunction : CALL ID '(' (FORMAT ',')* FORMAT ');';

// CONDITIONAL STATEMENTS
conditional : NOT? ID
            | ((ID|NUMBER) (AND|OR|EQUALS|'>'|'<'))+ (ID|NUMBER)
            | TEXT EQUALS TEXT
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

content  : list|var|write|question|forLoop|ifCond|doaslong|aslong|callfunction|varmanipulation;

LIST     : 'list';
QUESTION : 'question';

TYPE     : 'text' | 'number' | BOOLEAN | QUESTION ;  

TRUE     : 'true';
FALSE    : 'false';
BOOLEAN  : 'boolean';

AND      : 'AND';
OR       : 'OR';

EQUALS   : '==';
NOT      : 'NOT';

IF       : 'if';
ELSIF    : 'elsif';
FINAL    : 'final';
FOR      : 'for';
DO       : 'do';
ASLONG   : 'aslong';
FUNCTION : 'function';

IN       : 'in';
TO       : 'to';
RETURN   : 'return';
CALL     : 'call';

GET      : 'get';
WRITE    : 'write('(TEXT|CONSOLE)')';
READ     : 'read('(TEXT|CONSOLE)')';

CONSOLE  : 'CONSOLE';

MAIN     : 'main';

ID       : [a-zA-Z0-9]+;
TEXT     : '"'.*?'"' ;
NUMBER   : [0-9]+;

FORMAT   : TEXT|NUMBER|BOOLEAN|ID;

Comment  : '!!'.*? '\n' -> skip;
WS       : [ \n\t\r]+ -> skip;

