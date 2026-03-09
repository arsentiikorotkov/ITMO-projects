grammar Formatting;

valExpr : valTerm
        (PLUS { System.out.print(" + "); } valTerm
        | MINUS { System.out.print(" - "); } valTerm )*;

valTerm : valFactor
      (STAR { System.out.print(" * "); } valFactor
      | SLASH { System.out.print(" / "); } valFactor)*;

valFactor : (MINUS { System.out.print("-"); } | )
       OPEN_ROUND { System.out.print("("); } valExpr CLOSE_ROUND { System.out.print(")"); }
       | num=NUM { System.out.print($num.text); }
       | name=NAME { System.out.print($name.text); };


logExpr : logFactor (op=EQ_OP { System.out.print(" " + $op.text + " "); } logFactor)*;

logFactor : (INV { System.out.print("!"); } | )
       OPEN_ROUND { System.out.print("("); } logExpr CLOSE_ROUND { System.out.print(")"); }
       | T { System.out.print("true"); }
       | F { System.out.print("false"); }
       | name=NAME { System.out.print($name.text); } (logTerm | );

logTerm : op=COMP_OP name=NAME { System.out.print(" " + $op.text + " " + $name.text); };


charName : name=NAME { System.out.print($name.text); } | ch=SYMBOL { System.out.print($ch.text); };

expr : valExpr | logExpr | charName;


assignment : name=NAME { System.out.print($name.text); }
                                     (EQ { System.out.print(" = "); } expr
                                     | INC { System.out.print("++"); }
                                     | DEC { System.out.print("--"); } );

initialization :  INT { System.out.print("int "); }
                               name=NAME EQ { System.out.print($name.text + " = "); } valExpr
                               |
                               BOOL { System.out.print("boolean "); }
                               name=NAME EQ { System.out.print($name.text + " = "); } logExpr
                               |
                               CHAR { System.out.print("char "); }
                               name=NAME EQ { System.out.print($name.text + " = "); } charName;


eq[String tabs] : { System.out.print($tabs); } (assignment | initialization);


fIf[String tabs] : IF OPEN_ROUND { System.out.print($tabs + "if ("); }
                  logExpr
                  CLOSE_ROUND OPEN_FIGURED { System.out.println(") {"); }
                  constructions[tabs + "\t"]
                  CLOSE_FIGURED { System.out.println($tabs + "}"); };

fFor[String tabs] : FOR OPEN_ROUND { System.out.print($tabs + "for("); }
                   (eq[""] | ) SC { System.out.print(";"); }
                   ({ System.out.print(" "); } logExpr | ) SC { System.out.print(";"); }
                   ({ System.out.print(" "); } eq[""] | )
                   CLOSE_ROUND OPEN_FIGURED { System.out.println(") {"); }
                   constructions[tabs + "\t"]
                   CLOSE_FIGURED { System.out.println($tabs + "}"); };

fWhile[String tabs] : WHILE OPEN_ROUND { System.out.print($tabs + "while("); }
                     logExpr
                     CLOSE_ROUND OPEN_FIGURED { System.out.println(") {"); }
                     constructions[tabs + "\t"]
                     CLOSE_FIGURED { System.out.println($tabs + "}"); };


constructions[String tabs] : (eq[tabs] SC { System.out.println(";"); }
                              | fIf[tabs]
                              | fFor[tabs]
                              | fWhile[tabs]
                              | fReturn[tabs])+ | { System.out.println(); };


type : INT { System.out.print("int "); }
       | CHAR { System.out.print("char "); }
       | BOOL { System.out.print("boolean "); };

arg : type name=NAME { System.out.print($name.text); };

fReturn[String tabs] : RETURN { System.out.print($tabs + "return "); }
                       expr
                       SC { System.out.println(";"); };

function[String tabs] : { System.out.print($tabs); }
                        type name=NAME OPEN_ROUND { System.out.print($name.text + "("); }
                        arg? (CO { System.out.print(", "); } arg)*
                        CLOSE_ROUND OPEN_FIGURED { System.out.println(") {"); }
                        constructions[$tabs + "\t"]
                        CLOSE_FIGURED { System.out.println($tabs + "}"); };

fClass[String tabs] : CLASS name=NAME OPEN_FIGURED
                      { System.out.println($tabs + "class " + $name.text + " {"); }
                      ((function[tabs + "\t"]
                                | fClass[tabs + "\t"]
                                | { System.out.print($tabs + "\t"); } initialization SC { System.out.println(";"); })+
                       | { System.out.println(); })
                      CLOSE_FIGURED { System.out.println($tabs + "}"); };

start : { System.out.println("Formatted input: "); } (constructions[""] | (function[""] | fClass[""])+);


IF : 'if';
FOR : 'for';
WHILE : 'while';
RETURN : 'return';
CLASS : 'class';

INT : 'int';
CHAR : 'char';
BOOL : 'boolean';

T : 'true';
F : 'false';

INC : '++';
DEC : '--';

PLUS : '+';
MINUS : '-';
STAR : '*';
SLASH : '/';

EQ_OP : ('==' | '!=');
COMP_OP : ('>' | '>=' | '<' | '<=');
INV : '!';

OPEN_ROUND : '(';
CLOSE_ROUND : ')';

OPEN_FIGURED : '{';
CLOSE_FIGURED : '}';

EQ : '=';
SC : ';';
CO : ',';

NUM : [0] | [-]? [1-9] [0-9]*;

NAME : ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])*;

SYMBOL : '\'' . '\'';

WS : (' ' | '\t' | '\r' | '\n') -> skip;


