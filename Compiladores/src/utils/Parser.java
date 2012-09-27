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
    3,    3,    3,    4,    4,    4,    4,   11,   11,   11,
   12,   12,    8,    8,    8,    8,    8,    8,   14,   14,
    5,    5,    5,    7,    7,    7,    7,   10,   10,   16,
   16,   16,   16,   16,   16,   13,   13,   13,   17,   17,
   17,   17,   18,   18,   15,   15,   15,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    1,    0,    2,    1,    0,
    2,    2,    2,    3,    3,    3,    3,    1,    3,    1,
    4,    1,    8,   12,    9,    8,    5,    1,    3,    1,
    6,    5,    4,    5,    5,    4,    3,    3,    6,    1,
    1,    1,    1,    1,    1,    3,    3,    1,    3,    3,
    1,    1,    2,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    4,    6,    0,
    9,    0,    0,    0,    0,   20,   18,    0,    0,    0,
    0,   22,    0,    0,   13,    5,    0,    8,   28,    0,
   11,   12,   52,    0,   54,    0,    0,   51,    0,   56,
    0,   17,   14,    0,   37,    0,    0,    0,   16,   15,
    0,    0,    0,   53,    0,    0,    0,    0,    0,   19,
    0,   36,    0,   30,    0,    0,    0,    0,    0,    0,
    0,   49,   50,    0,   35,   34,   21,    0,   33,    2,
    0,   45,   44,   43,   40,   41,   42,    0,    0,   57,
    0,    0,    0,   32,   29,    0,    0,    3,   31,    0,
    0,    0,    0,    0,   26,    0,    0,    0,   24,
};
final static short yydgoto[] = {                          6,
    7,   79,    8,    9,   28,   10,   11,   31,   12,   13,
   19,   24,   37,   66,   38,   88,   39,   40,
};
final static short yysindex[] = {                        35,
  -82,  -55,  -25, -214, -236,    0,   35,    0,    0, -226,
    0, -197,   -6,  -45,  -45,    0,    0, -190,  -15, -185,
  -57,    0,    5,  -52,    0,    0,   37,    0,    0,   51,
    0,    0,    0,    7,    0, -184,   41,    0,   -7,    0,
    6,    0,    0, -164,    0,   59, -152,  -45,    0,    0,
  -42,  -40,  -45,    0,  -45,  -45,  -39,  -39, -163,    0,
  -51,    0,   13,    0,  -13,   70,  -34,   71,   20,   -7,
   -7,    0,    0,  -45,    0,    0,    0,   35,    0,    0,
  -22,    0,    0,    0,    0,    0,    0,  -39, -113,    0,
   41,  -11,  -13,    0,    0,   -8,   35,    0,    0,   35,
   -9,   31, -142, -141,    0,   -2,   35,   33,    0,
};
final static short yyrindex[] = {                      -174,
    0,    0,    0,    0,    0,    0,   25,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -29,    0,    0,   63,    0,  -21,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -174,    0,    0,    0,    0,   -4,
    2,    0,    0,    0,    0,    0,    0, -174,    0,    0,
 -174,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   64, -174, -174,    0,    0,    1, -174,    0,    0, -174,
 -174, -174,    0,   11,    0,   21, -174, -174,    0,
};
final static short yygindex[] = {                         0,
  -24,  -14,   16,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,   73,   17,    0,   47,    0,
};
final static int YYTABLESIZE=300;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         36,
   27,   47,   36,   18,   36,   36,   50,   76,   15,   97,
   23,   55,   55,   55,   21,   55,   65,   55,   41,   25,
   25,   48,   26,   48,    1,   85,   87,   86,   44,   55,
   55,   55,   55,   20,   57,   27,    5,   48,   46,   58,
   46,   22,   23,   43,   47,    5,   47,    5,   55,    5,
   56,   63,   32,   92,   46,   55,   69,   56,   29,   27,
   47,   30,   55,   55,   56,   42,   94,   67,   67,   23,
   45,   48,  101,   72,   73,  102,   51,   91,   99,   25,
   80,   10,  108,   55,   10,   56,   54,    7,   46,    5,
   52,    5,   60,    5,   47,   48,   80,   53,   59,   61,
   78,   70,   71,   62,   95,   77,   74,   26,   80,   78,
   81,   89,   90,   98,  100,  103,   26,   26,  105,  106,
  107,   38,   39,   26,   68,   27,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   23,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   25,   96,    0,    0,    0,
    0,    0,    0,    0,    0,  104,    0,  109,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   14,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,   17,    0,   49,   75,    0,    0,    0,   46,    0,
   33,   34,    0,   64,   34,   64,   34,   34,    0,    0,
    0,   82,    0,    0,    0,   35,   55,    0,   35,    0,
   35,   35,   83,   84,    1,    2,    0,   55,   55,    0,
   93,    3,    4,    1,    2,    1,    2,    1,    2,    0,
    3,    4,    3,    4,    3,    4,   27,   27,   27,   27,
    0,    0,   27,    0,   27,   27,   23,   23,   23,   23,
    0,    0,   23,    0,   23,   23,   25,   25,   25,   25,
   10,    0,   25,   10,   25,   25,    7,    1,    2,    1,
    2,    1,    2,    0,    3,    4,    3,    4,    3,    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   59,   45,   59,   45,   45,   59,   59,   91,  123,
    0,   41,   42,   43,   40,   45,   59,   47,   15,  256,
    0,   43,    7,   45,    0,   60,   61,   62,   44,   59,
   60,   61,   62,   59,   42,  262,   59,   59,   43,   47,
   45,  256,  257,   59,   43,   59,   45,   59,   43,   59,
   45,   48,   59,   78,   59,   43,   53,   45,  256,   59,
   59,  259,   43,   93,   45,  256,   81,   51,   52,   59,
  256,   93,   97,   57,   58,  100,   40,   74,   93,   59,
   65,  256,  107,   43,  259,   45,  271,  262,   93,   59,
   40,   59,  257,   59,   93,   91,   81,   91,   93,   41,
  123,   55,   56,  256,   88,   93,  270,   92,   93,  123,
   41,   41,   93,  125,  123,  125,  101,  102,  261,  261,
  123,   59,   59,  108,   52,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,  260,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  256,  257,   -1,  256,  256,   -1,   -1,   -1,  266,   -1,
  256,  257,   -1,  256,  257,  256,  257,  257,   -1,   -1,
   -1,  256,   -1,   -1,   -1,  271,  256,   -1,  271,   -1,
  271,  271,  267,  268,  257,  258,   -1,  267,  268,   -1,
  263,  264,  265,  257,  258,  257,  258,  257,  258,   -1,
  264,  265,  264,  265,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
  256,   -1,  262,  259,  264,  265,  262,  257,  258,  257,
  258,  257,  258,   -1,  264,  265,  264,  265,  264,  265,
};
}
final static short YYFINAL=6;
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
"declaracion : ARRAY arreglo ';'",
"declaracion : ARRAY arreglo error",
"declaracion : FLOAT ';' error",
"variables : IDENTIFICADOR",
"variables : variables ',' IDENTIFICADOR",
"variables : error",
"arreglo : IDENTIFICADOR '[' expresion ']'",
"arreglo : error",
"seleccion : IF '(' condicion ')' THEN '{' sentencias '}'",
"seleccion : IF '(' condicion ')' THEN '{' sentencias '}' ELSE '{' sentencias '}'",
"seleccion : IF '(' condicion ')' THEN '{' sentencias '}' ELSE",
"seleccion : IF '(' condicion ')' '{' sentencias '}' ELSE",
"seleccion : IF '(' condicion ')' THEN",
"seleccion : error",
"condicion : argumento comparador argumento",
"condicion : error",
"bucle : WHILE '(' condicion ')' DO bloque",
"bucle : WHILE '(' condicion ')' bloque",
"bucle : WHILE '(' ';' bloque",
"impresion : PRINT '(' CADENA ')' ';'",
"impresion : PRINT '(' CADENA ')' error",
"impresion : PRINT '(' ';' error",
"impresion : PRINT ';' error",
"asignacion : IDENTIFICADOR ASIG expresion",
"asignacion : IDENTIFICADOR '[' expresion ']' ASIG expresion",
"comparador : '<'",
"comparador : '>'",
"comparador : '='",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : error",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' argumento",
"termino : termino '/' argumento",
"termino : argumento",
"termino : error",
"num : '-' NUMERO",
"num : NUMERO",
"argumento : IDENTIFICADOR",
"argumento : num",
"argumento : IDENTIFICADOR '[' expresion ']'",
};

//#line 106 "gramatica.y"

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
//#line 369 "Parser.java"
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
//#line 14 "gramatica.y"
{

logSintactico.addLogger("El programa finalizo correctamente");
}
break;
case 7:
//#line 29 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Bucle");}
break;
case 10:
//#line 31 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion");}
break;
case 13:
//#line 33 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sentencia no permitida");}
break;
case 14:
//#line 36 "gramatica.y"
{logSintactico.addLogger("En linea "+lexico.getLineas()+": declaracion de un FLOAT");}
break;
case 15:
//#line 37 "gramatica.y"
{logSintactico.addLogger("En linea "+lexico.getLineas()+": declaracion de un ARRAY");}
break;
case 16:
//#line 38 "gramatica.y"
{logSintactico.addLogger("Error sintactico en linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 17:
//#line 39 "gramatica.y"
{logSintactico.addLogger("Error sintactico en linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 20:
//#line 44 "gramatica.y"
{logSintactico.addLogger("ERROR en linea"+lexico.getLineas()+": declaracion de variables");}
break;
case 22:
//#line 48 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en el arreglo");}
break;
case 24:
//#line 52 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion ifelse");}
break;
case 25:
//#line 53 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": Seleccion faltan las LLAVES");}
break;
case 26:
//#line 54 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": Seleccion falta setencia THEN ");}
break;
case 27:
//#line 55 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": Seleccion faltan las LLAVES");}
break;
case 28:
//#line 56 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en linea "+lexico.getLineas()+": seleccion no valida");}
break;
case 30:
//#line 60 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en linea "+lexico.getLineas()+": condicion no valida");}
break;
case 32:
//#line 64 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba el DO");}
break;
case 33:
//#line 65 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 34:
//#line 68 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Salida por pantalla");}
break;
case 35:
//#line 69 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 36:
//#line 70 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 37:
//#line 71 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
break;
case 38:
//#line 74 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Asignacion");}
break;
case 39:
//#line 75 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Asignacion");}
break;
case 45:
//#line 83 "gramatica.y"
{logSintactico.addLogger("ERROR en linea "+lexico.getLineas()+": sintactico en la comparacion");}
break;
case 46:
//#line 86 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 47:
//#line 87 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 52:
//#line 94 "gramatica.y"
{logSintactico.addLogger("ERROR en linea"+lexico.getLineas()+": no es posible resolver la expresion");}
break;
case 53:
//#line 97 "gramatica.y"
{putNegativo(val_peek(0).sval);}
break;
//#line 637 "Parser.java"
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
