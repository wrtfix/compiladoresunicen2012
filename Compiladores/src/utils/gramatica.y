%{

package utils;
import java.io.IOException;
import java.util.Vector;
%}

%token IDENTIFICADOR, FLOAT, IF,THEN, ELSE, WHILE, DO, PRINT, ARRAY, CADENA, MAYOR_IGUAL, MENOR_IGUAL, DISTINTO, ASIG, NUMERO

%start programa

%%

programa: sentencias {

logSintactico.addLogger("El programa finalizo correctamente");
}
;

bloque: sentencia
  |  '{'sentencias'}'
;

sentencias: sentencia
  |  sentencias sentencia
;

sentencia: declaracion
  |   {logSintactico.addLogger("Linea "+lexico.getLineas()+": Bucle");} bucle
  |  impresion
  |   {logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion");} seleccion
  |  asignacion';' 
  |  ';' error {logSintactico.addLogger("ERROR sintactico en linea "+lexico.getLineas()+": sentencia no permitida");}
;

declaracion: FLOAT variables';' {logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un FLOAT");}
  |  ARRAY arreglo';' {logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un ARRAY");}
| ARRAY arreglo error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
| FLOAT';' error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
;

variables: IDENTIFICADOR 
  |  variables','IDENTIFICADOR
  |  error {logSintactico.addLogger("ERROR sintactica en la linea"+lexico.getLineas()+": declaracion de variables");} 
;

arreglo: IDENTIFICADOR '[' expresion ']' 
| error {logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": sintactico en el arreglo");}
;

seleccion: IF'('condicion')'THEN'{'sentencias'}'   
|IF'('condicion')'THEN'{'sentencias'}' ELSE '{'sentencias'}'   {logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");}
|IF'('condicion')'THEN'{'sentencias'}' ELSE  {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion faltan las LLAVES");}
|IF'('condicion')''{'sentencias'}' ELSE  {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion falta setencia THEN ");}
|IF'('condicion')'THEN {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion faltan las LLAVES");}
|error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion no valida");}
;

condicion: argumento comparador argumento 
| error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": condicion no valida");}
;

bucle: WHILE '('condicion')' DO bloque 
| WHILE '('condicion')' bloque {logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba el DO");}
| WHILE '('';' bloque {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
;

impresion: PRINT'('CADENA')'';' 				{logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
|PRINT'('CADENA')' error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
|PRINT'('';' error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
|PRINT';' error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
;

asignacion: iden ASIG expresion  			{pi.add($2.sval); $$=$1;logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
  |  IDENTIFICADOR '[' expresion ']' ASIG expresion             {logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
;

iden: IDENTIFICADOR {pi.add($1.sval); $$=$1;}


comparador: '<' 
  |  '>'
  |  '='
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL
  |  error {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": no es posible resolver la comparacion");}
;

expresion: expresion '+' termino { pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  |  expresion '-' termino          {pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  | termino  
;

termino: termino '*' argumento  {pi.add($2.sval); $$=$1;}
  |  termino '/' argumento      {pi.add($2.sval); $$=$1;}
  |  argumento  {pi.add($1.sval);}
  | error {logSintactico.addLogger("ERROR sintactico en la linea"+lexico.getLineas()+": no es posible resolver la expresion");} {System.out.println($2.sval);}
;

num: '-'NUMERO {putNegativo($2.sval);}
| NUMERO 
;

argumento: IDENTIFICADOR {$$=$1;}
  |  num                {$$=$1;}
  |  IDENTIFICADOR '[' expresion ']'   {$$=$1;}
;

%%
  private Logger logSintactico = new Logger("sintactico.log");
  private AnalizadorLexico lexico;
  private Vector<String> pi;

  public Parser(AnalizadorLexico l) {
         lexico = l;
         pi = new Vector<String>();

    }

    private int yylex(){
        int yyl_return = -1;
        while (lexico.masTokens()) {
            try {
                yyl_return = lexico.yylex();
                yylval = lexico.getValorSimbolo();

            } catch (IOException e) {
                System.err.println("error de E/S:" + e);
            }
            return yyl_return;
        }
        return 0;
    }

    private void yyerror(String stack_underflow_aborting) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }
public void putNegativo(String valor){
         Simbolo s = new Simbolo(new StringBuffer("-"+valor),"NUMERO");
        lexico.getTabla().addSimbolo(s);
        
        Simbolo raya = new Simbolo(new StringBuffer("-"),"-");
        lexico.getTabla().eliminarSimbolo(raya);
        
        Simbolo elival = new Simbolo(new StringBuffer(valor),"NUMERO");
        lexico.getTabla().eliminarSimbolo(elival);
}
public void imprimirSintactico(){
    logSintactico.imprimir();
}

public void imprimirPila(){
    for(int i=0; i<pi.size();i++)
        System.out.println(pi.elementAt(i));
}