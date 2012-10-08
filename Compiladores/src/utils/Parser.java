//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "gramatica.y"


package utils;
import java.io.IOException;
import java.util.Vector;
import java.util.Stack;
//#line 24 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short FLOAT=258;
public final static short IF=259;
public final static short THEN=260;
public final static short ELSE=261;
public final static short WHILE=262;
public final static short DO=263;
public final static short PRINT=264;
public final static short ARRAY=265;
public final static short CADENA=266;
public final static short MAYOR_IGUAL=267;
public final static short MENOR_IGUAL=268;
public final static short DISTINTO=269;
public final static short ASIG=270;
public final static short NUMERO=271;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    2,    1,    1,    3,    6,    3,    3,    9,
    3,    3,    3,    4,    4,    4,    4,   11,   11,   11,
   12,   12,    8,    8,    8,    8,   16,    8,    8,   15,
   17,   14,   14,   14,   14,   19,   19,   19,   19,   19,
   19,   19,    5,    5,    5,    5,    5,   23,   20,   21,
   22,    7,    7,    7,    7,    7,   10,   10,   10,   10,
   24,   13,   13,   13,   25,   25,   25,   26,   25,   27,
   27,   18,   18,   18,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    1,    0,    2,    1,    0,
    2,    2,    2,    3,    4,    3,    3,    1,    3,    1,
    4,    1,    6,    5,    4,    2,    0,    9,    1,    3,
    3,    3,    3,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    3,    5,    2,    4,    5,    0,    2,    4,
    1,    5,    5,    4,    3,    6,    3,    6,    4,    4,
    1,    3,    3,    1,    3,    3,    1,    0,    2,    2,
    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    4,    6,
    0,    9,    0,    0,    0,    0,   20,   18,    0,    0,
    0,    0,   22,    0,    0,    0,   68,    0,   71,    0,
    0,   67,    0,   73,   13,    5,    0,    8,    0,    0,
    0,   29,    0,   11,   12,    0,    0,   17,   14,    0,
   55,    0,    0,    0,    0,    0,   16,   69,    0,   70,
    0,    0,    0,    0,    0,    0,   40,   39,   41,   36,
   37,   38,    0,    0,    0,    0,    0,    0,   45,   49,
   26,    0,    0,    0,    0,   19,    0,   54,    0,    0,
   15,    0,   60,    0,    0,   65,   66,    0,   42,    0,
    0,    0,    0,    0,   51,    2,   43,    0,   59,    0,
   53,   52,    0,   21,   74,   50,   33,   32,   34,    0,
   46,    0,    0,   25,    0,    0,   56,   44,   47,    3,
   24,    0,    0,   23,   27,    0,    0,    0,    0,    0,
   28,    0,   31,
};
final static short yydgoto[] = {                          7,
    8,  105,    9,   10,   38,   11,   12,   44,   13,   14,
   20,   26,   31,   73,  135,  137,  141,   32,   75,   39,
   40,  107,   41,   15,   33,   58,   34,
};
final static short yysindex[] = {                        52,
  -76,  -50,   28, -152,  -34, -224,    0,   52,    0,    0,
    3,    0, -225,   -3, -211,  -34,    0,    0, -189,   11,
 -187,  -17,    0,  -16, -128, -158,    0,   26,    0, -156,
   35,    0,   54,    0,    0,    0,  -43,    0,  -20, -146,
 -140,    0,  -37,    0,    0,  -15,  -22,    0,    0, -133,
    0,   91, -123,   80,  -34,   75,    0,    0,  -34,    0,
 -121,  -34,  -34,   16,   16,    0,    0,    0,    0,    0,
    0,    0,   95,   58,   16,   52,  -43,   20,    0,    0,
    0,  -43, -118,   71, -131,    0,  -46,    0,   81,   -8,
    0,   19,    0,   54,   54,    0,    0, -122,    0,   12,
 -114,   22,  -27,   52,    0,    0,    0,  -25,    0,  -34,
    0,    0, -112,    0,    0,    0,    0,    0,    0, -111,
    0, -161,   24,    0, -179,   71,    0,    0,    0,    0,
    0,   34,   52,    0,    0,   38, -113,    0,   29,   52,
    0,   48,    0,
};
final static short yyrindex[] = {                       -11,
 -119,    0,    0,    0,    0,    0,    0,    5,    0,    0,
 -109,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -35,    0,    0,
    0,    0,   -5,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -41,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -11,    0,  -11,    0,    0,
    0,    0,    0,   96,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    6,    7,    0,    0,    0,    0,    0,
    0,    0,    0,  -11,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -11,    0,    0,   99,    0,    0,    0,    0,
    0,  -11,  -11,    0,    0,  -11,    0,    1,    0,  -11,
    0,  -11,    0,
};
final static short yygindex[] = {                         0,
  -51,   27,   14,    0,    0,    0,    0,    0,    0,    0,
    0,  129,   17,   31,    0,    0,    0,    9,   86,    0,
  122,    0,    0,    0,   68,    0,    0,
};
final static int YYTABLESIZE=327;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
    3,   30,   82,   42,    1,   72,   72,   72,   19,   72,
   30,   72,  112,  122,   16,  125,   70,   72,   71,   77,
   62,   36,   63,   72,   72,   72,   72,   30,    7,   30,
   42,   35,   47,   43,   62,   64,   63,   64,   54,   64,
    3,   53,   37,   83,    7,   74,   62,   63,   62,   63,
   62,   63,  123,   64,   50,   45,   30,   72,   46,    3,
   30,   62,   84,   63,   62,   63,   48,   22,   51,   49,
   85,   90,   96,   97,   55,   92,  131,   62,    6,   63,
  132,  136,    6,  101,  114,   74,   21,   64,  142,  102,
   74,  106,    6,   61,  129,   64,    6,   57,   62,   63,
   65,  116,   76,   23,   24,   25,    6,  103,  118,   79,
    6,  115,  108,   62,   60,   63,   59,   70,   72,   71,
   89,   80,   62,   86,   63,    3,  126,   23,   24,   94,
   95,   87,   88,   91,   93,   98,   36,  109,  110,  113,
  116,  119,  104,  127,  128,  106,  120,  139,  130,   36,
   61,  140,   48,   56,   57,   36,  133,   58,  134,  100,
   78,    0,  138,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  143,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   17,   18,    0,    0,  111,
    0,    0,   66,   28,   35,   42,    0,    0,   81,    0,
   72,   27,   28,   67,   68,   69,    0,   29,  121,   42,
  124,   72,   72,   72,    0,    0,   29,    0,   27,   28,
   27,   28,    0,    0,   10,    0,    0,   10,   52,    0,
    7,    0,    0,   29,    0,   29,    3,    3,    3,    3,
   10,   30,    3,   10,    3,    3,    7,  117,   28,    0,
    3,    0,   28,    0,    0,    0,    1,    2,    0,    0,
    1,    2,   29,    3,    4,    0,   29,    3,    4,    5,
    1,    2,    0,    5,    1,    2,    0,    3,    4,    0,
    0,    3,    4,    5,    1,    2,    0,    5,    1,    2,
    0,    3,    4,   99,    0,    3,    4,    5,    0,    0,
    0,    5,    0,    0,   67,   68,   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   45,   40,   45,    0,   41,   42,   43,   59,   45,
   45,   47,   59,   41,   91,   41,   60,   61,   62,   40,
   43,    8,   45,   59,   60,   61,   62,   45,   40,   45,
  256,  256,   16,  259,   43,   41,   45,   43,   22,   45,
   40,   59,   40,   59,   40,   37,   41,   41,   43,   43,
   45,   45,  104,   59,   44,   59,   45,   93,  270,   59,
   45,   43,   46,   45,   59,   59,  256,   40,  256,   59,
   93,   55,   64,   65,   91,   59,  256,   43,   59,   45,
  260,  133,   59,   75,   93,   77,   59,   93,  140,   76,
   82,   78,   59,   59,  256,   42,   59,  256,   93,   93,
   47,  263,  123,  256,  257,  258,   59,   77,  100,  256,
   59,   93,   82,   43,  271,   45,   91,   60,   61,   62,
   41,  262,   43,  257,   45,  125,  110,  256,  257,   62,
   63,   41,  256,   59,  256,   41,  123,  256,  270,   59,
  263,  256,  123,  256,  256,  132,  125,  261,  125,  136,
  270,  123,  262,   25,   59,  142,  123,   59,  132,   74,
   39,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,   -1,  256,
   -1,   -1,  256,  257,  256,  257,   -1,   -1,  256,   -1,
  256,  256,  257,  267,  268,  269,   -1,  271,  256,  271,
  256,  267,  268,  269,   -1,   -1,  271,   -1,  256,  257,
  256,  257,   -1,   -1,  256,   -1,   -1,  259,  266,   -1,
  262,   -1,   -1,  271,   -1,  271,  256,  257,  258,  259,
  256,  261,  262,  259,  264,  265,  262,  256,  257,   -1,
  270,   -1,  257,   -1,   -1,   -1,  257,  258,   -1,   -1,
  257,  258,  271,  264,  265,   -1,  271,  264,  265,  270,
  257,  258,   -1,  270,  257,  258,   -1,  264,  265,   -1,
   -1,  264,  265,  270,  257,  258,   -1,  270,  257,  258,
   -1,  264,  265,  256,   -1,  264,  265,  270,   -1,   -1,
   -1,  270,   -1,   -1,  267,  268,  269,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=271;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENTIFICADOR","FLOAT","IF","THEN",
"ELSE","WHILE","DO","PRINT","ARRAY","CADENA","MAYOR_IGUAL","MENOR_IGUAL",
"DISTINTO","ASIG","NUMERO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencias",
"bloque : sentencia",
"bloque : '{' sentencias '}'",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : declaracion",
"$$1 :",
"sentencia : $$1 bucle",
"sentencia : impresion",
"$$2 :",
"sentencia : $$2 seleccion",
"sentencia : asignacion ';'",
"sentencia : ';' error",
"declaracion : FLOAT variables ';'",
"declaracion : ARRAY FLOAT arreglo ';'",
"declaracion : ARRAY arreglo error",
"declaracion : FLOAT ';' error",
"variables : IDENTIFICADOR",
"variables : variables ',' IDENTIFICADOR",
"variables : error",
"arreglo : IDENTIFICADOR '[' expresion ']'",
"arreglo : error",
"seleccion : IF '(' condicion ')' THEN bloque",
"seleccion : IF '(' condicion ')' error",
"seleccion : IF '(' condicion error",
"seleccion : IF error",
"$$3 :",
"seleccion : IF '(' condicion ')' THEN bloqueThen $$3 ELSE bloqueElse",
"seleccion : error",
"bloqueThen : '{' sentencias '}'",
"bloqueElse : '{' sentencias '}'",
"condicion : argumento comparador argumento",
"condicion : argumento comparador error",
"condicion : comparador argumento error",
"condicion : error",
"comparador : '<'",
"comparador : '>'",
"comparador : '='",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : DISTINTO",
"comparador : error",
"bucle : comienzo_while condicion_while bloque_while",
"bucle : comienzo_while '{' sentencia '}' error",
"bucle : condicion_while error",
"bucle : comienzo_while '(' condicion error",
"bucle : comienzo_while '(' condicion ')' error",
"$$4 :",
"comienzo_while : $$4 WHILE",
"condicion_while : '(' condicion ')' DO",
"bloque_while : bloque",
"impresion : PRINT '(' CADENA ')' ';'",
"impresion : PRINT '(' CADENA ')' error",
"impresion : PRINT '(' ';' error",
"impresion : PRINT ';' error",
"impresion : PRINT '(' expresion ')' ';' error",
"asignacion : iden ASIG expresion",
"asignacion : IDENTIFICADOR '[' expresion ']' ASIG expresion",
"asignacion : iden ASIG ';' error",
"asignacion : ASIG expresion ';' error",
"iden : IDENTIFICADOR",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' argumento",
"termino : termino '/' argumento",
"termino : argumento",
"$$5 :",
"termino : error $$5",
"num : '-' NUMERO",
"num : NUMERO",
"argumento : IDENTIFICADOR",
"argumento : num",
"argumento : IDENTIFICADOR '[' expresion ']'",
};

//#line 189 "gramatica.y"

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
//#line 421 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 15 "gramatica.y"
{

logSintactico.addLogger("El programa finalizo correctamente");
}
break;
case 7:
//#line 30 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Bucle");}
break;
case 10:
//#line 32 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion");}
break;
case 13:
//#line 34 "gramatica.y"
{logSintactico.addLogger("Error sintactico en linea "+lexico.getLineas()+": sentencia no permitida");}
break;
case 14:
//#line 37 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un FLOAT");}
break;
case 15:
//#line 38 "gramatica.y"
{ logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un ARRAY");}
break;
case 16:
//#line 39 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 17:
//#line 40 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 18:
//#line 43 "gramatica.y"
{lexico.getTabla().addTipo(val_peek(0).sval,"FLOAT");}
break;
case 20:
//#line 45 "gramatica.y"
{logSintactico.addLogger("Error sintactica en la linea"+lexico.getLineas()+": declaracion de variables");}
break;
case 21:
//#line 48 "gramatica.y"
{pi.add(val_peek(3).sval); yyval=val_peek(3);  lexico.getTabla().addTipo(val_peek(3).sval,"ARRAY FLOAT");}
break;
case 22:
//#line 49 "gramatica.y"
{logSintactico.addLogger("Error sintactica en la linea "+lexico.getLineas()+": sintactico en el arreglo");}
break;
case 23:
//#line 54 "gramatica.y"
{ 
            Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
            pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
            pi.remove(pos.intValue()+1);          
}
break;
case 24:
//#line 59 "gramatica.y"
{logSintactico.addLogger("Error sintactica en la linea "+lexico.getLineas()+": se esperaba un THEN");}
break;
case 25:
//#line 60 "gramatica.y"
{logSintactico.addLogger("Error sintactica en la linea "+lexico.getLineas()+": se esperaba un )");}
break;
case 26:
//#line 61 "gramatica.y"
{logSintactico.addLogger("Error sintactica en la linea "+lexico.getLineas()+": se esperaba una condicion )");}
break;
case 27:
//#line 63 "gramatica.y"
{ 
            pi.add(" "); /*agrega un blanco en  la polaca*/
            pi.add("bi"); /*agrega un bi en la polaca */
            Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
            pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
            pi.remove(pos.intValue()+1);          
            pila.push(pi.size()- 2);/*apila el lugar en blanco en la pila*/
}
break;
case 28:
//#line 72 "gramatica.y"
{ 
        Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
        pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
        pi.remove(pos.intValue()+1);            
        logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");
}
break;
case 29:
//#line 78 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": seleccion no valida");}
break;
case 32:
//#line 87 "gramatica.y"
{pi.add(val_peek(1).sval); pi.add(" "); pi.add("bf"); pila.push(pi.size()- 2 ); }
break;
case 33:
//#line 88 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
break;
case 34:
//#line 89 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
break;
case 35:
//#line 90 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un compardor");}
break;
case 42:
//#line 100 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": no es posible resolver la comparacion");}
break;
case 44:
//#line 104 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 45:
//#line 105 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba un WHILE");}
break;
case 46:
//#line 106 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba una sentencia");}
break;
case 47:
//#line 107 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba un DO ");}
break;
case 48:
//#line 110 "gramatica.y"
{pila.push(pi.size());}
break;
case 51:
//#line 114 "gramatica.y"
{
        Integer pos = (Integer) pila.pop(); /* posicion 3 */
        pi.add(pos.intValue(),String.valueOf(pi.size()+2));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
        pi.remove(pos.intValue()+1);  
        if (!pila.empty()){
            pos = (Integer) pila.pop(); 
            pi.add(String.valueOf(pos)); 
            pi.add("bi");                
        
        }        
            
}
break;
case 52:
//#line 128 "gramatica.y"
{pi.add(val_peek(2).sval); yyval=val_peek(2); lexico.getTabla().addTipo(val_peek(2).sval, "STRING");  logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
break;
case 53:
//#line 129 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 54:
//#line 130 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 55:
//#line 131 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
break;
case 56:
//#line 132 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 57:
//#line 135 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2);logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
break;
case 58:
//#line 136 "gramatica.y"
{if (lexico.getTabla().existeTipoVariable(val_peek(5).sval,"ARRAY FLOAT")){
                                                            pi.add(val_peek(5).sval); yyval=val_peek(5);
                                                            }else
                                                                System.out.println("error tu codigo anda para el ojete aprende a programar mong@");
                                                            logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");
                                                        }
break;
case 59:
//#line 142 "gramatica.y"
{ logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");}
break;
case 60:
//#line 143 "gramatica.y"
{ logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");}
break;
case 61:
//#line 146 "gramatica.y"
{if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"FLOAT")){
pi.add(val_peek(0).sval); yyval=val_peek(0);
}else
    logSintactico.addLogger("Error sintactico en linea: "+lexico.getLineas()+" no se encuentra declarada la variable");
}
break;
case 62:
//#line 153 "gramatica.y"
{ pi.add(val_peek(1).sval); yyval=val_peek(2); logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 63:
//#line 154 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2); logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 65:
//#line 158 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2);}
break;
case 66:
//#line 159 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2);}
break;
case 68:
//#line 161 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea"+lexico.getLineas()+": no es posible resolver la expresion");}
break;
case 69:
//#line 161 "gramatica.y"
{System.out.println(val_peek(0).sval);}
break;
case 70:
//#line 164 "gramatica.y"
{putNegativo(val_peek(0).sval);}
break;
case 72:
//#line 168 "gramatica.y"
{
if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"FLOAT")){
    pi.add(val_peek(0).sval); yyval=val_peek(0);
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada la variable");
                                                        


}
break;
case 73:
//#line 177 "gramatica.y"
{pi.add(val_peek(0).sval); yyval=val_peek(0);}
break;
case 74:
//#line 178 "gramatica.y"
{
if (lexico.getTabla().existeTipoVariable(val_peek(3).sval,"ARRAY FLOAT")){
    pi.add(val_peek(3).sval); yyval=val_peek(3);
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada el arreglo");
                                                        


}
break;
//#line 825 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
