%{
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;
%}

%token IDENTIFICADOR, FLOAT, IF, THEN, ELSE, WHILE, DO, PRINT, ARRAY, CADENA, MAYOR_IGUAL, MENOR_IGUAL, DISTINTO, ASIG

%left '+' '-'
%left '*' '/'

%start programa

%%

programa: sentencias						
;

bloque: sentencia
  |  '{' sentencias '}'
;

sentencias: sentencia
  |  sentencias sentencia
;

indice: arreglo
  |  expresion
  |  FLOAT
;

argumento: IDENTIFICADOR 
  |  FLOAT
  |  IDENTIFICADOR '['indice']'
;

sentencia: declaracion
  |  seleccion
  |  bucle
  |  impresion
  |  asignacion
;

declaracion: FLOAT variables';'
  |  ARRAY arreglo ';'
;

arreglo: IDENTIFICADOR '[' argumento ']'
;

variables: IDENTIFICADOR
  |  variables ',' IDENTIFICADOR
;

comparador: '<'
  |  '>'
  |  '='
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL
  |  DISTINTO
;

condicion: argumento comparador argumento
;

seleccion: IF '(' condicion ')' THEN bloque
  |  IF '('condicion')'THEN { bloque } ELSE bloque
;


bucle: WHILE '('condicion')' DO bloque
;

impresion: PRINT'('CADENA')'';'
;

termino: termino '*' argumento
  |  termino '/' argumento
  |  argumento 
;

expresion: expresion '+' termino
  |  expresion '-' termino
;

asignacion: IDENTIFICADOR ASIG expresion ';'
  |  IDENTIFICADOR ASIG argumento
;

%%
    private AnalizadorLexico lexico;

    public Parser(AnalizadorLexico l) {
         lexico = l;

    }

    private int yylex() throws FileNotFoundException, IOException{
	int yyl_return = -1;
	try{
		yyl_return = lexico.yylex();
		yylval = lexico.getValorSimbolo();

	}
	catch (IOException e)
        {
		System.err.println("error de E/S:"+e);
	}
	return yyl_return;
    }

    private void yyerror(String stack_underflow_aborting) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }
