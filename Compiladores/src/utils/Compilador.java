/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author wrtfix
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Abrimos el codigo fuente
      
        Lector l = new Lector(args[0]);
        AnalizadorLexico analizadorL = new AnalizadorLexico();
        System.out.println("Estados");
        analizadorL.imprimirEstados();
        System.out.println("Acciones");
        analizadorL.imprimirAcciones();
        ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
        Token r = analizadorL.getTokens(l, tablaSimbolos);
        Token r2 = analizadorL.getTokens(l, tablaSimbolos);
        Token r3 = analizadorL.getTokens(l, tablaSimbolos);
        Token r4 = analizadorL.getTokens(l, tablaSimbolos);
        Token r5 = analizadorL.getTokens(l, tablaSimbolos);
        Token r6 = analizadorL.getTokens(l, tablaSimbolos);
        Token r7 = analizadorL.getTokens(l, tablaSimbolos);
        Token r8 = analizadorL.getTokens(l, tablaSimbolos);
        System.out.println("TOKEN: " + r.getPuntero().getValor());
        System.out.println("TOKEN: " + r2.getPuntero().getValor());
        System.out.println("TOKEN: " + r3.getPuntero().getValor());
        System.out.println("TOKEN: " + r4.getPuntero().getValor());
        System.out.println("TOKEN: " + r5.getPuntero().getValor());
        System.out.println("TOKEN: " + r6.getPuntero().getValor());
        System.out.println("TOKEN: " + r7.getPuntero().getValor());
        System.out.println("TOKEN: " + r8.getPuntero().getValor());       
        System.out.println("# SIMBOLOS :" +tablaSimbolos.size());
        System.out.println("TABLA SIMBOLOS :");
        for(int i = 0; i < tablaSimbolos.size(); i++  )
            System.out.println(tablaSimbolos.get(i).getValor());
        
    }
}
