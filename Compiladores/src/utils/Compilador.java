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
        ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
        
        ArrayList<Token> lista = new ArrayList<Token>();
        while (!l.esFinal()){
            
            Token r = analizadorL.getTokens(l, tablaSimbolos);
            if(r != null)
                lista.add(r);
       }
        System.out.println("# TOKEN "+ lista.size());
        for (int i = 0; i<lista.size();i++)
            System.out.println("TOKEN "+ lista.get(i).getTipo()+":"+lista.get(i).getPuntero().getValor());
       
       
        System.out.println("# SIMBOLOS " +tablaSimbolos.size());
        System.out.println("TABLA SIMBOLOS ");
        for(int i = 0; i < tablaSimbolos.size(); i++  )
            System.out.println(tablaSimbolos.get(i).getTipo()+"  "+tablaSimbolos.get(i).getValor());
        
        ArrayList errores = analizadorL.getErrores();
        System.out.println("ERRORES");
        for(int i = 0;i < errores.size() ;i++)
            System.out.println(errores.get(i));
        
        
    }
}
