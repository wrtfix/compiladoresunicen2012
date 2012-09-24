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
    0,    1,    1,    3,    3,    2,    2,    2,    2,    2,
    2,    4,    4,    9,    9,    9,   10,   10,    8,    8,
    8,   12,   12,    5,    5,    5,    6,    6,    6,    6,
    7,    7,   14,   14,   14,   14,   14,   14,   11,   11,
   11,   15,   15,   15,   15,   16,   16,   13,   13,   13,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    2,    1,    1,    1,    2,    1,
    2,    2,    3,    2,    3,    1,    4,    1,    8,   12,
    1,    3,    1,    6,    5,    4,    5,    5,    4,    3,
    3,    6,    1,    1,    1,    1,    1,    1,    3,    3,
    1,    3,    3,    1,    1,    2,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
   21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    2,    6,    7,    8,    0,   10,    0,    0,   16,
    0,    0,    0,    0,    0,    0,   18,    0,    0,    4,
    0,   11,    9,   45,    0,   47,    0,    0,   44,    0,
   49,    0,   14,    0,   23,    0,    0,    0,    0,   30,
    0,    0,    0,   13,    3,    5,    0,   46,    0,    0,
    0,    0,    0,   15,    0,   38,   37,   36,   33,   34,
   35,    0,   26,    0,    0,   29,    0,    0,    0,    0,
   42,   43,    0,    0,   22,    0,   25,   28,   27,   17,
   50,    0,    0,   24,    0,    0,    0,    0,    0,   20,
};
final static short yydgoto[] = {                         10,
   11,   12,   31,   13,   14,   15,   16,   17,   22,   29,
   38,   46,   39,   72,   40,   41,
};
final static short yysindex[] = {                        -9,
    0,  -79, -185,  -25,  -16,    4, -183,   51, -226,    0,
    0,    0,    0,    0,    0,  -22,    0,  -38,  -38,    0,
  -13,    5,  -36,  -45, -204,  -34,    0,  -33,   -4,    0,
   21,    0,    0,    0,  -29,    0, -203,   24,    0,   17,
    0,  -10,    0, -182,    0,   37,  -43,   -9,   40,    0,
   46, -168,  -38,    0,    0,    0,  -38,    0,  -38,  -38,
  -35,  -35, -181,    0, -167,    0,    0,    0,    0,    0,
    0,  -35,    0,  -19,  -54,    0,   -7,   -2,   17,   17,
    0,    0,  -38,  -31,    0,   -9,    0,    0,    0,    0,
    0,   24,   51,    0,   31, -165,  -26,   51,   41,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -39,    0,    0,   39,    0,  -17,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -14,  -11,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,    0,    0,    0,   11,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -21,    8,  -32,    0,    0,    0,    0,    0,    0,    0,
   -6,   75,   33,    0,   25,    0,
};
final static int YYTABLESIZE=316;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         37,
   12,   48,   48,   48,   89,   48,   37,   48,   37,   37,
   19,   19,   42,   48,   23,   30,   69,   71,   70,   48,
   48,   48,   48,   24,   52,   41,   73,   41,   39,   32,
   39,   40,   59,   40,   60,   59,   33,   60,   56,    9,
   59,   41,   60,   26,   39,   43,   77,   40,   44,    9,
   78,   50,   87,   48,   54,   47,   47,   53,   61,   12,
   95,   57,   25,   62,   94,   99,   59,   58,   60,   19,
   20,   21,   27,   28,   64,   41,   92,   65,   39,    9,
   74,   40,   63,   79,   80,   90,   75,   76,   83,    9,
   91,   93,   84,   81,   82,   97,   98,   31,   49,    9,
   30,   32,   56,    8,   85,   30,   56,    0,    0,    9,
    0,    0,    0,    8,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   12,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   55,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   96,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  100,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   88,    0,    0,    0,    0,    0,    0,    0,    0,
   45,   35,   66,    0,    0,    0,   48,   34,   35,   45,
   35,   35,    0,   67,   68,   36,    0,   48,   48,    0,
    0,   51,   36,    0,   36,   36,    1,    2,    3,    4,
    0,    0,    5,   86,    6,    7,    1,    2,    3,    4,
    0,    0,    5,    0,    6,    7,   12,   12,   12,   12,
    0,    0,   12,    0,   12,   12,   19,   19,   19,   19,
    0,    0,   19,    0,   19,   19,    1,    2,    3,    4,
    0,    0,    5,    0,    6,    7,    1,    2,    3,    4,
    0,    0,    5,    0,    6,    7,    1,    2,    3,    4,
    0,    0,    5,    0,    6,    7,    1,    2,    3,    4,
    0,    0,    5,    0,    6,    7,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   41,   42,   43,   59,   45,   45,   47,   45,   45,
    0,   91,   19,   59,   40,    8,   60,   61,   62,   59,
   60,   61,   62,   40,   59,   43,   48,   45,   43,  256,
   45,   43,   43,   45,   45,   43,   59,   45,   31,   59,
   43,   59,   45,   40,   59,   59,   53,   59,   44,   59,
   57,  256,   74,   93,   59,   23,   24,   91,   42,   59,
   93,   91,   59,   47,   86,   98,   43,  271,   45,   59,
  256,  257,  256,  257,  257,   93,   83,   41,   93,   59,
   41,   93,   93,   59,   60,   93,   41,  256,  270,   59,
   93,  123,  260,   61,   62,  261,  123,   59,   24,   59,
   93,   59,   95,  123,   72,   98,   99,   -1,   -1,   59,
   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  256,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  256,  257,  256,   -1,   -1,   -1,  256,  256,  257,  256,
  257,  257,   -1,  267,  268,  271,   -1,  267,  268,   -1,
   -1,  266,  271,   -1,  271,  271,  256,  257,  258,  259,
   -1,   -1,  262,  263,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  256,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,
};
}
final static short YYFINAL=10;
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
"programa : bloque",
"bloque : sentencia",
"bloque : '{' sentencias '}'",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : declaracion",
"sentencia : bucle",
"sentencia : impresion",
"sentencia : asignacion ';'",
"sentencia : seleccion",
"sentencia : ';' error",
"declaracion : FLOAT variables",
"declaracion : ARRAY arreglo ';'",
"variables : IDENTIFICADOR ';'",
"variables : variables ',' IDENTIFICADOR",
"variables : error",
"arreglo : IDENTIFICADOR '[' expresion ']'",
"arreglo : error",
"seleccion : IF '(' condicion ')' THEN '{' sentencias '}'",
"seleccion : IF '(' condicion ')' THEN '{' sentencias '}' ELSE '{' sentencias '}'",
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

//#line 98 "gramatica.y"

  
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
//#line 358 "Parser.java"
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
{System.out.println("El programa finalizo correctamente");}
break;
case 11:
//#line 30 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": sentencia no permitida");}
break;
case 16:
//#line 39 "gramatica.y"
{System.out.println("ERROR en linea"+lexico.getLineas()+": declaracion de variables");}
break;
case 18:
//#line 43 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": sintactico en el arreglo");}
break;
case 19:
//#line 46 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": Seleccion");}
break;
case 20:
//#line 47 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": Seleccion ifelse");}
break;
case 21:
//#line 48 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": sintactico en la seleccion");}
break;
case 23:
//#line 52 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": sintactico en la seleccion");}
break;
case 24:
//#line 55 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": Bucle");}
break;
case 25:
//#line 56 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": se esperaba el DO");}
break;
case 26:
//#line 57 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 27:
//#line 60 "gramatica.y"
{System.out.println("Salida por pantalla en linea "+lexico.getLineas());}
break;
case 28:
//#line 61 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 29:
//#line 62 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 30:
//#line 63 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
break;
case 31:
//#line 66 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": Asignacion");}
break;
case 32:
//#line 67 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": Asignacion");}
break;
case 38:
//#line 75 "gramatica.y"
{System.out.println("ERROR en linea "+lexico.getLineas()+": sintactico en la comparacion");}
break;
case 39:
//#line 78 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 40:
//#line 79 "gramatica.y"
{System.out.println("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 45:
//#line 86 "gramatica.y"
{System.out.println("ERROR en linea"+lexico.getLineas()+": no es posible resolver la expresion");}
break;
case 46:
//#line 89 "gramatica.y"
{putNegativo(val_peek(0).sval);}
break;
//#line 595 "Parser.java"
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
