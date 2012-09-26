%{

package utils;
import java.io.IOException;

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
  |  bucle
  |  impresion
  |  asignacion';'
  |  seleccion 
  |  ';' error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sentencia no permitida");}
;

declaracion: FLOAT variables 
  |  ARRAY arreglo';'
;

variables: IDENTIFICADOR';' 
  |  variables','IDENTIFICADOR
  |  error {logSintactico.addLogger("ERROR en linea"+lexico.getLineas()+": declaracion de variables");} 
;

arreglo: IDENTIFICADOR '[' expresion ']' 
| error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en el arreglo");}
;

seleccion: IF'('condicion')'THEN '{'sentencias'}'   {logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion");}
|IF'('condicion')'THEN '{'sentencias'}' ELSE '{'sentencias'}'   {logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion ifelse");}
|error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en la seleccion");}
;

condicion: argumento comparador argumento 
| error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en la seleccion");}
;

bucle: WHILE '('condicion')' DO bloque {logSintactico.addLogger("Linea "+lexico.getLineas()+": Bucle");}
| WHILE '('condicion')' bloque {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba el DO");}
| WHILE '('';' bloque {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una condicion");}
;

impresion: PRINT'('CADENA')'';' 				{logSintactico.addLogger("Linea "+lexico.getLineas()+": Salida por pantalla");}
|PRINT'('CADENA')' error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba un punto y coma");}
|PRINT'('';' error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una cadena");}
|PRINT';' error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
;

asignacion: IDENTIFICADOR ASIG expresion  			{logSintactico.addLogger("Linea "+lexico.getLineas()+": Asignacion");}
  |  IDENTIFICADOR '[' expresion ']' ASIG expresion             {logSintactico.addLogger("Linea "+lexico.getLineas()+": Asignacion");}
;

comparador: '<' 
  |  '>'
  |  '='
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL
  |  error {logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en la comparacion");}
;

expresion: expresion '+' termino {logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  |  expresion '-' termino          {logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  | termino  
;

termino: termino '*' argumento  
  |  termino '/' argumento      
  |  argumento                  
  | error {logSintactico.addLogger("ERROR en linea"+lexico.getLineas()+": no es posible resolver la expresion");}
;

num: '-'NUMERO {putNegativo($2.sval);}
| NUMERO 
;

argumento: IDENTIFICADOR   
  |  num                
  |  IDENTIFICADOR '[' expresion ']'    
;

%%
  private Logger logSintactico = new Logger("sintactico.log");
  private AnalizadorLexico lexico;

  public Parser(AnalizadorLexico l) {
         lexico = l;

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