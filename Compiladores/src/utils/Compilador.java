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
        System.out.println("TOKEN: " + r.getTipo()+" "+ r.getLexema());
        System.out.println("TOKEN: " + r2.getTipo()+" "+ r2.getLexema());
        System.out.println("TOKEN: " + r3.getTipo()+" "+ r3.getLexema());
        System.out.println("TOKEN: " + r4.getTipo()+" "+ r4.getLexema());
        System.out.println("TOKEN: " + r5.getTipo()+" "+ r5.getLexema());
        
        System.out.println("SIMBOLOS :" +tablaSimbolos.size());
        for(int i = 0; i < tablaSimbolos.size(); i++  )
            System.out.println(tablaSimbolos.get(i).getNombre());
    }
}
