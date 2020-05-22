
grammar Questions;

prog    : question+ 
        | (easy|medium|hard)+
        ;

question : ID ': {' Title answers 
         | ID ': {' Title Tries answers 
         | ID ': {' Title Tries Points answers
         | ID ': {' Title Tries Points Time answers
         | ID ': {' Title Tries Time answers
         | ID ': {' Title Points Time answers
         | ID ': {' Title Time answers
         | ID ': {' Title Points answers
         ;
answers : 'answers: {' right '}'
        | '{' right wrong '}'
        | '{' wrong right'}'
        ;

right : 'right: {' ID ':' TEXT '}' 
      ;

wrong : 'wrong: {'ID ':' TEXT '}'
      ;

easy      : 'easy [' question+ ']';
medium    : 'medium [' question+ ']';
hard      : 'hard [' question+ ']';

Title  : 'title: "' .*? '"';

Tries  : 'tries: '[0-9];

Points : 'points: '[0-9]+;

Time   : 'time: '[0-9]+;

TEXT : '"'.*?'"' ;

ID: [0-9]+;

Comment: '--'.*? '\n' -> skip;
WS: [ \n\t\r]+ -> skip;
