
grammar Questions;

prog    : question+ ;

question : ID '{' Title ',' Type ',' Difficulty ',' (Tries ',')? (Time ',')? (Points ',')? RightAns ',' WrongAns ','? '}';

Difficulty  : 'difficulty: EASY'         
            | 'difficulty: MEDIUM'         
            | 'difficulty: HARD'           
            ;

Type        : 'type: OPEN'               
            | 'type: MULTIPLE'            
            ;

RightAns : 'right: [' (TEXT ',')* TEXT ']' ;

WrongAns : 'wrong: [' ((TEXT ',')* TEXT)? ']';
      

Title  : 'title: "' .*? '"';

Tries  : 'tries: '[0-9];

Points : 'points: '[0-9]+;

Time   : 'time: '[0-9]+;

TEXT : '"'.*?'"' ;

ID: '<'.*?'>';

Comment: '--'.*? '\n' -> skip;
WS: [ \n\t\r]+ -> skip;
