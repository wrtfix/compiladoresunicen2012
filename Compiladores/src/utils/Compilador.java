/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author wrtfix
 */

public class Compilador {

    
    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        //Abrimos el codigo fuente
        

        
        
        Lector l = new Lector(args[0]);
        
        AnalizadorLexico analizadorL = new AnalizadorLexico(args[0]);
        System.out.println("Analizador Sintactico");
        Parser p = new Parser(analizadorL);
        //while (analizadorL.masTokens()){
            p.run();
        p.imprimirSintactico();
            //}
        p.imprimirPila();
//        
//        ArrayList<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
//         ArrayList<Token> lista = new ArrayList<Token>();
//        while (analizadorL.masTokens()){
//            Token r = analizadorL.getTokens();
//            if(r != null)
//                lista.add(r);
//       }
//        analizadorL.imprimirAcciones();
        
//        System.out.println("# TOKEN: "+ lista.size());
//        System.out.println("<Tipo,Valor>");
//        for (int i = 0; i<lista.size();i++)
//            System.out.println("< "+ lista.get(i).getTipo()+" , "+lista.get(i).getPuntero().getValor()+" >");
//       
//       
//        System.out.println("# SIMBOLOS " +tablaSimbolos.size());
//        System.out.println("TABLA SIMBOLOS ");
//        for(int i = 0; i < tablaSimbolos.size(); i++  )
//            System.out.println(tablaSimbolos.get(i).getTipo()+"  "+tablaSimbolos.get(i).getValor());
//      
        System.out.println("");
        System.out.println("TOKENS");
        System.out.println("<Tipo,Valor>");
        analizadorL.imprimirToken();
        System.out.println("");
        analizadorL.getTabla().imprimirTabla();
        System.out.println("");
        System.out.println("ERRORES");
        analizadorL.imprimitErrores();
        
        
//        System.out.println("ERRORES");
//        for(int i = 0;i < errores.size() ;i++)
//            System.out.println(errores.get(i));
        
        
    }
}