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
      
        
        Lector l = new Lector(args[0]);
        
        AnalizadorLexico analizadorL = new AnalizadorLexico(args[0]);
        System.out.println("Analizador Sintactico");
        PolacaInversa pi = new PolacaInversa();
        Parser p = new Parser(analizadorL);
        p.run();

        p.imprimirSintactico();

        p.imprimirPolacaInversa();

        //p.imprimirLabels();
        System.out.println("");
        /*System.out.println("TOKENS");
        System.out.println("<Tipo,Valor>");
        analizadorL.imprimirToken();
        System.out.println("");*/
        analizadorL.getTabla().imprimirTabla();
        /*System.out.println("");
        System.out.println("ERRORES LEXICOS");
        analizadorL.imprimitErrores();*/
        
        GeneradorCodigo c = new GeneradorCodigo(analizadorL.getTabla(), p.getLabels());
        
        c.addTablaSimbolo();
        c.recorrerPolaca(p.getPolaca());
        c.imprimir();
      
    }
}