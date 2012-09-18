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

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;
//#line 22 "Parser.java"

public class Parser {

    private AnalizadorLexico lexico;
    boolean yydebug;        //do I want debug output?
    int yynerrs;            //number of errors so far
    int yyerrflag;          //was there an error?
    int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
    void debug(String msg) {
        if (yydebug) {
            System.out.println(msg);
        }
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
    final void state_push(int state) {
        try {
            stateptr++;
            statestk[stateptr] = state;
        } catch (ArrayIndexOutOfBoundsException e) {
            int oldsize = statestk.length;
            int newsize = oldsize * 2;
            int[] newstack = new int[newsize];
            System.arraycopy(statestk, 0, newstack, 0, oldsize);
            statestk = newstack;
            statestk[stateptr] = state;
        }
    }

    final int state_pop() {
        return statestk[stateptr--];
    }

    final void state_drop(int cnt) {
        stateptr -= cnt;
    }

    final int state_peek(int relative) {
        return statestk[stateptr - relative];
    }
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
    final boolean init_stacks() {
        stateptr = -1;
        val_init();
        return true;
    }
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
    void dump_stacks(int count) {
        int i;
        System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
        for (i = 0; i < count; i++) {
            System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
        }
        System.out.println("======================");
    }
//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java
    String yytext;//user variable to return contextual strings
    ParserVal yyval; //used to return semantic vals from action routines
    ParserVal yylval;//the 'lval' (result) I got from yylex()
    ParserVal valstk[];
    int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
    void val_init() {
        valstk = new ParserVal[YYSTACKSIZE];
        yyval = new ParserVal();
        yylval = new ParserVal();
        valptr = -1;
    }

    void val_push(ParserVal val) {
        if (valptr >= YYSTACKSIZE) {
            return;
        }
        valstk[++valptr] = val;
    }

    ParserVal val_pop() {
        if (valptr < 0) {
            return new ParserVal();
        }
        return valstk[valptr--];
    }

    void val_drop(int cnt) {
        int ptr;
        ptr = valptr - cnt;
        if (ptr < 0) {
            return;
        }
        valptr = ptr;
    }

    ParserVal val_peek(int relative) {
        int ptr;
        ptr = valptr - relative;
        if (ptr < 0) {
            return new ParserVal();
        }
        return valstk[ptr];
    }

    final ParserVal dup_yyval(ParserVal val) {
        ParserVal dup = new ParserVal();
        dup.ival = val.ival;
        dup.dval = val.dval;
        dup.sval = val.sval;
        dup.obj = val.obj;
        return dup;
    }
//#### end semantic value section ####
    public final static short IDENTIFICADOR = 257;
    public final static short FLOAT = 258;
    public final static short IF = 259;
    public final static short THEN = 260;
    public final static short ELSE = 261;
    public final static short WHILE = 262;
    public final static short DO = 263;
    public final static short PRINT = 264;
    public final static short ARRAY = 265;
    public final static short CADENA = 266;
    public final static short MAYOR_IGUAL = 267;
    public final static short MENOR_IGUAL = 268;
    public final static short DISTINTO = 269;
    public final static short ASIG = 270;
    public final static short YYERRCODE = 256;
    final static short yylhs[] = {-1,
        0, 2, 2, 1, 1, 4, 4, 4, 7, 7,
        7, 3, 3, 3, 3, 3, 8, 8, 5, 13,
        13, 14, 14, 14, 14, 14, 14, 15, 9, 16,
        9, 10, 11, 17, 17, 17, 6, 6, 12, 12,};
    final static short yylen[] = {2,
        1, 1, 3, 1, 2, 1, 1, 1, 1, 1,
        4, 1, 1, 1, 1, 1, 3, 3, 4, 1,
        3, 1, 1, 1, 1, 1, 1, 3, 6, 0,
        8, 6, 5, 3, 3, 1, 3, 3, 4, 3,};
    final static short yydefred[] = {0,
        0, 0, 0, 0, 0, 0, 0, 0, 4, 12,
        13, 14, 15, 16, 0, 20, 0, 0, 0, 0,
        0, 0, 5, 0, 10, 0, 40, 17, 0, 0,
        0, 0, 0, 0, 18, 0, 0, 0, 39, 21,
        26, 25, 27, 22, 23, 24, 0, 0, 0, 0,
        0, 8, 0, 6, 0, 36, 0, 0, 28, 0,
        0, 33, 19, 11, 0, 0, 0, 29, 2, 0,
        32, 34, 35, 0, 0, 3, 31,};
    final static short yydgoto[] = {7,
        8, 68, 69, 53, 22, 26, 30, 10, 11, 12,
        13, 14, 17, 47, 31, 70, 57,};
    final static short yysindex[] = {-225,
        -252, -235, -14, 7, 11, -216, 0, -225, 0, 0,
        0, 0, 0, 0, -222, 0, -32, -222, -222, -221,
        -39, -6, 0, -37, 0, -36, 0, 0, -202, -58,
        18, 24, 25, -222, 0, -208, -222, -222, 0, 0,
        0, 0, 0, 0, 0, 0, -222, -193, -195, 10,
        -23, 0, -22, 0, -24, 0, -27, -27, 0, -123,
        -123, 0, 0, 0, -222, -222, -225, 0, 0, -188,
        0, 0, 0, -114, -123, 0, 0,};
    final static short yyrindex[] = {0,
        0, 0, 0, 0, 0, 0, 0, 74, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, -18, 0, -35, -29, 0, -185,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0,};
    final static short yygindex[] = {0,
        13, -44, 5, 0, 41, 42, -9, 0, 0, 0,
        0, 0, 0, 0, 62, 0, 44,};
    final static int YYTABLESIZE = 270;
    static short yytable[];

    static {
        yytable();
    }

    static void yytable() {
        yytable = new short[]{67,
            9, 44, 46, 45, 9, 27, 37, 37, 38, 37,
            76, 29, 23, 38, 65, 38, 71, 15, 37, 66,
            38, 16, 39, 37, 51, 18, 28, 56, 56, 38,
            77, 1, 2, 3, 24, 25, 4, 59, 5, 6,
            21, 9, 9, 9, 33, 9, 19, 9, 21, 52,
            20, 34, 35, 36, 40, 72, 73, 37, 48, 9,
            9, 9, 9, 38, 49, 50, 60, 61, 62, 63,
            64, 9, 75, 1, 7, 30, 54, 55, 23, 74,
            32, 58, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 9, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 9, 0, 0, 0, 0,
            0, 0, 0, 1, 2, 3, 0, 0, 4, 0,
            5, 6, 1, 2, 3, 0, 0, 4, 0, 5,
            6, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 41, 42,
            43, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 9, 9, 9,
            0, 0, 9, 0, 9, 9, 0, 9, 9, 9,};
    }
    static short yycheck[];

    static {
        yycheck();
    }

    static void yycheck() {
        yycheck = new short[]{123,
            0, 60, 61, 62, 0, 15, 43, 43, 45, 45,
            125, 44, 8, 43, 42, 45, 61, 270, 43, 47,
            45, 257, 59, 59, 34, 40, 59, 37, 38, 59,
            75, 257, 258, 259, 257, 258, 262, 47, 264, 265,
            257, 41, 42, 43, 266, 45, 40, 47, 257, 258,
            40, 91, 59, 91, 257, 65, 66, 93, 41, 59,
            60, 61, 62, 93, 41, 41, 260, 263, 59, 93,
            93, 67, 261, 0, 93, 261, 36, 36, 74, 67,
            19, 38, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, 93, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 125, -1, -1, -1, -1,
            -1, -1, -1, 257, 258, 259, -1, -1, 262, -1,
            264, 265, 257, 258, 259, -1, -1, 262, -1, 264,
            265, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, 267, 268,
            269, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, 257, 258, 259,
            -1, -1, 262, -1, 264, 265, -1, 267, 268, 269,};
    }
    final static short YYFINAL = 7;
    final static short YYMAXTOKEN = 270;
    final static String yyname[] = {
        "end-of-file", null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, "'('", "')'", "'*'", "'+'", "','",
        "'-'", null, "'/'", null, null, null, null, null, null, null, null, null, null, null, "';'",
        "'<'", "'='", "'>'", null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        "'['", null, "']'", null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, "'{'", null, "'}'", null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, "IDENTIFICADOR", "FLOAT", "IF", "THEN",
        "ELSE", "WHILE", "DO", "PRINT", "ARRAY", "CADENA", "MAYOR_IGUAL", "MENOR_IGUAL",
        "DISTINTO", "ASIG",};
    final static String yyrule[] = {
        "$accept : programa",
        "programa : sentencias",
        "bloque : sentencia",
        "bloque : '{' sentencias '}'",
        "sentencias : sentencia",
        "sentencias : sentencias sentencia",
        "indice : arreglo",
        "indice : expresion",
        "indice : FLOAT",
        "argumento : IDENTIFICADOR",
        "argumento : FLOAT",
        "argumento : IDENTIFICADOR '[' indice ']'",
        "sentencia : declaracion",
        "sentencia : seleccion",
        "sentencia : bucle",
        "sentencia : impresion",
        "sentencia : asignacion",
        "declaracion : FLOAT variables ';'",
        "declaracion : ARRAY arreglo ';'",
        "arreglo : IDENTIFICADOR '[' argumento ']'",
        "variables : IDENTIFICADOR",
        "variables : variables ',' IDENTIFICADOR",
        "comparador : '<'",
        "comparador : '>'",
        "comparador : '='",
        "comparador : MENOR_IGUAL",
        "comparador : MAYOR_IGUAL",
        "comparador : DISTINTO",
        "condicion : argumento comparador argumento",
        "seleccion : IF '(' condicion ')' THEN bloque",
        "$$1 :",
        "seleccion : IF '(' condicion ')' THEN $$1 ELSE bloque",
        "bucle : WHILE '(' condicion ')' DO bloque",
        "impresion : PRINT '(' CADENA ')' ';'",
        "termino : termino '*' argumento",
        "termino : termino '/' argumento",
        "termino : argumento",
        "expresion : expresion '+' termino",
        "expresion : expresion '-' termino",
        "asignacion : IDENTIFICADOR ASIG expresion ';'",
        "asignacion : IDENTIFICADOR ASIG argumento",};

//#line 90 "gramatica.y"
//#line 292 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
    void yylexdebug(int state, int ch) {
        String s = null;
        if (ch < 0) {
            ch = 0;
        }
        if (ch <= YYMAXTOKEN) //check index bounds
        {
            s = yyname[ch];    //now get it
        }
        if (s == null) {
            s = "illegal-symbol";
        }
        debug("state " + state + ", reading " + ch + " (" + s + ")");
    }
//The following are now global, to aid in error reporting
    int yyn;       //next next thing to do
    int yym;       //
    int yystate;   //current parsing state from state table
    String yys;    //current token string

//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
    int yyparse() throws FileNotFoundException, IOException {
        boolean doaction;
        init_stacks();
        yynerrs = 0;
        yyerrflag = 0;
        yychar = -1;          //impossible char forces a read
        yystate = 0;            //initial state
        state_push(yystate);  //save it
        val_push(yylval);     //save empty value
        while (true) //until parsing is done, either correctly, or w/error
        {
            doaction = true;
            if (yydebug) {
                debug("loop");
            }
            //#### NEXT ACTION (from reduction table)
            for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
                if (yydebug) {
                    debug("yyn:" + yyn + "  state:" + yystate + "  yychar:" + yychar);
                }
                if (yychar < 0) //we want a char?
                {
                    yychar = yylex();  //get next token
                    if (yydebug) {
                        debug(" next yychar:" + yychar);
                    }
                    //#### ERROR CHECK ####
                    if (yychar < 0) //it it didn't work/error
                    {
                        yychar = 0;      //change it to default string (no -1!)
                        if (yydebug) {
                            yylexdebug(yystate, yychar);
                        }
                    }
                }//yychar<0
                yyn = yysindex[yystate];  //get amount to shift by (shift index)
                if ((yyn != 0) && (yyn += yychar) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {
                    if (yydebug) {
                        debug("state " + yystate + ", shifting to state " + yytable[yyn]);
                    }
                    //#### NEXT STATE ####
                    yystate = yytable[yyn];//we are in a new state
                    state_push(yystate);   //save it
                    val_push(yylval);      //push our lval as the input for next rule
                    yychar = -1;           //since we have 'eaten' a token, say we need another
                    if (yyerrflag > 0) //have we recovered an error?
                    {
                        --yyerrflag;        //give ourselves credit
                    }
                    doaction = false;        //but don't process yet
                    break;   //quit the yyn=0 loop
                }

                yyn = yyrindex[yystate];  //reduce
                if ((yyn != 0) && (yyn += yychar) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {   //we reduced!
                    if (yydebug) {
                        debug("reduce");
                    }
                    yyn = yytable[yyn];
                    doaction = true; //get ready to execute
                    break;         //drop down to actions
                } else //ERROR RECOVERY
                {
                    if (yyerrflag == 0) {
                        yyerror("syntax error");
                        yynerrs++;
                    }
                    if (yyerrflag < 3) //low error count?
                    {
                        yyerrflag = 3;
                        while (true) //do until break
                        {
                            if (stateptr < 0) //check for under & overflow here
                            {
                                yyerror("stack underflow. aborting...");  //note lower case 's'
                                return 1;
                            }
                            yyn = yysindex[state_peek(0)];
                            if ((yyn != 0) && (yyn += YYERRCODE) >= 0
                                    && yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE) {
                                if (yydebug) {
                                    debug("state " + state_peek(0) + ", error recovery shifting to state " + yytable[yyn] + " ");
                                }
                                yystate = yytable[yyn];
                                state_push(yystate);
                                val_push(yylval);
                                doaction = false;
                                break;
                            } else {
                                if (yydebug) {
                                    debug("error recovery discarding state " + state_peek(0) + " ");
                                }
                                if (stateptr < 0) //check for under & overflow here
                                {
                                    yyerror("Stack underflow. aborting...");  //capital 'S'
                                    return 1;
                                }
                                state_pop();
                                val_pop();
                            }
                        }
                    } else //discard this token
                    {
                        if (yychar == 0) {
                            return 1; //yyabort
                        }
                        if (yydebug) {
                            yys = null;
                            if (yychar <= YYMAXTOKEN) {
                                yys = yyname[yychar];
                            }
                            if (yys == null) {
                                yys = "illegal-symbol";
                            }
                            debug("state " + yystate + ", error recovery discards token " + yychar + " (" + yys + ")");
                        }
                        yychar = -1;  //read another
                    }
                }//end error recovery
            }//yyn=0 loop
            if (!doaction) //any reason not to proceed?
            {
                continue;      //skip action
            }
            yym = yylen[yyn];          //get count of terminals on rhs
            if (yydebug) {
                debug("state " + yystate + ", reducing " + yym + " by rule " + yyn + " (" + yyrule[yyn] + ")");
            }
            if (yym > 0) //if count of rhs not 'nil'
            {
                yyval = val_peek(yym - 1); //get current semantic value
            }
            yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
            switch (yyn) {
//########## USER-SUPPLIED ACTIONS ##########
                case 30:
//#line 67 "gramatica.y"
//{ bloque }
                    break;
//#line 445 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
            }//switch
            //#### Now let's reduce... ####
            if (yydebug) {
                debug("reduce");
            }
            state_drop(yym);             //we just reduced yylen states
            yystate = state_peek(0);     //get new state
            val_drop(yym);               //corresponding value drop
            yym = yylhs[yyn];            //select next TERMINAL(on lhs)
            if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
            {
                if (yydebug) {
                    debug("After reduction, shifting from state 0 to state " + YYFINAL + "");
                }
                yystate = YYFINAL;         //explicitly say we're done
                state_push(YYFINAL);       //and save it
                val_push(yyval);           //also save the semantic value of parsing
                if (yychar < 0) //we want another character?
                {
                    yychar = yylex();        //get next character
                    if (yychar < 0) {
                        yychar = 0;  //clean, if necessary
                    }
                    if (yydebug) {
                        yylexdebug(yystate, yychar);
                    }
                }
                if (yychar == 0) //Good exit (if lex returns 0 ;-)
                {
                    break;                 //quit the loop--all DONE
                }
            }//if yystate
            else //else not done yet
            {                         //get next state and push, for next yydefred[]
                yyn = yygindex[yym];      //find out where to go
                if ((yyn != 0) && (yyn += yystate) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yystate) {
                    yystate = yytable[yyn]; //get new state
                } else {
                    yystate = yydgoto[yym]; //else go to new defred
                }
                if (yydebug) {
                    debug("after reduction, shifting from state " + state_peek(0) + " to state " + yystate + "");
                }
                state_push(yystate);     //going again, so push state & val...
                val_push(yyval);         //for next action
            }
        }//main loop
        return 0;//yyaccept!!
    }
//## end of method parse() ######################################

//## run() --- for Thread #######################################
    /**
     * A default run method, used for operating this parser object in the
     * background. It is intended for extending Thread or implementing Runnable.
     * Turn off with -Jnorun .
     */
    public void run() throws FileNotFoundException, IOException {
        yyparse();
    }
//## end of method run() ########################################

//## Constructors ###############################################
    /**
     * Default constructor. Turn off with -Jnoconstruct .
     *
     */
    public Parser() {
        //nothing to do
    }

    /**
     * Create a parser, setting the debug to true or false.
     *
     * @param debugMe true for debugging, false for no debug.
     */
    public Parser(boolean debugMe) {
        yydebug = debugMe;
    }

//###############################################################
    public Parser(AnalizadorLexico l) {
         lexico = l;

    }

    private int yylex() throws FileNotFoundException, IOException{
	int yyl_return = -1;
	try
        {
		yyl_return = lexico.yylex();
		yylval = lexico.getValorSimbolo();

	}
	catch (IOException e)
        {
		System.err.println("error de E/S:"+e);
	}
	return yyl_return;
    }

    private void yyerror(String stack_underflow_aborting) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
//################### END OF CLASS ##############################
