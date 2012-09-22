#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 1 "gramatica.y"

package utils;
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;

#line 13 "y.tab.c"
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
    0,    1,    1,    3,    3,    2,    2,    2,    2,    2,
    4,    4,    4,    9,    9,   10,    8,    8,   12,   14,
   14,   14,   14,   14,    5,    6,    6,    6,    6,    7,
    7,   11,   11,   11,   15,   15,   15,   16,   16,   13,
   13,   13,
};
short yylen[] = {                                         2,
    1,    1,    3,    1,    2,    1,    1,    1,    2,    1,
    2,    3,    2,    2,    3,    4,    6,    8,    3,    1,
    1,    1,    1,    1,    6,    5,    5,    4,    2,    3,
    6,    3,    3,    1,    3,    3,    1,    2,    1,    1,
    1,    4,
};
short yydefred[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    2,    6,    7,    8,    0,   10,    0,    0,    0,    0,
    0,    0,   29,    0,    0,    0,    0,    4,    0,    9,
    0,   39,    0,    0,   37,    0,   41,    0,   14,    0,
    0,    0,    0,    0,    0,   12,    3,    5,    0,   38,
    0,    0,    0,    0,    0,   15,    0,   24,   23,   20,
   21,   22,    0,    0,   28,    0,    0,    0,    0,    0,
   35,   36,    0,    0,   19,    0,   26,   27,   16,   42,
    0,    0,   25,    0,   18,
};
short yydgoto[] = {                                       9,
   10,   11,   29,   12,   13,   14,   15,   16,   20,   26,
   34,   41,   35,   63,   36,   37,
};
short yysindex[] = {                                   -115,
  -85, -247,   -3,   16,  -29, -216, -247, -180,    0,    0,
    0,    0,    0,    0,   -6,    0,  -43,  -43,   -2,   14,
  -43,  -43,    0, -199,  -23,   15,   14,    0, -105,    0,
  -10,    0, -198,    2,    0,  -35,    0,  -21,    0, -170,
   47,  -57,   48,   -7,  -43,    0,    0,    0,  -43,    0,
  -43,  -43,  -43,  -43, -179,    0, -168,    0,    0,    0,
    0,    0,  -43, -173,    0,  -45,  -18,  -17,  -35,  -35,
    0,    0,  -43, -115,    0, -115,    0,    0,    0,    0,
    2, -166,    0, -115,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   13,
    0,    0,    0,    0,    0,    0,   23,    0,    0,    0,
    1,    0,    0,   -6,    0,  -28,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -27,  -24,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,   33,    0,    0,    0,
};
short yygindex[] = {                                      0,
  -25,   21,    0,    0,    0,    0,    0,    0,   89,    0,
   -9,   75,   17,    0,    3,    0,
};
#define YYTABLESIZE 299
short yytable[] = {                                      33,
   40,   33,   60,   62,   61,   18,   53,    8,   38,   19,
   24,   54,   11,   77,   34,   32,   34,   32,   33,   47,
   33,   51,   13,   52,   51,   51,   52,   52,   28,   23,
   34,   32,   17,   66,   33,   67,   21,   42,   42,   68,
   25,   40,   40,   40,   51,   40,   52,   40,   82,   48,
   83,   65,   30,   69,   70,   22,   39,   40,   85,   40,
   40,   40,   40,   81,   34,   32,   44,   45,   33,   71,
   72,   55,   50,   46,   79,   80,    1,    2,    3,   75,
   49,    4,   78,    5,    6,    7,   56,   57,   64,   76,
   73,   74,   31,   40,   84,   27,   43,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   40,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   11,    0,    0,
    0,    1,    2,    3,    0,    0,    4,   13,    5,    6,
    7,    1,    2,    3,    0,    0,    4,   17,    5,    6,
    7,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   17,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   59,   31,    0,   31,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   32,    0,   32,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,   40,   40,
    0,   40,   40,    0,   40,   40,   40,   40,   40,   11,
   11,   11,    0,   11,   11,    0,   11,   11,   11,   13,
   13,   13,    0,   13,   13,    0,   13,   13,   13,   17,
   17,   17,    0,    0,   17,    0,   17,   17,   17,
};
short yycheck[] = {                                      45,
    0,   45,   60,   61,   62,   91,   42,  123,   18,  257,
   40,   47,    0,   59,   43,   43,   45,   45,   43,  125,
   45,   43,    0,   45,   43,   43,   45,   45,    8,   59,
   59,   59,    0,   41,   59,   45,   40,   21,   22,   49,
  257,   41,   42,   43,   43,   45,   45,   47,   74,   29,
   76,   59,   59,   51,   52,   40,   59,   44,   84,   59,
   60,   61,   62,   73,   93,   93,  266,   91,   93,   53,
   54,   93,  271,   59,   93,   93,  257,  258,  259,   63,
   91,  262,   66,  264,  265,  266,  257,   41,   41,  263,
  270,  260,   59,   93,  261,    7,   22,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,
   -1,  257,  258,  259,   -1,   -1,  262,  125,  264,  265,
  266,  257,  258,  259,   -1,   -1,  262,  125,  264,  265,
  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  270,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,
  268,  257,   -1,  257,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  271,   -1,  271,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   -1,  264,  265,  266,  267,  268,  257,
  258,  259,   -1,  261,  262,   -1,  264,  265,  266,  257,
  258,  259,   -1,  261,  262,   -1,  264,  265,  266,  257,
  258,  259,   -1,   -1,  262,   -1,  264,  265,  266,
};
#define YYFINAL 9
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
"bucle : WHILE '(' condicion ')' DO bloque",
"impresion : PRINT '(' CADENA ')' ';'",
"impresion : PRINT '(' CADENA ')' argumento",
"impresion : PRINT '(' CADENA ';'",
"impresion : PRINT ';'",
"asignacion : IDENTIFICADOR ASIG expresion",
"asignacion : IDENTIFICADOR '[' expresion ']' ASIG expresion",
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
#line 92 "gramatica.y"

  
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
#line 280 "y.tab.c"
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
case 17:
#line 46 "gramatica.y"
{System.out.println("Seleccion en linea "+lexico.getLineas());}
break;
case 18:
#line 47 "gramatica.y"
{System.out.println("Seleccion en IFELSE linea "+lexico.getLineas());}
break;
case 19:
#line 50 "gramatica.y"
{System.out.println("Esto es un comparador");}
break;
case 25:
#line 60 "gramatica.y"
{System.out.println("Bucle en linea "+lexico.getLineas());}
break;
case 26:
#line 63 "gramatica.y"
{System.out.println("Salida por pantalla en linea "+lexico.getLineas());}
break;
case 27:
#line 64 "gramatica.y"
{System.out.println("ERROR en salida por pantalla un ; en linea "+ lexico.getLineas());}
break;
case 28:
#line 65 "gramatica.y"
{System.out.println("ERROR en salida por pantalla en linea "+lexico.getLineas()+" se esperaba un )");}
break;
case 29:
#line 66 "gramatica.y"
{System.out.println("ERROR en salida por pantalla en linea "+lexico.getLineas() + " se esperaba una CADENA");}
break;
case 30:
#line 69 "gramatica.y"
{System.out.println("Asignacion en linea "+lexico.getLineas());}
break;
case 31:
#line 70 "gramatica.y"
{System.out.println("Asignacion");}
break;
case 32:
#line 73 "gramatica.y"
{System.out.println("Expresion en linea "+lexico.getLineas());}
break;
case 33:
#line 74 "gramatica.y"
{System.out.println("Expresion en linea "+lexico.getLineas());}
break;
case 38:
#line 83 "gramatica.y"
{putNegativo(yyvsp[0].sval);}
break;
#line 472 "y.tab.c"
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
