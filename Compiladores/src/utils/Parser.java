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






//#line 2 "gramatica.y"

package utils;
import java.io.IOException;
import java.util.Vector;
import java.util.Stack;
//#line 23 "Parser.java"




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
    3,    3,    3,    4,    4,    4,    4,   11,   11,   12,
    8,   15,    8,   13,   13,   13,   13,   14,   16,   17,
   17,   17,   19,   19,   19,   19,   19,   19,    5,    5,
    5,   23,   20,   21,   21,   21,   22,    7,   24,   25,
   25,   25,   25,   25,   25,   10,   28,   29,   10,   10,
   10,   10,   27,   26,   26,   26,   30,   30,   30,   31,
   31,   18,   18,   32,   18,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    1,    0,    2,    1,    0,
    2,    1,    1,    3,    4,    2,    2,    1,    3,    4,
    2,    0,    5,    5,    3,    4,    2,    3,    3,    3,
    1,    3,    1,    1,    1,    1,    1,    1,    3,    4,
    1,    0,    2,    4,    3,    2,    1,    2,    1,    4,
    3,    2,    2,    1,    4,    4,    0,    0,    9,    3,
    3,    3,    1,    3,    3,    1,    3,    3,    1,    2,
    1,    1,    1,    0,    5,
};
final static short yydefred[] = {                         0,
   13,    0,    0,   49,    0,    0,    0,    0,    4,    6,
    0,    9,    0,   12,    0,    0,    0,   18,   17,    0,
    0,    0,   16,    0,   71,    0,   69,    0,    0,   73,
    5,    0,    8,    0,   41,    0,    0,   11,    0,   54,
    0,   48,    0,    0,   14,    0,    0,    0,    0,   70,
   61,    0,    0,    0,    0,   31,   37,   36,   38,   33,
   34,   35,    0,    0,    0,    0,    0,   43,   27,    0,
    0,   21,    2,   22,    0,   53,    0,   60,    0,    0,
   19,    0,   15,    0,    0,    0,   67,   68,    0,    0,
    0,    0,    0,   47,   39,    0,    0,    0,    0,    0,
   56,   58,   20,    0,   44,   30,   32,   40,    0,    0,
    0,    0,   50,   55,    0,   75,    3,   24,    0,   23,
    0,    0,    0,   29,   59,
};
final static short yydgoto[] = {                          7,
    8,   72,    9,   10,   33,   11,   12,   38,   13,   14,
   20,   23,   39,   74,   98,  120,   63,   27,   65,   34,
   35,   95,   36,   15,   42,   28,   16,   17,  115,   29,
   30,   49,
};
final static short yysindex[] = {                       144,
    0,    0,  -55,    0, -219,  -24,    0,  144,    0,    0,
  -28,    0, -244,    0,   -5, -250,  -69,    0,    0,  -27,
  -62, -201,    0,    0,    0, -214,    0,   54,   11,    0,
    0,  136,    0,  -34,    0, -203,  -37,    0, -114,    0,
  -32,    0,  -29,  -24,    0, -186, -195,   19,   -4,    0,
    0,  -24,  -24,  -24,  -24,    0,    0,    0,    0,    0,
    0,    0,   50,  129,  -24,  144,  -95,    0,    0,  136,
  144,    0,    0,    0,   60,    0,   41,    0,   63,  -20,
    0,   10,    0,  -24,   11,   11,    0,    0, -159,  -24,
 -145,  -13,  144,    0,    0,   75, -118, -143,   62,   69,
    0,    0,    0,  -12,    0,    0,    0,    0,  -57, -141,
    0,    6,    0,    0, -139,    0,    0,    0,  144,    0,
  -24,   51,   64,    0,    0,
};
final static short yyrindex[] = {                       -14,
    0,  -91,    0,    0,    0,    0,    0,    5,    0,    0,
 -130,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,    0,    0,   24,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -14,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   70,    0,    0,  -14,  -14,    0,    0,    0,
  -14,    0,    0,    0,  105,    0,    0,    0,  115,    0,
    0,    0,    0,    0,   34,   55,    0,    0,   80,    0,
    0,    0,  -14,    0,    0,  -38,  -14,    0,  125,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -14,  -21,
   90,    0,    0,    0,    0,    0,    0,    0,  -14,    0,
    0,  -14,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -53,   66,  349,    0,    0,    0,    0,    0,    0,    0,
    0,  112,    0,    0,    0,    0,   65,  -18,   72,    0,
  103,    0,    0,    0,    0,  -33,    0,    0,    0,   -3,
    0,    0,
};
final static int YYTABLESIZE=471;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   72,   25,   70,   19,    1,   32,  111,   77,   71,   79,
   80,   32,   26,   64,   37,   26,   46,   97,   26,   43,
   26,   44,   52,   66,   53,    7,   76,   93,   47,   78,
   52,   45,   53,   64,   41,   87,   88,   21,   22,  109,
   72,   72,   72,   72,    7,   72,   91,   72,   85,   86,
  104,   64,   54,   40,   65,   21,   50,   55,   68,   72,
   72,   72,   72,   66,   66,  122,   66,  117,   66,   46,
   81,  106,  102,   64,   64,   82,   64,   83,   64,   45,
  116,  100,   66,   52,   25,   53,   84,  123,   66,    3,
   89,   74,   64,   72,   65,   65,   52,   65,   53,   65,
   99,   26,  103,  105,   52,   52,   52,   53,   53,   46,
  107,  108,   51,   65,   62,  110,   66,  112,  118,   45,
  113,  101,  125,   72,   51,   72,   64,  114,  119,    3,
  121,   42,   94,   48,   96,   90,   67,    1,    2,    3,
    0,    1,    2,    3,   52,    4,    5,   65,   66,    4,
    5,    6,    0,    0,   62,    6,    0,    0,   64,    0,
    1,    2,    3,    0,   51,    0,    0,    0,    4,    5,
    0,    0,    0,    0,    6,  124,    0,    0,   63,   65,
   26,    0,    0,    0,    0,    0,    0,    0,   60,   62,
   61,    0,   46,    0,   46,   60,   62,   61,    1,    2,
    3,   18,   45,    0,   45,    0,    4,    5,    0,    0,
    0,    0,    6,    0,    3,    0,    0,   25,   25,   25,
   25,    0,   69,   25,   24,   25,   25,   24,    0,   52,
    0,   25,   24,   75,   26,   26,   26,   26,   25,   62,
   26,   25,   26,   26,   10,    0,   25,    7,   26,   51,
    0,    0,    0,    0,    0,    0,   72,   72,   72,   72,
    0,    0,   72,   10,   72,   72,    7,   72,   72,   72,
   72,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   66,   66,   66,    0,    0,   66,    0,   66,   66,   64,
   64,   64,   64,   66,    0,   64,    0,   64,   64,    0,
    0,    0,    0,   64,    0,    0,    1,    2,    3,    0,
   65,   65,   65,   65,    4,    5,   65,    0,   65,   65,
    6,    0,    0,    0,   65,   46,   46,   46,   46,    0,
    0,   46,    0,   46,   46,   45,   45,   45,   45,   46,
    0,   45,    0,   45,   45,    3,    3,    3,    3,   45,
   28,    3,    0,    3,    3,    0,   31,    0,    0,    3,
   52,   52,   52,   52,    0,    0,   52,    0,   52,   52,
   62,   62,   62,   62,   52,    0,   62,    0,   62,   62,
   51,   51,   51,   51,   62,    0,   51,   73,   51,   51,
    0,   56,   24,    0,   51,   57,   58,   59,    0,    1,
    2,    3,   57,   58,   59,    0,   25,    4,    5,    0,
    0,    0,    0,    6,   92,   73,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   31,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   31,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   31,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         91,
    0,   40,   40,   59,    0,   40,  125,   41,  123,   43,
   44,   40,   45,   32,  259,   45,   44,   71,   40,  270,
   45,   91,   43,    0,   45,   40,   59,  123,   91,   59,
   43,   59,   45,    0,   40,   54,   55,  257,  258,   93,
   40,   41,   42,   43,   40,   45,   65,   47,   52,   53,
   84,   70,   42,   59,    0,  257,  271,   47,  262,   59,
   60,   61,   62,   40,   41,  119,   43,  125,   45,    0,
  257,   90,   93,   40,   41,  271,   43,   59,   45,    0,
   93,   41,   59,   43,  123,   45,   91,  121,  123,    0,
   41,   91,   59,   93,   40,   41,   43,   43,   45,   45,
   41,  123,   93,  263,    0,   43,   43,   45,   45,   40,
  256,  125,   59,   59,    0,   41,   93,  261,  260,   40,
   59,   59,   59,  123,    0,  125,   93,   59,  123,   40,
  270,  262,   67,   22,   70,   64,   34,  256,  257,  258,
   -1,  256,  257,  258,   40,  264,  265,   93,  125,  264,
  265,  270,   -1,   -1,   40,  270,   -1,   -1,  125,   -1,
  256,  257,  258,   -1,   40,   -1,   -1,   -1,  264,  265,
   -1,   -1,   -1,   -1,  270,  125,   -1,   -1,  270,  125,
   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   -1,  123,   -1,  125,   60,   61,   62,  256,  257,
  258,  257,  123,   -1,  125,   -1,  264,  265,   -1,   -1,
   -1,   -1,  270,   -1,  125,   -1,   -1,  256,  257,  258,
  259,   -1,  260,  262,  257,  264,  265,  257,   -1,  125,
   -1,  270,  257,  266,  256,  257,  258,  259,  271,  125,
  262,  271,  264,  265,  259,   -1,  271,  262,  270,  125,
   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,  258,  259,
   -1,   -1,  262,  259,  264,  265,  262,  267,  268,  269,
  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  256,
  257,  258,  259,   -1,   -1,  262,   -1,  264,  265,  256,
  257,  258,  259,  270,   -1,  262,   -1,  264,  265,   -1,
   -1,   -1,   -1,  270,   -1,   -1,  256,  257,  258,   -1,
  256,  257,  258,  259,  264,  265,  262,   -1,  264,  265,
  270,   -1,   -1,   -1,  270,  256,  257,  258,  259,   -1,
   -1,  262,   -1,  264,  265,  256,  257,  258,  259,  270,
   -1,  262,   -1,  264,  265,  256,  257,  258,  259,  270,
  261,  262,   -1,  264,  265,   -1,    8,   -1,   -1,  270,
  256,  257,  258,  259,   -1,   -1,  262,   -1,  264,  265,
  256,  257,  258,  259,  270,   -1,  262,   -1,  264,  265,
  256,  257,  258,  259,  270,   -1,  262,   39,  264,  265,
   -1,  256,  257,   -1,  270,  267,  268,  269,   -1,  256,
  257,  258,  267,  268,  269,   -1,  271,  264,  265,   -1,
   -1,   -1,   -1,  270,   66,   67,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   97,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  109,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  122,
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
"sentencia : asignacion",
"sentencia : error",
"declaracion : FLOAT variables ';'",
"declaracion : ARRAY FLOAT arreglo ';'",
"declaracion : ARRAY arreglo",
"declaracion : FLOAT ';'",
"variables : IDENTIFICADOR",
"variables : variables ',' IDENTIFICADOR",
"arreglo : IDENTIFICADOR '[' NUMERO ']'",
"seleccion : comienzoif bloque",
"$$3 :",
"seleccion : comienzoif bloqueThen $$3 ELSE bloqueElse",
"comienzoif : IF '(' condicion ')' THEN",
"comienzoif : IF '(' condicion",
"comienzoif : IF '(' condicion ')'",
"comienzoif : IF THEN",
"bloqueThen : '{' sentencias '}'",
"bloqueElse : '{' sentencias '}'",
"condicion : argumento comparador argumento",
"condicion : error",
"condicion : comparador argumento error",
"comparador : '<'",
"comparador : '>'",
"comparador : '='",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : DISTINTO",
"bucle : comienzo_while condicion_while bloque_while",
"bucle : comienzo_while '{' sentencia '}'",
"bucle : condicion_while",
"$$4 :",
"comienzo_while : $$4 WHILE",
"condicion_while : '(' condicion ')' DO",
"condicion_while : '(' condicion ')'",
"condicion_while : '(' condicion",
"bloque_while : bloque",
"impresion : comienzoPrint cadena",
"comienzoPrint : PRINT",
"cadena : '(' CADENA ')' ';'",
"cadena : '(' CADENA ')'",
"cadena : '(' CADENA",
"cadena : '(' ';'",
"cadena : ';'",
"cadena : '(' expresion ')' ';'",
"asignacion : iden ASIG expresion ';'",
"$$5 :",
"$$6 :",
"asignacion : IDENTIFICADOR $$5 '[' expresion ']' $$6 ASIG expresion ';'",
"asignacion : iden ASIG ';'",
"asignacion : ASIG expresion ';'",
"asignacion : iden ASIG expresion",
"iden : IDENTIFICADOR",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' argumento",
"termino : termino '/' argumento",
"termino : argumento",
"num : '-' NUMERO",
"num : NUMERO",
"argumento : IDENTIFICADOR",
"argumento : num",
"$$7 :",
"argumento : IDENTIFICADOR $$7 '[' expresion ']'",
};

//#line 239 "gramatica.y"
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
//#line 469 "Parser.java"
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
{lexico.getTabla().addTipo(val_peek(0).sval,"FLOAT",logSintactico,lexico.getLineas(),"1");}
break;
case 20:
//#line 47 "gramatica.y"
{ extraerExpresion(val_peek(3).sval,val_peek(1).sval);}
break;
case 21:
//#line 52 "gramatica.y"
{ 
            if (!pila.isEmpty()){
            Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
            pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
            pi.remove(pos.intValue()+1);          
            label.add(pi.size());
            }
}
break;
case 22:
//#line 61 "gramatica.y"
{ 
            pi.add(" "); /*agrega un blanco en  la polaca*/
            pi.add("JMP"); /*agrega un bi en la polaca */
            if (!pila.isEmpty()){
            Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
            pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
            pi.remove(pos.intValue()+1);          
            label.add(pi.size());
            pila.push(pi.size()- 2);/*apila el lugar en blanco en la pila*/
            }
}
break;
case 23:
//#line 71 "gramatica.y"
{ 
    if (!pila.isEmpty()){
        Integer pos = (Integer) pila.pop(); /* saca el tope de la pila*/
        pi.add(pos.intValue(),String.valueOf(pi.size()));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
        pi.remove(pos.intValue()+1);            
        label.add(pi.size());
        logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");
    }
}
break;
case 25:
//#line 81 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un )");}
break;
case 26:
//#line 82 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un THEN ");}
break;
case 27:
//#line 83 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 30:
//#line 95 "gramatica.y"
{pi.add(val_peek(1).sval);
if ( val_peek(1).sval.equals("<")){
pi.add(" "); pi.add("JNB"); pila.push(pi.size()- 2 );
}
if ( val_peek(1).sval.equals(">")){
pi.add(" "); pi.add("JNA"); pila.push(pi.size()- 2 );
}
if ( val_peek(1).sval.equals("=")){
pi.add(" "); pi.add("JNE"); pila.push(pi.size()- 2 );
}
if ( val_peek(1).sval.equals("<=")){
pi.add(" "); pi.add("JNBE"); pila.push(pi.size()- 2 );
}
if ( val_peek(1).sval.equals(">=")){
pi.add(" "); pi.add("JNGE"); pila.push(pi.size()- 2 );
}
if ( val_peek(1).sval.equals("<>")){
    pi.add(" ");  pi.add("JE"); pila.push(pi.size()- 2 );
}

}
break;
case 31:
//#line 116 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
break;
case 32:
//#line 117 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": condicion no valida se esperaba un argumento");}
break;
case 40:
//#line 130 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 41:
//#line 131 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un WHILE");}
break;
case 42:
//#line 134 "gramatica.y"
{pila.push(pi.size());}
break;
case 45:
//#line 138 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un DO ");}
break;
case 46:
//#line 139 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una sentencia");}
break;
case 47:
//#line 142 "gramatica.y"
{
        Integer pos = (Integer) pila.pop(); /* posicion 3 */
        pi.add(pos.intValue(),String.valueOf(pi.size()+2));/* cambia el valor blanco en la polaca por el salto(size de polaca)               */
        pi.remove(pos.intValue()+1);  
             
        if (!pila.empty()){
            pos = (Integer) pila.pop(); 
            pi.add(String.valueOf(pos)); 
            label.add(pos);
            pi.add("JMP");                
        
        }
        /*label.add(pi.elementAt(pos));   */
        label.add(pi.size());   
            
}
break;
case 48:
//#line 160 "gramatica.y"
{pi.add(val_peek(1).sval);}
break;
case 50:
//#line 166 "gramatica.y"
{pi.add(val_peek(2).sval); yyval=val_peek(2); lexico.getTabla().addTipo(val_peek(2).sval, "STRING",logSintactico,lexico.getLineas(),"1");  logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
break;
case 51:
//#line 167 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 52:
//#line 168 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un );");}
break;
case 53:
//#line 169 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 54:
//#line 170 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
break;
case 55:
//#line 171 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 56:
//#line 174 "gramatica.y"
{pi.add(val_peek(2).sval); yyval=val_peek(3);logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
break;
case 57:
//#line 175 "gramatica.y"
{

 if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"ARRAY FLOAT")){
    pi.add(val_peek(0).sval); pi.add("^"); pi.add("4"); yyval=val_peek(0); entro1 = true;
    }else
        logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+" variable no declarada");
    logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");


}
break;
case 58:
//#line 184 "gramatica.y"
{ if (entro1){
   pi.add("1"); pi.add("-"); pi.add("*"); pi.add("+");pi.add("&"); entro1=false;
    } }
break;
case 59:
//#line 186 "gramatica.y"
{ pi.add(val_peek(2).sval); yyval=val_peek(8); logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion"); }
break;
case 60:
//#line 188 "gramatica.y"
{ logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");}
break;
case 61:
//#line 189 "gramatica.y"
{ logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba una asignacion");}
break;
case 62:
//#line 190 "gramatica.y"
{ logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 63:
//#line 193 "gramatica.y"
{if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"FLOAT")){
pi.add(val_peek(0).sval); yyval=val_peek(0);
}else
    logSintactico.addLogger("Error sintactico en linea: "+lexico.getLineas()+" no se encuentra declarada la variable");
}
break;
case 64:
//#line 200 "gramatica.y"
{ pi.add(val_peek(1).sval); yyval=val_peek(2); logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 65:
//#line 201 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2); logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 67:
//#line 205 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2);}
break;
case 68:
//#line 206 "gramatica.y"
{pi.add(val_peek(1).sval); yyval=val_peek(2);}
break;
case 70:
//#line 210 "gramatica.y"
{putNegativo(val_peek(0).sval); pi.add(val_peek(1).sval+val_peek(0).sval); yyval=val_peek(1);}
break;
case 71:
//#line 211 "gramatica.y"
{pi.add(val_peek(0).sval); yyval=val_peek(0);}
break;
case 72:
//#line 214 "gramatica.y"
{
if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"FLOAT")){
    pi.add(val_peek(0).sval); yyval=val_peek(0);
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada la variable");
                                                        


}
break;
case 74:
//#line 224 "gramatica.y"
{
if (lexico.getTabla().existeTipoVariable(val_peek(0).sval,"ARRAY FLOAT")){
    pi.add(val_peek(0).sval); pi.add("^"); pi.add("4"); entro2 = true;
}else
    System.out.println("ERROR en linea "+lexico.getLineas()+": no se ecuentra declarada el arreglo");

}
break;
case 75:
//#line 230 "gramatica.y"
{
    if (entro2){
        pi.add("1"); pi.add("-"); pi.add("*"); pi.add("+");pi.add("&");
        entro2 = false;
    }
}
break;
//#line 907 "Parser.java"
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
