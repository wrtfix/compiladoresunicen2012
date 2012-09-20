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
import java.util.Hashtable;
import java.util.Vector;
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
    0,    1,    1,    2,    2,    2,    2,    2,    3,    3,
    3,    8,    8,    9,    7,    7,   11,   14,   14,   14,
   14,   14,   12,   12,    4,    5,    6,    6,   10,   10,
   10,   15,   15,   15,   13,   13,   13,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    2,    1,    2,    3,
    2,    2,    3,    4,    6,    8,    3,    1,    1,    1,
    1,    1,    1,    3,    6,    5,    3,    6,    3,    3,
    1,    3,    3,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    2,
    4,    5,    6,    0,    8,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    7,    0,   36,    0,
   34,    0,    0,   12,    0,    0,    0,    0,    0,    0,
   10,    0,    0,    0,    0,    0,    0,   13,    0,   22,
   21,   18,   19,   20,    0,    0,    0,    0,    0,    0,
    0,   32,   33,    0,    0,   17,    0,   26,   14,   37,
    0,    0,   23,    0,   25,    0,    0,   24,   16,
};
final static short yydgoto[] = {                          8,
    9,   73,   11,   12,   13,   14,   15,   19,   24,   30,
   36,   74,   31,   55,   32,
};
final static short yysindex[] = {                      -170,
  -91, -212,    8,   14,   15, -206, -212,    0, -170,    0,
    0,    0,    0,    3,    0, -218, -218,    5,   16, -218,
 -218, -214,  -32,   10,   16,    0,    0,  -30,    0,    4,
    0,   -1,  -15,    0, -194,   29,  -48,   30,   31, -218,
    0, -218, -218, -218, -218, -218, -197,    0, -186,    0,
    0,    0,    0,    0, -218, -188,   17,   -8,   -7,   -1,
   -1,    0,    0, -218, -114,    0, -114,    0,    0,    0,
    4, -170,    0, -181,    0,  -99, -114,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,   81,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,   11,    0,    0,  -37,    0,    3,
    0,  -28,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -25,
  -16,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   23,    0,    0,   21,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   18,    7,    0,    0,    0,    0,    0,   77,    0,    2,
   70,  -27,   12,    0,  -41,
};
final static int YYTABLESIZE=287;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         17,
    9,   60,   61,   35,   35,   35,   10,   35,   72,   35,
   11,   52,   54,   53,   31,   26,   31,   29,   33,   29,
   15,   35,   35,   35,   35,   78,   30,   43,   30,   44,
   31,   37,   37,   29,   43,   43,   44,   44,   28,   75,
   45,   58,   30,   59,   18,   46,   43,   20,   44,   79,
   23,   39,   29,   21,   22,   35,   62,   63,   40,   35,
   42,   27,   48,   34,   31,   71,   66,   29,   41,   49,
   56,   57,   64,   65,   67,   68,   30,   47,   10,   77,
    1,   28,   26,   25,   69,   70,    1,    2,    3,   76,
   38,    4,    0,    5,    6,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   11,    0,    0,    0,    0,
    0,    0,    1,    2,    3,   15,    0,    4,    0,    5,
    6,    7,    0,    0,    0,    0,    0,    1,    2,    3,
    0,    0,    4,    0,    5,    6,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   16,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   50,   51,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   35,
   35,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    9,    9,    9,
    0,    9,    9,    0,    9,    9,    9,   11,   11,   11,
    0,   11,   11,    0,   11,   11,   11,   15,   15,   15,
    0,    0,   15,    0,   15,   15,   15,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         91,
    0,   43,   44,   41,   42,   43,    0,   45,  123,   47,
    0,   60,   61,   62,   43,    9,   45,   43,   17,   45,
    0,   59,   60,   61,   62,  125,   43,   43,   45,   45,
   59,   20,   21,   59,   43,   43,   45,   45,  257,   67,
   42,   40,   59,   42,  257,   47,   43,   40,   45,   77,
  257,  266,  271,   40,   40,   93,   45,   46,   91,   44,
   91,   59,  257,   59,   93,   64,   55,   93,   59,   41,
   41,   41,  270,  260,  263,   59,   93,   93,   72,  261,
    0,   59,   76,    7,   93,   93,  257,  258,  259,   72,
   21,  262,   -1,  264,  265,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  125,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  270,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,  268,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,
  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   -1,  264,  265,  266,  257,  258,  259,
   -1,  261,  262,   -1,  264,  265,  266,  257,  258,  259,
   -1,   -1,  262,   -1,  264,  265,  266,
};
}
final static short YYFINAL=8;
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
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : declaracion",
"sentencia : bucle",
"sentencia : impresion",
"sentencia : asignacion ';'",
"sentencia : seleccion",
"declaracion : FLOAT variables",
"declaracion : ARRAY arreglo ';'",
"declaracion : CADENA variables",
"variables : IDENTIFICADOR ';'",
"variables : variables ',' IDENTIFICADOR",
"arreglo : IDENTIFICADOR '[' expresion ']'",
"seleccion : IF '(' condicion ')' THEN bloque",
"seleccion : IF '(' condicion ')' THEN bloque ELSE bloque",
"condicion : argumento comparador argumento",
"comparador : '<'",
"comparador : '>'",
"comparador : '='",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"bloque : sentencia",
"bloque : '{' sentencias '}'",
"bucle : WHILE '(' condicion ')' DO bloque",
"impresion : PRINT '(' CADENA ')' ';'",
"asignacion : IDENTIFICADOR ASIG expresion",
"asignacion : IDENTIFICADOR '[' expresion ']' ASIG expresion",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' argumento",
"termino : termino '/' argumento",
"termino : argumento",
"argumento : IDENTIFICADOR",
"argumento : NUMERO",
"argumento : IDENTIFICADOR '[' expresion ']'",
};

//#line 89 "gramatica.y"
  
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
//#line 320 "Parser.java"
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
//#line 18 "gramatica.y"
{System.out.println("Terminacion del programa");}
break;
case 9:
//#line 32 "gramatica.y"
{System.out.println("Variable tipo float");}
break;
case 10:
//#line 33 "gramatica.y"
{System.out.println("Variable tipo arreglo");}
break;
case 11:
//#line 34 "gramatica.y"
{System.out.println("Variable tipo cadena");}
break;
case 12:
//#line 38 "gramatica.y"
{System.out.println("Esto es una variable ");}
break;
case 15:
//#line 45 "gramatica.y"
{System.out.println("Sentecia IF");}
break;
case 16:
//#line 46 "gramatica.y"
{System.out.println("Sentecia IF ELSE");}
break;
case 17:
//#line 49 "gramatica.y"
{System.out.println("Esto es un comparador");}
break;
case 25:
//#line 63 "gramatica.y"
{System.out.println("Bucle while");}
break;
case 26:
//#line 66 "gramatica.y"
{System.out.println("Salida por pantalla");}
break;
case 27:
//#line 69 "gramatica.y"
{System.out.println("Asignacion");}
break;
case 28:
//#line 70 "gramatica.y"
{System.out.println("Asignacion");}
break;
case 29:
//#line 73 "gramatica.y"
{System.out.println("Esto es una expresion");}
break;
case 30:
//#line 74 "gramatica.y"
{System.out.println("Esto es una expresion");}
break;
case 32:
//#line 78 "gramatica.y"
{System.out.println("Esto es un termino");}
break;
case 33:
//#line 79 "gramatica.y"
{System.out.println("Esto es un termino");}
break;
case 34:
//#line 80 "gramatica.y"
{System.out.println("Esto es un argumento");}
break;
//#line 537 "Parser.java"
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
