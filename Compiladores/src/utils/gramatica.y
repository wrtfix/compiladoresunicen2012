%{

package utils;
import java.io.IOException;
import java.util.Vector;
import java.util.Stack;
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
  |  ';' error {logSintactico.addLogger("Error sintactico en linea "+lexico.getLineas()+": sentencia no permitida");}
;

declaracion: FLOAT variables';' {logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un FLOAT");}
  | ARRAY FLOAT arreglo';' { logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un ARRAY");}
  | ARRAY arreglo error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
  | FLOAT';' error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
;

variables: IDENTIFICADOR {lexico.getTabla().addTipo($1.sval,"FLOAT");}
  |  variables','IDENTIFICADOR
  |  error {logSintactico.addLogger("Error sintactica en la linea"+lexico.getLineas()+": declaracion de variables");} 
;

arreglo: IDENTIFICADOR '[' expresion ']' {pi.add($1.sval); $$=$1;  lexico.getTabla().addTipo($1.sval,"ARRAY FLOAT");}
  | error {logSintactico.addLogger("Error sintactica en la linea "+lexico.getLineas()+": sintactico en el arreglo");}
;


seleccion: IF '('condicion')'  THEN bloque
{ 
            Integer pos = (Integer) pila.pop(); // saca el tope de la pila
            pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
            pi.remove(pos.intValue()+1);          
} 
| IF '('condicion')' THEN bloqueThen 
{ 
            pi.add(" "); //agrega un blanco en  la polaca
            pi.add("bi"); //agrega un bi en la polaca 
            Integer pos = (Integer) pila.pop(); // saca el tope de la pila
            pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
            pi.remove(pos.intValue()+1);          
            pila.push(pi.size()- 2);//apila el lugar en blanco en la pila
}  
ELSE bloqueElse 
{ 
        Integer pos = (Integer) pila.pop(); // saca el tope de la pila
        pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
        pi.remove(pos.intValue()+1);            
        logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");
}
|error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": seleccion no valida");}
;

bloqueThen: '{'sentencias'}' 
;
bloqueElse: '{'sentencias'}'
;	


condicion: argumento comparador argumento  {pi.add($2.sval); pi.add(" "); pi.add("bf"); pila.push(pi.size()- 2 ); }
  | error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida");}
;


comparador: '<'
  |  '>' 
  |  '=' 
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL 
  |  DISTINTO
  |  error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": no es posible resolver la comparacion");}
;

bucle: {pila.push(pi.size());} WHILE '('condicion')' DO bloque {
        Integer pos = (Integer) pila.pop(); // posicion 3 
        pi.add(pos.intValue(),String.valueOf(pi.size()+2));// cambia el valor blanco en la polaca por el salto(size de polaca)               
        pi.remove(pos.intValue()+1);  

        pos = (Integer) pila.pop(); 
        pi.add(String.valueOf(pos)); 
        pi.add("bi");                
}
;

impresion: PRINT'('CADENA')'';' {pi.add($3.sval); $$=$3; lexico.getTabla().addTipo($3.sval, "STRING");  logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
|PRINT'('CADENA')' error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
|PRINT'('';' error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
|PRINT';' error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
;

asignacion: iden ASIG expresion  		      {pi.add($2.sval); $$=$1;logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
  |  IDENTIFICADOR '[' expresion ']' ASIG expresion   {if (lexico.getTabla().existeTipoVariable($1.sval,"ARRAY FLOAT")){
pi.add($1.sval); $$=$1;
}else
    System.out.println("error tu codigo anda para el ojete aprende a programar mong@");
}
  {logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
;

iden: IDENTIFICADOR {if (lexico.getTabla().existeTipoVariable($1.sval,"FLOAT")){
pi.add($1.sval); $$=$1;
}else
    logSintactico.addLogger("Error sintactico en linea: "+lexico.getLineas()+"se esperaba otro valor");
}


expresion: expresion '+' termino { pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  |  expresion '-' termino          {pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  | termino  
;

termino: termino '*' argumento  {pi.add($2.sval); $$=$1;}
  |  termino '/' argumento      {pi.add($2.sval); $$=$1;}
  |  argumento  
  | error {logSintactico.addLogger("Error sintactico en la linea"+lexico.getLineas()+": no es posible resolver la expresion");} {System.out.println($2.sval);}
;

num: '-'NUMERO {putNegativo($2.sval);}
| NUMERO 
;

argumento: IDENTIFICADOR {pi.add($1.sval); $$=$1;}
  |  num                {pi.add($1.sval); $$=$1;}
  |  IDENTIFICADOR '[' expresion ']'   {pi.add($1.sval); $$=$1;}
;

%%
  private Logger logSintactico = new Logger("sintactico.log");
  private AnalizadorLexico lexico;
  private Vector<String> pi;
  private Stack<Integer> pila;

  public Parser(AnalizadorLexico l) {
         lexico = l;
         pi = new Vector<String>();
         pila = new Stack<Integer>();
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

        
public void imprimirPolacaInversa() {
    for (int i = 0; i < pi.size(); i++) {
        System.out.println(i+":"+pi.elementAt(i));
    }
}
