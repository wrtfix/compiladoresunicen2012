#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 1 "gramatica.y"


package utils;
import java.io.IOException;

#line 12 "y.tab.c"
#define IDENTIFICADOR 257
#define FLOAT 258
#define IF 259
#define THEN 260
#define ELSE 261
#define WHILE 262
#define DO 263
#define PRINT 264
#define ARRAY 265
#define CADENA 266
#define MAYOR_IGUAL 267
#define MENOR_IGUAL 268
#define DISTINTO 269
#define ASIG 270
#define NUMERO 271
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    2,    2,    1,    1,    3,    6,    3,    3,    9,
    3,    3,    3,    4,    4,    4,    4,   11,   11,   11,
   12,   12,    8,    8,    8,    8,    8,    8,   14,   14,
    5,    5,    5,    7,    7,    7,    7,   10,   10,   16,
   16,   16,   16,   16,   16,   13,   13,   13,   17,   17,
   17,   17,   18,   18,   15,   15,   15,
};
short yylen[] = {                                         2,
    1,    1,    3,    1,    2,    1,    0,    2,    1,    0,
    2,    2,    2,    3,    3,    3,    3,    1,    3,    1,
    4,    1,    8,   12,    9,    8,    5,    1,    3,    1,
    6,    5,    4,    5,    5,    4,    3,    3,    6,    1,
    1,    1,    1,    1,    1,    3,    3,    1,    3,    3,
    1,    1,    2,    1,    1,    1,    4,
};
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                       6,
    7,   79,    8,    9,   28,   10,   11,   31,   12,   13,
   19,   24,   37,   66,   38,   88,   39,   40,
};
short yysindex[] = {                                     35,
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
short yyrindex[] = {                                   -174,
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
short yygindex[] = {                                      0,
  -24,  -14,   16,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,   73,   17,    0,   47,    0,
};
#define YYTABLESIZE 300
short yytable[] = {                                      36,
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
short yycheck[] = {                                      45,
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
#define YYFINAL 6
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 271
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,0,
"';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'['",0,"']'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,
"'}'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,"IDENTIFICADOR","FLOAT","IF","THEN","ELSE","WHILE",
"DO","PRINT","ARRAY","CADENA","MAYOR_IGUAL","MENOR_IGUAL","DISTINTO","ASIG",
"NUMERO",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 106 "gramatica.y"

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
#line 305 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 14 "gramatica.y"
{

logSintactico.addLogger("El programa finalizo correctamente");
}
break;
case 7:
#line 29 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Bucle");}
break;
case 10:
#line 31 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": Seleccion");}
break;
case 13:
#line 33 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en linea "+lexico.getLineas()+": sentencia no permitida");}
break;
case 14:
#line 36 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un FLOAT");}
break;
case 15:
#line 37 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": declaracion de un ARRAY");}
break;
case 16:
#line 38 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 17:
#line 39 "gramatica.y"
{logSintactico.addLogger("Error sintactico en la linea "+lexico.getLineas()+": declaracion de variables");}
break;
case 20:
#line 44 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea"+lexico.getLineas()+": declaracion de variables");}
break;
case 22:
#line 48 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": sintactico en el arreglo");}
break;
case 24:
#line 52 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": seleccion ifelse");}
break;
case 25:
#line 53 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion faltan las LLAVES");}
break;
case 26:
#line 54 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion falta setencia THEN ");}
break;
case 27:
#line 55 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion faltan las LLAVES");}
break;
case 28:
#line 56 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": seleccion no valida");}
break;
case 30:
#line 60 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": condicion no valida");}
break;
case 32:
#line 64 "gramatica.y"
{logSintactico.addLogger("ERROR sintactica en la linea "+lexico.getLineas()+": se esperaba el DO");}
break;
case 33:
#line 65 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una condicion");}
break;
case 34:
#line 68 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+":salida por pantalla");}
break;
case 35:
#line 69 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba un punto y coma");}
break;
case 36:
#line 70 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una cadena");}
break;
case 37:
#line 71 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": se esperaba una ('cadena')");}
break;
case 38:
#line 74 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
break;
case 39:
#line 75 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": asignacion");}
break;
case 45:
#line 83 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea "+lexico.getLineas()+": no es posible resolver la comparacion");}
break;
case 46:
#line 86 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 47:
#line 87 "gramatica.y"
{logSintactico.addLogger("Linea "+lexico.getLineas()+": se encontro una expresion");}
break;
case 52:
#line 94 "gramatica.y"
{logSintactico.addLogger("ERROR sintactico en la linea"+lexico.getLineas()+": no es posible resolver la expresion");}
break;
case 53:
#line 97 "gramatica.y"
{putNegativo(yyvsp[0].sval);}
break;
#line 564 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
