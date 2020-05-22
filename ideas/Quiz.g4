
grammar Quiz;

list    : LIST 'question' TEXT '=>'  #listQuestion
        | LIST 'number' TEXT '=>'    #listNums
        | LIST 'text' TEXT '=>'      #listText
        ;
REMOVE   : 'remove('NUMBER')';
ADD      : 'add('TEXT'
TEXT     : '"'.*?'"' ;
NUMBER   : [0-9]+;
LIST     : 'list';
QUESTION : 'question';


TRUE     : 'true';
FALSE    : 'false';
BOOLEAN  : 'boolean';

AND      : '&';
OR       : '|';

EQUALS   : '=';
NOT      : 'NOT';

IF       : 'if';
ELSE     : 'else';
FINAL    : 'final';
FOR      : 'for';
ASLONG   : 'aslong';

GET      : 'get';
WRITE    : 'write';
PRINT    : 'print';

Comment: '!!'.*? '\n' -> skip;
WS: [ \n\t\r]+ -> skip;

