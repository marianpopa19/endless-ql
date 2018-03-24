grammar QL;

root            : FORM IDENTIFIER block EOF;
block           : '{' statement* '}';
statement       : condition | question;
condition       : IF '(' expression ')' conditionTrueBlock=block ELSE conditionFalseBlock=block
                | IF '(' expression ')' conditionTrueBlock=block;
question        : label=STRING IDENTIFIER ':' (type | type '=' expression);

// Expressions, prioritized from top to bottom
// label them for easier evaluation
// inspired by: https://stackoverflow.com/a/23092428
expression      : '(' expr=expression ')'                                   # parenExpr
                | MINUS expr=expression                                     # negExpr
                | NOT expr=expression                                       # notExpr
                | left=expression op=(MUL | DIV) right=expression           # opExpr
                | left=expression op=(PLUS | MINUS) right=expression        # opExpr
                | left=expression op=(LE | LT | GE | GT) right=expression   # boolExpr
                | left=expression op=(EQ | NE) right=expression             # compExpr
                | left=expression op=AND right=expression                   # andOrExpr
                | left=expression op=OR right=expression                    # andOrExpr
                | constant                                                  # constExpr
                ;

type            : BOOLEANTYPE
                | STRINGTYPE
                | INTEGERTYPE
                | DATETYPE
                | DECIMALTYPE
                | MONEYTYPE;

constant        : booleanConstant
                | integerConstant
                | decimalConstant
                | moneyConstant
                | stringConstant
                | identifierConstant;

booleanConstant     : (TRUE | FALSE);
integerConstant     : INTEGER;
decimalConstant     : DECIMAL;
moneyConstant       : MONEY;
stringConstant      : STRING;
identifierConstant  : IDENTIFIER;

// Operators
PLUS            : '+';
MINUS           : '-';
MUL             : '*';
DIV             : '/';
GT              : '>';
GE              : '>=';
LT              : '<';
LE              : '<=';
EQ              : '==';
NE              : '!=';
AND             : '&&';
OR              : '||';
NOT             : '!';
TRUE            : 'true';
FALSE           : 'false';

// Keywords
FORM            : 'form';
BOOLEANTYPE     : 'boolean';
STRINGTYPE      : 'string';
INTEGERTYPE     : 'integer';
DATETYPE        : 'date';
DECIMALTYPE     : 'decimal';
MONEYTYPE       : 'money';
IF              : 'if';
ELSE            : 'else';

// Literals
INTEGER         : [0-9]+;
MONEY           : ([0-9]+ '.' [0-9] [0-9]);
DECIMAL         : [0-9]+ '.' [0-9]+;
STRING          : '"' .*? '"';
IDENTIFIER      : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

WS              : [ \t\r\n]+ -> skip;
COMMENT         : ('/*' .*? '*/') -> skip;
LINE_COMMENT    : '//' ~[\r\n]* -> skip;