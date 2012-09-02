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
        AnalizadorLexico L = new AnalizadorLexico();
        L.imprimir();
        ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
        Token r = L.getTokens(l, tablaSimbolos);
        Token r2 = L.getTokens(l, tablaSimbolos);
        Token r3 = L.getTokens(l, tablaSimbolos);
        Token r4 = L.getTokens(l, tablaSimbolos);
        Token r5 = L.getTokens(l, tablaSimbolos);
        System.out.println("TOKENS: " + r.getTipo());
        System.out.println("TOKENS: " + r2.getTipo());
        System.out.println("TOKENS: " + r3.getLexema());
        System.out.println("TOKENS: " + r4.getLexema());
        System.out.println("TOKENS: " + r5.getLexema());
        
        System.out.println("SIMBOLOS :" +tablaSimbolos.size());
        for(int i = 0; i < tablaSimbolos.size(); i++  )
            System.out.println(tablaSimbolos.get(i).getNombre());
    }
}
