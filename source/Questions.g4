
grammar Questions;

prog    : standart question+ ;

standart : Time Tries Points ;

question : ID '{' Title ',' Type ',' Difficulty ',' (Tries ',')? (Time ',')? (Points ',')? RightAns ',' WrongAns ','? '}'
         ;

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

TEXT : '"' ('\\' ["\\] | ~["\\\r\n])* '"'  ;

ID: '<'[a-zA-Z0-9]+'>';


Comment: '--'.*? '\n' -> skip;
WS: [ \n\t\r]+ -> skip;
