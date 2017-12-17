grammar YelpQuery;

//To add the package so eclipse and gradle don't go crazy
@header {
package ca.ece.ubc.cpen221.mp5.Query5;
}

/*
 * LEXAR TOKENS
 */

OR :  '||' ; 
AND : '&&' ; 
GREATERTHAN :  '>' ; 
GREATERTHANEQUAL :'>=' ;
LESSTHAN : '<' ;
LESSTHANEQUAL : '<=' ;
EQUAL : '=' ;
NUMBER : [1-5] ;
LParen :'(';
RParen : ')' ;
STRING : [a-z]+ |[A-Z]+ | [a-z]+[A-Z]+ | [A-Z]+[a-z]+ ;
WS : [ \r\t\n]+ -> skip ; 

/*
 * PARSER RULES
 */
orExpr :  andExpr (OR andExpr)* ;
andExpr : atom (AND atom)*;
atom : in|category|rating|price|name|LParen orExpr RParen;
ineq : GREATERTHAN|GREATERTHANEQUAL|LESSTHAN|LESSTHANEQUAL|EQUAL;
in : 'in' LParen STRING+ RParen;
category : 'category' LParen STRING+ RParen;
name : 'name' LParen STRING+ RParen;
rating : 'rating' ineq NUMBER;
price : 'price' ineq NUMBER; 

  


