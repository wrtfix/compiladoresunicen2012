%{
package utils;
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;

%}

%token IDENTIFICADOR, FLOAT, IF,THEN, ELSE, WHILE, DO, PRINT, ARRAY, CADENA, MAYOR_IGUAL, MENOR_IGUAL, DISTINTO, ASIG,NUMERO

%left '+' '-'
%left '*' '/'

%start programa

%%

programa: sentencias	{System.out.println("Terminacion del programa");}
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

declaracion: FLOAT variables {System.out.println("Variable tipo float");}
  |  ARRAY arreglo ';' {System.out.println("Variable tipo arreglo");}
| CADENA variables  {System.out.println("Variable tipo cadena");}
  
;

variables: IDENTIFICADOR ';' {System.out.println("Esto es una variable ");}
  |  variables ',' IDENTIFICADOR
;

arreglo: IDENTIFICADOR '[' expresion ']' 
;

seleccion: IF'('condicion')'THEN bloque {System.out.println("Sentecia IF");}
  |  IF'('condicion')'THEN bloque ELSE bloque  {System.out.println("Sentecia IF ELSE");}
;

condicion: argumento comparador argumento {System.out.println("Esto es un comparador");}
;

comparador: '<' 
  |  '>'
  |  '='
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL
;

bloque: sentencia
  |  '{'sentencias'}'
;

bucle: WHILE '('condicion')' DO bloque {System.out.println("Bucle while");}
;

impresion: PRINT'('CADENA')'';' 					{System.out.println("Salida por pantalla");}
;

asignacion: IDENTIFICADOR ASIG expresion  			{System.out.println("Asignacion");}
  |  IDENTIFICADOR '[' expresion ']' ASIG expresion             {System.out.println("Asignacion");}
;

expresion: expresion '+' termino {System.out.println("Esto es una expresion");}
  |  expresion '-' termino          {System.out.println("Esto es una expresion");}
  | termino  
;

termino: termino '*' argumento  {System.out.println("Esto es un termino");}
  |  termino '/' argumento      {System.out.println("Esto es un termino");}
  |  argumento                  {System.out.println("Esto es un argumento");}
;

argumento: IDENTIFICADOR   
  |  NUMERO                
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
