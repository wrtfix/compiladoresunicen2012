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
  |  asignacion 
  |  error {logSintactico.addLogger("Error sintactico en linea "+lexico.getLineas()+": sentencia no permitida");}
;

declaracion: FLOAT variables';' {logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un FLOAT");}
  | ARRAY FLOAT arreglo';' { logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un ARRAY");}
  | ARRAY arreglo {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
  | FLOAT';' {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
;

variables: IDENTIFICADOR {lexico.getTabla().addTipo($1.sval,"FLOAT",logSintactico,lexico.getLineas(),"1");}
  |  variables','IDENTIFICADOR
;

arreglo: IDENTIFICADOR '['NUMERO']' { extraerExpresion($1.sval,$3.sval);}
;


seleccion: comienzoif bloque
{ 
            if (!pila.isEmpty()){
            Integer pos = (Integer) pila.pop(); // saca el tope de la pila
            pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
            pi.remove(pos.intValue()+1);          
            label.add(pi.size());
            }
}
| comienzoif bloqueThen 
{ 
            pi.add(" "); //agrega un blanco en  la polaca
            pi.add("JMP"); //agrega un bi en la polaca 
            Integer pos = (Integer) pila.pop(); // saca el tope de la pila
            pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
            pi.remove(pos.intValue()+1);          
            label.add(pi.size());
            pila.push(pi.size()- 2);//apila el lugar en blanco en la pila
}  
ELSE bloqueElse 
{ 
        Integer pos = (Integer) pila.pop(); // saca el tope de la pila
        pi.add(pos.intValue(),String.valueOf(pi.size()));// cambia el valor blanco en la polaca por el salto(size de polaca)               
        pi.remove(pos.intValue()+1);            
        label.add(pi.size());
        logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");
}
;

comienzoif: IF '('condicion')' THEN
|IF '('condicion {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un )");}
|IF '('condicion')' {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un THEN ");}
|IF THEN {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
;




bloqueThen: '{'sentencias'}' 
;
bloqueElse: '{'sentencias'}'
;	


condicion: argumento comparador argumento  {pi.add($2.sval);
if ( $2.sval.equals("<")){
pi.add(" "); pi.add("JNB"); pila.push(pi.size()- 2 );
}
if ( $2.sval.equals(">")){
pi.add(" "); pi.add("JNA"); pila.push(pi.size()- 2 );
}
if ( $2.sval.equals("=")){
pi.add(" "); pi.add("JNE"); pila.push(pi.size()- 2 );
}
if ( $2.sval.equals("<=")){
pi.add(" "); pi.add("JNBE"); pila.push(pi.size()- 2 );
}
if ( $2.sval.equals(">=")){
pi.add(" "); pi.add("JNGE"); pila.push(pi.size()- 2 );
}
if ( $2.sval.equals("<>")){
    pi.add(" ");  pi.add("JE"); pila.push(pi.size()- 2 );
}

}
  | error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
  | comparador argumento error {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
;


comparador: '<' 
  |  '>' 
  |  '=' 
  |  MENOR_IGUAL 
  |  MAYOR_IGUAL 
  |  DISTINTO 
;

bucle:  comienzo_while condicion_while bloque_while
| comienzo_while '{'sentencia'}' {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
| condicion_while {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un WHILE");}
;

comienzo_while:{pila.push(pi.size());} WHILE
;

condicion_while: '('condicion')' DO
| '('condicion')' {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un DO ");}
| '('condicion {logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una sentencia");}
;

bloque_while: bloque {
        Integer pos = (Integer) pila.pop(); // posicion 3 
        pi.add(pos.intValue(),String.valueOf(pi.size()+2));// cambia el valor blanco en la polaca por el salto(size de polaca)               
        pi.remove(pos.intValue()+1);  
             
        if (!pila.empty()){
            pos = (Integer) pila.pop(); 
            pi.add(String.valueOf(pos)); 
            label.add(pos);
            pi.add("JMP");                
        
        }
        //label.add(pi.elementAt(pos));   
        label.add(pi.size());   
            
}


impresion: comienzoPrint cadena {pi.add($1.sval);}
;

comienzoPrint: PRINT 
;

cadena: '('CADENA')'';' {pi.add($2.sval); $$=$2; lexico.getTabla().addTipo($2.sval, "STRING",logSintactico,lexico.getLineas(),"1");  logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
| '('CADENA')' {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
| '('CADENA {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un );");}
| '('';'  {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
| ';' {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
| '('expresion')'';' {logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
;

asignacion: iden ASIG expresion ';' {pi.add($2.sval); $$=$1;logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
  | IDENTIFICADOR {

 if (lexico.getTabla().existeTipoVariable($1.sval,"ARRAY FLOAT")){
    pi.add($1.sval); pi.add("^"); pi.add("4"); $$=$1; entro1 = true;
    }else
        logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+" variable no declarada");
    logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");


}'[' expresion ']'{ if (entro1){
   pi.add("1"); pi.add("-"); pi.add("*"); pi.add("+");pi.add("&"); entro1=false;
    } } ASIG expresion ';'   { pi.add($7.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion"); }
                                                            
  | iden ASIG';' { logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");} 
  | ASIG expresion';' { logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");} 
  | iden ASIG expresion  { logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");} 
;

iden: IDENTIFICADOR {if (lexico.getTabla().existeTipoVariable($1.sval,"FLOAT")){
pi.add($1.sval); $$=$1;
}else
    logSintactico.addLogger("Error sintactico en linea: "+lexico.getLineas()+" no se encuentra declarada la variable");
}
;

expresion: expresion '+' termino { pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  |  expresion '-' termino          {pi.add($2.sval); $$=$1; logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
  | termino  
;

termino: termino '*' argumento  {pi.add($2.sval); $$=$1;}
  |  termino '/' argumento      {pi.add($2.sval); $$=$1;}
  |  argumento  
;

num: '-'NUMERO {putNegativo($2.sval); pi.add($1.sval+$2.sval); $$=$1;}
| NUMERO {pi.add($1.sval); $$=$1;}
;

argumento: IDENTIFICADOR {
if (lexico.getTabla().existeTipoVariable($1.sval,"FLOAT")){
    pi.add($1.sval); $$=$1;
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada la variable");
                                                        


}
  |  num                
  |  IDENTIFICADOR {
if (lexico.getTabla().existeTipoVariable($1.sval,"ARRAY FLOAT")){
    pi.add($1.sval); pi.add("^"); pi.add("4"); entro2 = true;
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada el arreglo");

} '[' expresion ']'   {
    if (entro2){
        pi.add("1"); pi.add("-"); pi.add("*"); pi.add("+");pi.add("&");
        entro2 = false;
    }
}
;

%%
  private Logger logSintactico = new Logger("sintactico.log");
  private AnalizadorLexico lexico;
  private Vector<String> pi;
  private Stack<Integer> pila;
  private Stack<Integer> label;
  private boolean entro1;
  private boolean entro2;
  public Parser(AnalizadorLexico l) {
         lexico = l;
         pi = new Vector<String>();
         pila = new Stack<Integer>();
         label = new Stack<Integer>();
         entro1 = false;
         entro2 = false;
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
        s.setTipoVariable("NUMERO");
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

void imprimirLabels(){
    while(!label.empty())
        System.out.println(label.pop());
}

void extraerExpresion(String iden, String valor){
    valor = valor.split("\\.")[0];
    lexico.getTabla().addTipo(iden,"ARRAY FLOAT",logSintactico,lexico.getLineas(),valor);
}

public Stack<Integer> getLabels(){
    return label;
}
public Vector<String> getPolaca(){
    return pi;
}