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
        //System.out.println("Estados");
        //analizadorL.imprimirEstados();
        //System.out.println("Acciones");
        //analizadorL.imprimirAcciones();
        ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
        
        //System.out.println("El tamañano"+l.cantLineas());
        ArrayList<Token> lista = new ArrayList<Token>();
//        for (int i =0; i<l.cantLineas();i++){
        while (!l.esFinal()){
            //System.out.println("esfinal"+l.esFinal()+":"+l.getLine());
            
            Token r = analizadorL.getTokens(l, tablaSimbolos);
            if(r != null)
                lista.add(r);
       }

        for (int i = 0; i<lista.size();i++)
            System.out.println("TOKEN: "+ lista.get(i).getTipo());
       
        
/*        Token r = analizadorL.getTokens(l, tablaSimbolos);        
        Token r2 = analizadorL.getTokens(l, tablaSimbolos);        
        Token r3 = analizadorL.getTokens(l, tablaSimbolos);
        Token r4 = analizadorL.getTokens(l, tablaSimbolos);
        Token r5 = analizadorL.getTokens(l, tablaSimbolos);
        Token r6 = analizadorL.getTokens(l, tablaSimbolos);
        Token r7 = analizadorL.getTokens(l, tablaSimbolos);
        Token r8 = analizadorL.getTokens(l, tablaSimbolos);
        Token r9 = analizadorL.getTokens(l, tablaSimbolos);
        Token r10 = analizadorL.getTokens(l, tablaSimbolos);
        Token r11 = analizadorL.getTokens(l, tablaSimbolos);
        Token r12 = analizadorL.getTokens(l, tablaSimbolos);
        Token r13 = analizadorL.getTokens(l, tablaSimbolos);
        System.out.println("TOKEN: " + r.getPuntero().getValor());
        System.out.println("TOKEN: " + r2.getPuntero().getValor());
        System.out.println("TOKEN: " + r3.getPuntero().getValor());
        System.out.println("TOKEN: " + r4.getPuntero().getValor());
        System.out.println("TOKEN: " + r5.getPuntero().getValor());
        System.out.println("TOKEN: " + r6.getPuntero().getValor());
        System.out.println("TOKEN: " + r7.getPuntero().getValor());
        System.out.println("TOKEN: " + r8.getPuntero().getValor());       
        System.out.println("TOKEN: " + r9.getPuntero().getValor());
        System.out.println("TOKEN: " + r10.getPuntero().getValor());
        System.out.println("TOKEN: " + r11.getPuntero().getValor());
        System.out.println("TOKEN: " + r12.getPuntero().getValor());  
        System.out.println("TOKEN: " + r13.getPuntero().getValor());  */
        
        System.out.println("# SIMBOLOS :" +tablaSimbolos.size());
        System.out.println("TABLA SIMBOLOS :");
        for(int i = 0; i < tablaSimbolos.size(); i++  )
            System.out.println(tablaSimbolos.get(i).getTipo()+":"+tablaSimbolos.get(i).getValor());
        
        ArrayList errores = analizadorL.getErrores();
        System.out.println("ERRORES:");
        for(int i = 0;i < errores.size() ;i++)
            System.out.println(errores.get(i));
        
        
    }
}
