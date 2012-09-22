%{

package utils;
import java.io.IOException;

%}

%token IDENTIFICADOR, FLOAT, IF,THEN, ELSE, WHILE, DO, PRINT, ARRAY, CADENA, MAYOR_IGUAL, MENOR_IGUAL, DISTINTO, ASIG, NUMERO

%start programa

%%

programa: bloque
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
;

declaracion: FLOAT variables 
  |  ARRAY arreglo';' 
;

variables: IDENTIFICADOR';' 
  |  variables','IDENTIFICADOR
;

arreglo: IDENTIFICADOR '[' expresion ']' 
;

seleccion: IF'('condicion')'THEN bloque {System.out.println("Seleccion en linea "+lexico.getLineas());}
|IF '(' condicion ')' bloque_then ELSE bloque_else   
;
bloque_then: bloque
;

bloque_else: bloque
;
condicion: argumento comparador argumento {System.out.println("Esto es un comparador");}
;

comparador: '<' 
  |  '>'
  |  '='
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL
;

bucle: WHILE '('condicion')' DO bloque {System.out.println("Bucle en linea "+lexico.getLineas());}
;

impresion: PRINT'('CADENA')'';' 				{System.out.println("Salida por pantalla en linea "+lexico.getLineas());}
|PRINT'('CADENA')'argumento {System.out.println("ERROR en salida por pantalla un ; en linea "+ lexico.getLineas());}
|PRINT'('CADENA';'     {System.out.println("ERROR en salida por pantalla en linea "+lexico.getLineas()+" se esperaba un )");}
|PRINT';' {System.out.println("ERROR en salida por pantalla en linea "+lexico.getLineas() + " se esperaba una CADENA");}
;

asignacion: IDENTIFICADOR ASIG expresion  			{System.out.println("Asignacion en linea "+lexico.getLineas());}
  |  IDENTIFICADOR '[' expresion ']' ASIG expresion             {System.out.println("Asignacion");}
;

expresion: expresion '+' termino {System.out.println("Expresion en linea "+lexico.getLineas());}
  |  expresion '-' termino          {System.out.println("Expresion en linea "+lexico.getLineas());}
  | termino  
;

termino: termino '*' argumento  
  |  termino '/' argumento      
  |  argumento                  
;

num: '-'NUMERO {putNegativo($2.sval);}
| NUMERO 
;

argumento: IDENTIFICADOR   
  |  num                
  |  IDENTIFICADOR '[' expresion ']'    
;



%%
  
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