
grammar Questions;

prog    : question+ ;

question : ID ': {' Title dificulty answers '}'
         | ID ': {' Title dificulty Tries answers '}'
         | ID ': {' Title dificulty Tries Points answers '}'
         | ID ': {' Title dificulty Tries Points Time answers '}'
         | ID ': {' Title dificulty Tries Time answers '}'
         | ID ': {' Title dificulty Points Time answers '}'
         | ID ': {' Title dificulty Time answers '}'
         | ID ': {' Title dificulty Points answers '}'
         ;

dificulty   : 'dificulty: easy'           #DificultyEasy
            | 'dificulty: medium'         #DificultyMedium
            | 'dificulty: hard'           #DificultyHard
            ;

type        : 'type: open'                #Open
            | 'type: multiple'            #Multiple
            ;
            
answers : 'answers: {' right '}'
        | '{' answer'}'
        | '{' right wrong '}'
        | '{' wrong right'}'
        ;

answer: (ID ':' TEXT)+; 
right : 'right: {' (ID ':' TEXT)+ '}' 
      ;

wrong : 'wrong: {' (ID ':' TEXT)+ '}'
      ;



Title  : 'title: "' .*? '"';

Tries  : 'tries: '[0-9];

Points : 'points: '[0-9]+;

Time   : 'time: '[0-9]+;

TEXT : '"'.*?'"' ;

ID: [0-9]+;

Comment: '--'.*? '\n' -> skip;
WS: [ \n\t\r]+ -> skip;
