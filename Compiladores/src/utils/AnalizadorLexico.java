/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Mauricio
 */
public class AnalizadorLexico {

    private Matriz estados;
    private Matriz accionesSemanticas;
    private ArrayList<String> errores;
    private Lector l;
    private TablaSimbolo tablaS = new TablaSimbolo();
    private Token token;
    private ParserVal p;
    private Logger logError = new Logger("error.log");
    private Logger logToken = new Logger("token.log");

    public AnalizadorLexico(String ruta) {
        l = new Lector(ruta);
        errores = new ArrayList<String>();
        estados = new Matriz(14, 18);
        accionesSemanticas = new Matriz(14, 18);
        //acciones semanticas
        AccionSemantica accionSemantica1 = new AccionSemantica1("accion1");
        AccionSemantica accionSemantica2 = new AccionSemantica2("accion2");
        AccionSemantica accionSemantica3 = new AccionSemantica3("accion3");
        AccionSemantica accionSemantica4 = new AccionSemantica4("accion4");
        AccionSemantica accionSemantica5 = new AccionSemantica5("accion5");
        AccionSemantica accionSemantica6 = new AccionSemantica6("accion6");
        AccionSemantica accionSemantica7 = new AccionSemantica7("accion7");
        AccionSemantica accionSemantica8 = new AccionSemantica8("accion8");
        AccionSemantica accionSemantica9 = new AccionSemantica9("accion9");
        AccionSemantica accionSemantica10 = new AccionSemantica10("accion10");
        AccionSemantica accionSemantica11 = new AccionSemantica11("accion11");
        AccionSemantica accionSemantica12 = new AccionSemantica12("accion12");
        AccionSemantica accionSemantica13 = new AccionSemantica13("accion13");


        /*
         * AccionSemantica accionSemantica1 = new AccionSemantica("");
         * AccionSemantica accionSemantica2 = new AccionSemantica("");
         * AccionSemantica accionSemantica3 = new AccionSemantica("");
         * AccionSemantica accionSemantica4 = new AccionSemantica4("");
         * AccionSemantica accionSemantica5 = new AccionSemantica("");
         * AccionSemantica accionSemantica6 = new AccionSemantica("");
         * AccionSemantica accionSemantica7 = new AccionSemantica("");
         * AccionSemantica accionSemantica8 = new AccionSemantica("");
         * AccionSemantica accionSemantica9 = new AccionSemantica("");
         * AccionSemantica accionSemantica10 = new AccionSemantica("");
         * AccionSemantica accionSemantica11 = new AccionSemantica("");
         * AccionSemantica accionSemantica12 = new AccionSemantica("");
         */
        //FILA0
        estados.set(0, 0, 0);
        estados.set(0, 1, 1);
        estados.set(0, 2, 9);
        estados.set(0, 3, 2);
        estados.set(0, 4, 14);
        estados.set(0, 5, 14);//final
        estados.set(0, 6, 0);
        estados.set(0, 7, 3);
        estados.set(0, 8, 4);
        estados.set(0, 9, 0);
        estados.set(0, 10, 0);
        estados.set(0, 11, 0);
        estados.set(0, 12, 14);
        estados.set(0, 13, 5);
        estados.set(0, 14, 7);
        estados.set(0, 15, 11);
        estados.set(0, 16, 7);
        estados.set(0, 17, 14);
        //FILA1
        estados.set(1, 0, 14);
        estados.set(1, 1, 1);
        estados.set(1, 2, 1);
        estados.set(1, 3, 14);
        estados.set(1, 4, 14);
        estados.set(1, 5, 14);
        estados.set(1, 6, 14);
        estados.set(1, 7, 14);
        estados.set(1, 8, 14);
        estados.set(1, 9, 14);
        estados.set(1, 10, 14);
        estados.set(1, 11, 14);
        estados.set(1, 12, 14);
        estados.set(1, 13, 14);
        estados.set(1, 14, 14);
        estados.set(1, 15, 14);
        estados.set(1, 16, 14);
        estados.set(1, 17, 14);
        //e2
        estados.set(2, 0, 0);
        estados.set(2, 1, 0);
        estados.set(2, 2, 0);
        estados.set(2, 3, 0);
        estados.set(2, 4, 14);
        estados.set(2, 5, 0);
        estados.set(2, 6, 0);
        estados.set(2, 7, 0);
        estados.set(2, 8, 0);
        estados.set(2, 9, 0);
        estados.set(2, 10, 0);
        estados.set(2, 11, 0);
        estados.set(2, 12, 0);
        estados.set(2, 13, 0);
        estados.set(2, 14, 0);
        estados.set(2, 15, 0);
        estados.set(2, 16, 0);
        estados.set(2, 17, 0);

        //FILA3
        estados.set(3, 0, 14);
        estados.set(3, 1, 14);
        estados.set(3, 2, 14);
        estados.set(3, 3, 14);
        estados.set(3, 4, 14);
        estados.set(3, 5, 14);
        estados.set(3, 6, 14);
        estados.set(3, 7, 14);
        estados.set(3, 8, 14);
        estados.set(3, 9, 14);
        estados.set(3, 10, 14);
        estados.set(3, 11, 14);
        estados.set(3, 12, 14);
        estados.set(3, 13, 14);
        estados.set(3, 14, 14);
        estados.set(3, 15, 14);
        estados.set(3, 16, 14);
        estados.set(3, 17, 14);
        //e4
        estados.set(4, 0, 14);
        estados.set(4, 1, 14);
        estados.set(4, 2, 14);
        estados.set(4, 3, 14);
        estados.set(4, 4, 14);
        estados.set(4, 5, 14);
        estados.set(4, 6, 14);
        estados.set(4, 7, 14);
        estados.set(4, 8, 14);
        estados.set(4, 9, 14);
        estados.set(4, 10, 14);
        estados.set(4, 11, 14);
        estados.set(4, 12, 14);
        estados.set(4, 13, 14);
        estados.set(4, 14, 14);
        estados.set(4, 15, 14);
        estados.set(4, 16, 14);
        estados.set(4, 17, 14);
        //FILA5
        estados.set(5, 0, 14);
        estados.set(5, 1, 14);
        estados.set(5, 2, 14);
        estados.set(5, 3, 14);
        estados.set(5, 4, 14);
        estados.set(5, 5, 14);
        estados.set(5, 6, 14);
        estados.set(5, 7, 14);
        estados.set(5, 8, 14);
        estados.set(5, 9, 14);
        estados.set(5, 10, 14);
        estados.set(5, 11, 14);
        estados.set(5, 12, 14);
        estados.set(5, 13, 6);
        estados.set(5, 14, 14);
        estados.set(5, 15, 14);
        estados.set(5, 16, 14);
        estados.set(5, 17, 14);
        //FILA6
        estados.set(6, 0, 6);
        estados.set(6, 1, 6);
        estados.set(6, 2, 6);
        estados.set(6, 3, 6);
        estados.set(6, 4, 6);
        estados.set(6, 5, 6);
        estados.set(6, 6, 6);
        estados.set(6, 7, 6);
        estados.set(6, 8, 6);
        estados.set(6, 9, 0);
        estados.set(6, 10, 6);
        estados.set(6, 11, 6);
        estados.set(6, 12, 6);
        estados.set(6, 13, 6);
        estados.set(6, 14, 6);
        estados.set(6, 15, 6);
        estados.set(6, 16, 6);
        estados.set(6, 17, 6);
        //e7
        estados.set(7, 0, 7);
        estados.set(7, 1, 7);
        estados.set(7, 2, 7);
        estados.set(7, 3, 7);
        estados.set(7, 4, 7);
        estados.set(7, 5, 7);
        estados.set(7, 6, 8);
        estados.set(7, 7, 7);
        estados.set(7, 8, 7);
        estados.set(7, 9, 14);
        estados.set(7, 10, 7);
        estados.set(7, 11, 7);
        estados.set(7, 12, 7);
        estados.set(7, 13, 7);
        estados.set(7, 14, 14);
        estados.set(7, 15, 7);
        estados.set(7, 16, 7);
        estados.set(7, 17, 7);
        //FILA8
        estados.set(8, 0, 8);
        estados.set(8, 1, 8);
        estados.set(8, 2, 8);
        estados.set(8, 3, 8);
        estados.set(8, 4, 8);
        estados.set(8, 5, 8);
        estados.set(8, 6, 7);
        estados.set(8, 7, 8);
        estados.set(8, 8, 8);
        estados.set(8, 9, 8);
        estados.set(8, 10, 8);
        estados.set(8, 11, 8);
        //estados.set(8, 12, 8);
        estados.set(8, 12, 0);
        estados.set(8, 13, 8);
        estados.set(8, 14, 14);
        estados.set(8, 15, 8);
        estados.set(8, 16, 8);
        estados.set(8, 17, 8);
        //FILA9
        estados.set(9, 0, 0);
        estados.set(9, 1, 0);
        estados.set(9, 2, 9);
        estados.set(9, 3, 0);
        estados.set(9, 4, 0);
        estados.set(9, 5, 0);
        estados.set(9, 6, 0);
        estados.set(9, 7, 0);
        estados.set(9, 8, 0);
        estados.set(9, 9, 0);
        estados.set(9, 10, 0);
        estados.set(9, 11, 0);
        estados.set(9, 12, 0);
        estados.set(9, 13, 0);
        estados.set(9, 14, 0);
        estados.set(9, 15, 12);
        estados.set(9, 16, 10);
        estados.set(9, 17, 0);
        //FILA10
        estados.set(10, 0, 0);
        estados.set(10, 1, 0);
        estados.set(10, 2, 13);
        estados.set(10, 3, 0);
        estados.set(10, 4, 0);
        estados.set(10, 5, 0);
        estados.set(10, 6, 0);
        estados.set(10, 7, 0);
        estados.set(10, 8, 0);
        estados.set(10, 9, 0);
        estados.set(10, 10, 0);
        estados.set(10, 11, 0);
        estados.set(10, 12, 0);
        estados.set(10, 13, 0);
        estados.set(10, 14, 0);
        estados.set(10, 15, 0);
        estados.set(10, 16, 0);
        estados.set(10, 17, 13);
        //FILA11
        estados.set(11, 0, 0);
        estados.set(11, 1, 0);
        estados.set(11, 2, 12);
        estados.set(11, 3, 0);
        estados.set(11, 4, 0);
        estados.set(11, 5, 0);
        estados.set(11, 6, 0);
        estados.set(11, 7, 0);
        estados.set(11, 8, 0);
        estados.set(11, 9, 0);
        estados.set(11, 10, 0);
        estados.set(11, 11, 0);
        estados.set(11, 12, 0);
        estados.set(11, 13, 0);
        estados.set(11, 14, 0);
        estados.set(11, 15, 0);
        estados.set(11, 16, 0);
        estados.set(11, 17, 0);
        //FILA12
        estados.set(12, 0, 0);
        estados.set(12, 1, 14);
        estados.set(12, 2, 12);
        estados.set(12, 3, 14);
        estados.set(12, 4, 14);
        estados.set(12, 5, 14);
        estados.set(12, 6, 14);
        estados.set(12, 7, 14);
        estados.set(12, 8, 14);
        estados.set(12, 9, 14);
        estados.set(12, 10, 14);
        estados.set(12, 11, 14);
        estados.set(12, 12, 14);
        estados.set(12, 13, 14);
        estados.set(12, 14, 14);
        estados.set(12, 15, 14);
        estados.set(12, 16, 10);
        estados.set(12, 17, 14);
        //FILA13
        estados.set(13, 0, 14);
        estados.set(13, 1, 14);
        estados.set(13, 2, 13);
        estados.set(13, 3, 14);
        estados.set(13, 4, 14);
        estados.set(13, 5, 14);
        estados.set(13, 6, 14);
        estados.set(13, 7, 14);
        estados.set(13, 8, 14);
        estados.set(13, 9, 14);
        estados.set(13, 10, 14);
        estados.set(13, 11, 14);
        estados.set(13, 12, 14);
        estados.set(13, 13, 14);
        estados.set(13, 14, 14);
        estados.set(13, 15, 14);
        estados.set(13, 16, 14);
        estados.set(13, 17, 14);

        //FILA0
        accionesSemanticas.set(0, 0, accionSemantica1);
        accionesSemanticas.set(0, 1, accionSemantica2);
        accionesSemanticas.set(0, 2, accionSemantica2);
        accionesSemanticas.set(0, 3, accionSemantica2);
        accionesSemanticas.set(0, 4, accionSemantica4);
//        accionesSemanticas.set(0, 5, accionSemantica12);
        accionesSemanticas.set(0, 5, accionSemantica4);
        accionesSemanticas.set(0, 6, accionSemantica1);
        accionesSemanticas.set(0, 7, accionSemantica2);
        accionesSemanticas.set(0, 8, accionSemantica2);
        accionesSemanticas.set(0, 9, accionSemantica5);
        accionesSemanticas.set(0, 10, accionSemantica5);
        accionesSemanticas.set(0, 11, accionSemantica5);
        accionesSemanticas.set(0, 12, accionSemantica11);
        accionesSemanticas.set(0, 13, accionSemantica2);
        accionesSemanticas.set(0, 14, accionSemantica2);
        accionesSemanticas.set(0, 15, accionSemantica2);
        accionesSemanticas.set(0, 16, accionSemantica2);
        accionesSemanticas.set(0, 17, accionSemantica4);
//        accionesSemanticas.set(0, 17, accionSemantica12);
        //FILA1
        accionesSemanticas.set(1, 0, accionSemantica3);
        accionesSemanticas.set(1, 1, accionSemantica2);
        accionesSemanticas.set(1, 2, accionSemantica2);
        accionesSemanticas.set(1, 3, accionSemantica3);
        accionesSemanticas.set(1, 4, accionSemantica3);
        accionesSemanticas.set(1, 5, accionSemantica3);
        accionesSemanticas.set(1, 6, accionSemantica3);
        accionesSemanticas.set(1, 7, accionSemantica3);
        accionesSemanticas.set(1, 8, accionSemantica3);
        accionesSemanticas.set(1, 9, accionSemantica3);
        accionesSemanticas.set(1, 10, accionSemantica3);
        accionesSemanticas.set(1, 11, accionSemantica3);
        accionesSemanticas.set(1, 12, accionSemantica3);
        accionesSemanticas.set(1, 13, accionSemantica3);
        accionesSemanticas.set(1, 14, accionSemantica3);
        accionesSemanticas.set(1, 15, accionSemantica3);
        accionesSemanticas.set(1, 16, accionSemantica3);
        accionesSemanticas.set(1, 17, accionSemantica3);
        //FILA2
        accionesSemanticas.set(2, 0, accionSemantica7);
        accionesSemanticas.set(2, 1, accionSemantica7);
        accionesSemanticas.set(2, 2, accionSemantica7);
        accionesSemanticas.set(2, 3, accionSemantica7);
        accionesSemanticas.set(2, 4, accionSemantica6);
        accionesSemanticas.set(2, 5, accionSemantica7);
        accionesSemanticas.set(2, 6, accionSemantica7);
        accionesSemanticas.set(2, 7, accionSemantica7);
        accionesSemanticas.set(2, 8, accionSemantica7);
        accionesSemanticas.set(2, 9, accionSemantica7);
        accionesSemanticas.set(2, 10, accionSemantica7);
        accionesSemanticas.set(2, 11, accionSemantica7);
        accionesSemanticas.set(2, 12, accionSemantica7);
        accionesSemanticas.set(2, 13, accionSemantica7);
        accionesSemanticas.set(2, 14, accionSemantica7);
        accionesSemanticas.set(2, 15, accionSemantica7);
        accionesSemanticas.set(2, 16, accionSemantica7);
        accionesSemanticas.set(2, 17, accionSemantica7);
        //FILA3
        accionesSemanticas.set(3, 0, accionSemantica12);
        accionesSemanticas.set(3, 1, accionSemantica12);
        accionesSemanticas.set(3, 2, accionSemantica12);
        accionesSemanticas.set(3, 3, accionSemantica12);
        accionesSemanticas.set(3, 4, accionSemantica6);
        accionesSemanticas.set(3, 5, accionSemantica12);
        accionesSemanticas.set(3, 6, accionSemantica12);
        accionesSemanticas.set(3, 7, accionSemantica12);
        accionesSemanticas.set(3, 8, accionSemantica6);
        accionesSemanticas.set(3, 9, accionSemantica12);
        accionesSemanticas.set(3, 10, accionSemantica12);
        accionesSemanticas.set(3, 11, accionSemantica12);
        accionesSemanticas.set(3, 12, accionSemantica12);
        accionesSemanticas.set(3, 13, accionSemantica12);
        accionesSemanticas.set(3, 14, accionSemantica12);
        accionesSemanticas.set(3, 15, accionSemantica12);
        accionesSemanticas.set(3, 16, accionSemantica12);
        accionesSemanticas.set(3, 17, accionSemantica12);
        //FILA4
        accionesSemanticas.set(4, 0, accionSemantica12);
        accionesSemanticas.set(4, 1, accionSemantica12);
        accionesSemanticas.set(4, 2, accionSemantica12);
        accionesSemanticas.set(4, 3, accionSemantica12);
        accionesSemanticas.set(4, 4, accionSemantica6);
        accionesSemanticas.set(4, 5, accionSemantica12);
        accionesSemanticas.set(4, 6, accionSemantica12);
        accionesSemanticas.set(4, 7, accionSemantica12);
        accionesSemanticas.set(4, 8, accionSemantica12);
        accionesSemanticas.set(4, 9, accionSemantica12);
        accionesSemanticas.set(4, 10, accionSemantica12);
        accionesSemanticas.set(4, 11, accionSemantica12);
        accionesSemanticas.set(4, 12, accionSemantica12);
        accionesSemanticas.set(4, 13, accionSemantica12);
        accionesSemanticas.set(4, 14, accionSemantica12);
        accionesSemanticas.set(4, 15, accionSemantica12);
        accionesSemanticas.set(4, 16, accionSemantica12);
        accionesSemanticas.set(4, 17, accionSemantica12);
        //e5
        accionesSemanticas.set(5, 0, accionSemantica4);
        accionesSemanticas.set(5, 1, accionSemantica4);
        accionesSemanticas.set(5, 2, accionSemantica4);
        accionesSemanticas.set(5, 3, accionSemantica4);
        accionesSemanticas.set(5, 4, accionSemantica4);
        accionesSemanticas.set(5, 5, accionSemantica4);
        accionesSemanticas.set(5, 6, accionSemantica4);
        accionesSemanticas.set(5, 7, accionSemantica4);
        accionesSemanticas.set(5, 8, accionSemantica4);
        accionesSemanticas.set(5, 9, accionSemantica4);
        accionesSemanticas.set(5, 10, accionSemantica4);
        accionesSemanticas.set(5, 11, accionSemantica4);
        accionesSemanticas.set(5, 12, accionSemantica4);
        accionesSemanticas.set(5, 13, accionSemantica5);
        accionesSemanticas.set(5, 14, accionSemantica4);
        accionesSemanticas.set(5, 15, accionSemantica4);
        accionesSemanticas.set(5, 16, accionSemantica4);
        accionesSemanticas.set(5, 17, accionSemantica4);
        //e6
        accionesSemanticas.set(6, 0, accionSemantica5);
        accionesSemanticas.set(6, 1, accionSemantica5);
        accionesSemanticas.set(6, 2, accionSemantica5);
        accionesSemanticas.set(6, 3, accionSemantica5);
        accionesSemanticas.set(6, 4, accionSemantica5);
        accionesSemanticas.set(6, 5, accionSemantica5);
        accionesSemanticas.set(6, 6, accionSemantica5);
        accionesSemanticas.set(6, 7, accionSemantica5);
        accionesSemanticas.set(6, 8, accionSemantica5);
        accionesSemanticas.set(6, 9, accionSemantica8);
        accionesSemanticas.set(6, 10, accionSemantica5);
        accionesSemanticas.set(6, 11, accionSemantica5);
        accionesSemanticas.set(6, 12, accionSemantica5);
        accionesSemanticas.set(6, 13, accionSemantica5);
        accionesSemanticas.set(6, 14, accionSemantica5);
        accionesSemanticas.set(6, 15, accionSemantica5);
        accionesSemanticas.set(6, 16, accionSemantica5);
        accionesSemanticas.set(6, 17, accionSemantica5);
        //FILA7
        accionesSemanticas.set(7, 0, accionSemantica2);
        accionesSemanticas.set(7, 1, accionSemantica2);
        accionesSemanticas.set(7, 2, accionSemantica2);
        accionesSemanticas.set(7, 3, accionSemantica2);
        accionesSemanticas.set(7, 4, accionSemantica2);
        accionesSemanticas.set(7, 5, accionSemantica2);
        accionesSemanticas.set(7, 6, accionSemantica5);
        accionesSemanticas.set(7, 7, accionSemantica2);
        accionesSemanticas.set(7, 8, accionSemantica2);
        accionesSemanticas.set(7, 9, accionSemantica2);
        accionesSemanticas.set(7, 10, accionSemantica2);
        accionesSemanticas.set(7, 11, accionSemantica2);
        accionesSemanticas.set(7, 12, accionSemantica2);
        accionesSemanticas.set(7, 13, accionSemantica2);
        accionesSemanticas.set(7, 14, accionSemantica9);
        accionesSemanticas.set(7, 15, accionSemantica2);
        accionesSemanticas.set(7, 16, accionSemantica2);
        accionesSemanticas.set(7, 17, accionSemantica2);
        //FILA8
        accionesSemanticas.set(8, 0, accionSemantica5);
        accionesSemanticas.set(8, 1, accionSemantica5);
        accionesSemanticas.set(8, 2, accionSemantica5);
        accionesSemanticas.set(8, 3, accionSemantica5);
        accionesSemanticas.set(8, 4, accionSemantica5);
        accionesSemanticas.set(8, 5, accionSemantica5);
        accionesSemanticas.set(8, 6, accionSemantica5);
        accionesSemanticas.set(8, 7, accionSemantica5);
        accionesSemanticas.set(8, 8, accionSemantica5);
        accionesSemanticas.set(8, 9, accionSemantica5);
        accionesSemanticas.set(8, 10, accionSemantica5);
        accionesSemanticas.set(8, 11, accionSemantica5);
        accionesSemanticas.set(8, 12, accionSemantica1);
        accionesSemanticas.set(8, 13, accionSemantica5);
        accionesSemanticas.set(8, 14, accionSemantica9);
        accionesSemanticas.set(8, 15, accionSemantica5);
        accionesSemanticas.set(8, 16, accionSemantica5);
        accionesSemanticas.set(8, 17, accionSemantica5);
        //FILA9
        accionesSemanticas.set(9, 0, accionSemantica1);
        accionesSemanticas.set(9, 1, accionSemantica1);
        accionesSemanticas.set(9, 2, accionSemantica2);
        accionesSemanticas.set(9, 3, accionSemantica1);
        accionesSemanticas.set(9, 4, accionSemantica1);
        accionesSemanticas.set(9, 5, accionSemantica1);
        accionesSemanticas.set(9, 6, accionSemantica1);
        accionesSemanticas.set(9, 7, accionSemantica1);
        accionesSemanticas.set(9, 8, accionSemantica1);
        accionesSemanticas.set(9, 9, accionSemantica1);
        accionesSemanticas.set(9, 10, accionSemantica1);
        accionesSemanticas.set(9, 11, accionSemantica1);
        accionesSemanticas.set(9, 12, accionSemantica1);
        accionesSemanticas.set(9, 13, accionSemantica1);
        accionesSemanticas.set(9, 14, accionSemantica1);
        accionesSemanticas.set(9, 15, accionSemantica2);
        accionesSemanticas.set(9, 16, accionSemantica2);
        accionesSemanticas.set(9, 17, accionSemantica1);
        //FILA10
        accionesSemanticas.set(10, 0, accionSemantica1);
        accionesSemanticas.set(10, 1, accionSemantica1);
        accionesSemanticas.set(10, 2, accionSemantica2);
        accionesSemanticas.set(10, 3, accionSemantica1);
        accionesSemanticas.set(10, 4, accionSemantica1);
        accionesSemanticas.set(10, 5, accionSemantica1);
        accionesSemanticas.set(10, 6, accionSemantica1);
        accionesSemanticas.set(10, 7, accionSemantica1);
        accionesSemanticas.set(10, 8, accionSemantica1);
        accionesSemanticas.set(10, 9, accionSemantica1);
        accionesSemanticas.set(10, 10, accionSemantica1);
        accionesSemanticas.set(10, 11, accionSemantica1);
        accionesSemanticas.set(10, 12, accionSemantica1);
        accionesSemanticas.set(10, 13, accionSemantica1);
        accionesSemanticas.set(10, 14, accionSemantica1);
        accionesSemanticas.set(10, 15, accionSemantica1);
        accionesSemanticas.set(10, 16, accionSemantica1);
        accionesSemanticas.set(10, 17, accionSemantica2);
        //FILA11
        accionesSemanticas.set(11, 0, accionSemantica13);
        accionesSemanticas.set(11, 1, accionSemantica13);
        accionesSemanticas.set(11, 2, accionSemantica2);
        accionesSemanticas.set(11, 3, accionSemantica13);
        accionesSemanticas.set(11, 4, accionSemantica13);
        accionesSemanticas.set(11, 5, accionSemantica13);
        accionesSemanticas.set(11, 6, accionSemantica13);
        accionesSemanticas.set(11, 7, accionSemantica13);
        accionesSemanticas.set(11, 8, accionSemantica13);
        accionesSemanticas.set(11, 9, accionSemantica13);
        accionesSemanticas.set(11, 10, accionSemantica13);
        accionesSemanticas.set(11, 11, accionSemantica13);
        accionesSemanticas.set(11, 12, accionSemantica13);
        accionesSemanticas.set(11, 13, accionSemantica13);
        accionesSemanticas.set(11, 14, accionSemantica13);
        accionesSemanticas.set(11, 15, accionSemantica13);
        accionesSemanticas.set(11, 16, accionSemantica13);
        accionesSemanticas.set(11, 17, accionSemantica13);
        //FILA12
        accionesSemanticas.set(12, 0, accionSemantica1);
        accionesSemanticas.set(12, 1, accionSemantica10);
        accionesSemanticas.set(12, 2, accionSemantica2);
        accionesSemanticas.set(12, 3, accionSemantica10);
        accionesSemanticas.set(12, 4, accionSemantica10);
        accionesSemanticas.set(12, 5, accionSemantica10);
        accionesSemanticas.set(12, 6, accionSemantica10);
        accionesSemanticas.set(12, 7, accionSemantica10);
        accionesSemanticas.set(12, 8, accionSemantica10);
        accionesSemanticas.set(12, 9, accionSemantica10);
        accionesSemanticas.set(12, 10, accionSemantica10);
        accionesSemanticas.set(12, 11, accionSemantica10);
        accionesSemanticas.set(12, 12, accionSemantica10);
        accionesSemanticas.set(12, 13, accionSemantica10);
        accionesSemanticas.set(12, 14, accionSemantica10);
        accionesSemanticas.set(12, 15, accionSemantica10);
        accionesSemanticas.set(12, 16, accionSemantica2);
        accionesSemanticas.set(12, 17, accionSemantica10);
        //FILA13
        accionesSemanticas.set(13, 0, accionSemantica10);
        accionesSemanticas.set(13, 1, accionSemantica10);
        accionesSemanticas.set(13, 2, accionSemantica2);
        accionesSemanticas.set(13, 3, accionSemantica10);
        accionesSemanticas.set(13, 4, accionSemantica10);
        accionesSemanticas.set(13, 5, accionSemantica10);
        accionesSemanticas.set(13, 6, accionSemantica10);
        accionesSemanticas.set(13, 7, accionSemantica10);
        accionesSemanticas.set(13, 8, accionSemantica10);
        accionesSemanticas.set(13, 9, accionSemantica10);
        accionesSemanticas.set(13, 10, accionSemantica10);
        accionesSemanticas.set(13, 11, accionSemantica10);
        accionesSemanticas.set(13, 12, accionSemantica10);
        accionesSemanticas.set(13, 13, accionSemantica10);
        accionesSemanticas.set(13, 14, accionSemantica10);
        accionesSemanticas.set(13, 15, accionSemantica10);
        accionesSemanticas.set(13, 16, accionSemantica10);
        accionesSemanticas.set(13, 17, accionSemantica10);

    }

    public void imprimirEstados() {
        for (int i = 0; i < 14; i++) {
            System.out.print("F:" + i);
            for (int j = 0; j < 18; j++) {
                System.out.print(" C" + j + ":" + estados.get(i, j));
            }
            System.out.println("");
        }
    }

    public void imprimirAcciones() {
        for (int i = 0; i < 14; i++) {
            System.out.print("F:" + i);
            for (int j = 0; j < 18; j++) {
                if (accionesSemanticas.get(i, j) != null) {
                    AccionSemantica a = (AccionSemantica) accionesSemanticas.get(i, j);
                    System.out.print(" C:" + j + "_" + a.getIdentificador());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public Token getTokens() {
        StringBuffer lexema = new StringBuffer();
        Integer eActual = 0;
        Integer eSiguiente = 0;
        Token token = new Token();
        char caracter = ' ';
        while (caracter != '$' && eSiguiente != 14) {
            //System.out.println("Entro");
            caracter = l.getCaracter();
            AccionSemantica acc = (AccionSemantica) accionesSemanticas.getCelda(caracter, eActual);
            eSiguiente = (Integer) estados.getCelda(caracter, eActual);
//            System.out.println("act:" + eActual);
//            System.out.println(acc.getIdentificador());



            token = acc.run(lexema, caracter, tablaS, l.getLine());

            // if(token != null)
//            System.out.println("Caracter: " + caracter);




            if (acc.getError() == true) {
                errores.add(acc.getMensajeError());
                logError.addLogger(acc.getMensajeError());
                acc.setError(false);
            }
            if (acc.getRetroceder()) {
                l.retrocederPosicion();
                acc.setRetroceder(false);
                eSiguiente = (Integer) estados.getCelda(caracter, eActual);
            }
            eActual = eSiguiente;
//            System.out.println("sig:" + eSiguiente);
//            System.out.println("---------------");
            if (eActual == 14) {
                lexema = new StringBuffer().append("");
            }
        }
        return token;
    }

    public ParserVal getValorSimbolo() {
        return p;
    }

    public Token devolverToken() {
        return token;
    }

    public int yylex() throws FileNotFoundException, IOException {

        int numero = 0;

        token = getTokens();
        if (token != null) {
            logToken.addLogger("< " + token.getTipo() + " , " + token.getPuntero().getValor() + " >");
//            System.out.println("< " + token.getTipo() + " , " + token.getPuntero().getValor() + " >");

            if (token.getTipo().equals("NUMERO")) {
                numero = Parser.NUMERO;
            }
            if (token.getTipo().equals("FLOAT")) {
                numero = Parser.FLOAT;
            } else if (token.getTipo().equals("ASIGNACION")) {
                numero = Parser.ASIG;
            } else if (token.getTipo().equals("DISTINTO")) {
                numero = Parser.DISTINTO;
            } else if (token.getTipo().equals("MENOR_IGUAL")) {
                numero = Parser.MENOR_IGUAL;
            } else if (token.getTipo().equals("MAYOR_IGUAL")) {
                numero = Parser.MAYOR_IGUAL;
            } else if (token.getTipo().equals("WHILE")) {
                numero = Parser.WHILE;
            } else if (token.getTipo().equals("DO")) {
                numero = Parser.DO;
            } else if (token.getTipo().equals("IF")) {
                numero = Parser.IF;
            } else if (token.getTipo().equals("THEN")) {
                numero = Parser.THEN;
            } else if (token.getTipo().equals("ELSE")) {
                numero = Parser.ELSE;
            } else if (token.getTipo().equals("PRINT")) {
                numero = Parser.PRINT;
            } else if (token.getTipo().equals("ARRAY")) {
                numero = Parser.ARRAY;
            } else if (token.getTipo().equals("CADENA")) {
                numero = Parser.CADENA;
            } else if (token.getTipo().equals("IDENTIFICADOR")) {
                numero = Parser.IDENTIFICADOR;
            } else if (token.getPuntero().getValor().length() == 1) {
                numero = token.getPuntero().getValor().charAt(0);
            }
            //System.out.println("Pidio un token!");
            //System.out.println("el tipo  \""+token.getTipo()+"\"");
            p = new ParserVal(token.getPuntero().getValor().toString());
        }
        return numero;
    }

    public boolean masTokens() {
        return (!l.esFinal());
    }

    public TablaSimbolo getTabla() {
        return this.tablaS;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    public int getLinea() {
        return l.getLine();
    }

    public int getLineas() {
        return l.getLine() + 1;
    }
    public void imprimirToken(){
        logToken.imprimir();
    }
    public void imprimitErrores(){
        logError.imprimir();
    }
}
