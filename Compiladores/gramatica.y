%{
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;
%}

%token IDENTIFICADOR, FLOAT, IF, ELSE, WHILE, DO, PRINT, ARRAY, CADENA, MAYOR_IGUAL, MENOR_IGUAL, DISTINTO, ASIG

%left '+' '-'
%left '*' '/'

%start programa

%%

programa: sentencias						{System.out.println("Se analizo todo el codigo fuente.");}  
;

sentencias: sentencia
  |  sentencias sentencia
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

arreglo: identificador '[' argumento ']'
;

variables: identificador
  |  variables','identificador
;

seleccion: IF'('condicion')'THEN bloque
  |  IF'('condicion')'THEN bloque ELSE bloque
;

condicion: argumento comparador argumento
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

bucle: WHILE '('condicion')' DO bloque
;

impresion: PRINT'('CADENA')'';'
;

asignacion: IDENTIFICADOR ASIG expresion ';'
  |  IDENTIFICADOR ASIG argumento
;

expresion: expresion '+' termino
  |  expresion '-' termino
;

termino: termino '*' argumento
  |  termino / argumento
  |  argumento 
;

argumento: IDENTIFICADOR 
  |  FLOAT
  |  IDENTIFICADOR '['indice']'
;

indice: arreglo
  |  expresion
  |  FLOAT


conj_sent_decl: conj_sent_decl sent_decl
  |  sent_decl
;

sent_decl: tipo lista_var ';' 						{System.out.println("Linea " + AL.getNumLinea() + ": Declaracion de variables encontrada.");}
  |  tipo error								{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Sentencia declarativa invalida.");}
  |  tipo lista_var error						{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ';' para finalizar la sentencia de declaracion.");}
  |  ID '[' CTE_UINT DOS_PUNTOS CTE_UINT ']' ARRAY OF tipo ';' 		{System.out.println("Linea " + AL.getNumLinea() + ": Declaracion de arreglo encontrada.");}
  |  ID '[' CTE_UINT DOS_PUNTOS CTE_UINT ']' error 			{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Declaracion de arreglo invalida.");} ';'  
  |  ID '[' CTE_UINT DOS_PUNTOS CTE_UINT ']' ARRAY OF tipo error	{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ';' para finalizar la sentencia de declaracion.");}
  |  ID '[' CTE_UINT DOS_PUNTOS CTE_UINT error				{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ']' en la declaracion del arreglo.");}
  |  ID '[' CTE_UINT DOS_PUNTOS CTE_UINT ']' ARRAY OF error 		{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Tipo de arreglo no aceptado.");} ';'
  |  error								{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Sentencia declarativa invalida.");} ';'
;

lista_var: lista_var ',' ID
  |  ID
;

tipo: UINT  
  |  ULONG
;

bloque:   '{' conj_sent '}'
  | '{' '}'
  | '{' conj_sent	 					{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta '}' para cerrar el bloque principal.");}
;

conj_sent: conj_sent sentencia
  |  sentencia
;

sentencia: claus_selec 		
  |  claus_iter
  |  salida 		
  |  asignacion
  |  error								{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Sentencia invalida.");} ';'
;

claus_selec: IF '(' condicion ')' bloque_clausula ELSE bloque_clausula  {System.out.println("Linea " + AL.getNumLinea() + ": Final de sentencia 'IF' encontrada.");}
  |  IF '(' condicion ')' bloque_clausula error				{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta bloque 'ELSE'.");}	
  |  IF '(' condicion  							{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ')' para condicion del 'IF'.");}
  |  IF error  								{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta '(' para condicion del 'IF'.");}
  |  IF '(' condicion ')' bloque_clausula ELSE error			{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta especificar el bloque del 'ELSE'.");}
  |  IF '(' condicion ')' error						{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta especificar el bloque del 'IF'.");}
;

bloque_clausula:    '{' conj_sent '}'
  | '{' '}'
  | '{' conj_sent error	 					{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta '}' para cerrar el bloque.");}
  |  sentencia
;

condicion: expresion comparador expresion
  |  expresion error 							{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Condicion invalida.");}
;

expresion: expresion '+' expresion
  |  expresion '-' expresion 
  |  expresion '*' expresion
  |  expresion '/' expresion
  |  '(' expresion ')'
  |  factor
  |  '(' expresion error 						{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ')' para encerrar la expresion.");}
  |  error   								{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Expresion invalida.");}
;

factor: factor_comun
  |  CTE_ULONG		 
  |  elem_arreglo
;

factor_comun: ID
  |  CTE_UINT
  |  CTE_ERROR				
;

comparador: comp_simple
  |  '='
  |  MENOR_IGUAL
  |  MAYOR_IGUAL
;

comp_simple: '<'
  |  '>'
;

claus_iter: FOR '(' ID ASIG factor_comun ';' ID comp_simple factor_comun ';' factor_comun ')' bloque_clausula	{System.out.println("Linea " + AL.getNumLinea() + ": Final de sentencia 'FOR' encontrada.");}
  |  FOR '(' ID ASIG factor_comun ';' ID comp_simple factor_comun ';' factor_comun error 			{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ')' para cerrar el 'FOR'.");}
  |  FOR error  												{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta '(' para el 'FOR'.");}
  |  FOR '(' error												{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Sentencia 'FOR' invalida.");}
  |  FOR '(' ID ASIG factor_comun ';' ID comp_simple factor_comun ';' factor_comun ')' error			{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta especificar el bloque del 'FOR'.");}
;

salida: PRINT '(' STRING ')' ';'				{System.out.println("Linea " + AL.getNumLinea() + ": Sentencia de salida de mensajes por pantalla encontrada.");}
  |  PRINT error  						{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Sentencia de salida de mensajes por pantalla invalida.");}
  |  PRINT '(' STRING ')' 					{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ';' para finalizar la salida de mensajes por pantalla.");}  
;

asignacion: lista_lado_izq ASIG lista_expres ';' 		{System.out.println("Linea " + AL.getNumLinea() + ": Final de asignacion encontrada.");}	       
  |  lista_lado_izq ASIG lista_expres				{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ';' para finalizar la asignacion.");}  
;

lista_lado_izq: lista_lado_izq ',' ID
  |  lista_lado_izq ',' elem_arreglo
  |  ID
  |  elem_arreglo
  |  error 							{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": parte izquierda de asignacion invalida.");}  ASIG
;

elem_arreglo: ID '[' factor_comun ']'
  |  ID '[' factor_comun error					{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta ']' para indicar el indice del arreglo.");} 					
  |  ID '[' error 						{System.out.println("Error sintactico en linea " + AL.getNumLinea() + ": Falta especificar el indice del arreglo.");}
;

lista_expres: lista_expres ',' expresion
  |  expresion
;

%%

AnalizadorLexico AL;
Hashtable<String,Integer> valToken;         

int yylex(){
	
	String token="";

	try{
		token = AL.getToken();
		yylval = AL.getValor();
		
	}catch(IOException ex){
		return -1;
	}

	if (valToken(token)!=null)
		return valToken(token).get();
	return token.charAt(0);
}

void yyerror(String s){
}


public void run(String filepath, Hashtable<String,Vector> TSimb){

	valToken = new Hashtable<String,Integer>();
	valToken.put("id",ID);
	valToken.put("cte_uint",CTE_UINT);
	valToken.put("cte_ulong",CTE_ULONG);	
	valToken.put("cte_error",CTE_ERROR);
	valToken.put("if",IF);
	valToken.put("else",ELSE);
        valToken.put("for",FOR);
	valToken.put("print",PRINT);
	valToken.put("uint",UINT);
	valToken.put("ulong",ULONG);
	valToken.put("array",ARRAY);
	valToken.put("of",OF);
	valToken.put("string",STRING);
	valToken.put("..",DOS_PUNTOS);
	valToken.put(">=",MAYOR_IGUAL);
	valToken.put("<=",MENOR_IGUAL);
	valToken.put(":=",ASIG);
	valToken.put("eof",0);
	
	try{	
		AL = new AnalizadorLexico(filepath,TSimb);
		yyparse();
	}catch(IOException ex){}

}
            
	
	
	
