/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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
        Matriz a = new Matriz(2,2);
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(1, 0, 3);
        a.set(1, 1, 4);
         
        System.out.println(a.get(0, 0));
        System.out.println(a.get(0, 1));
        System.out.println(a.get(1, 0));
        System.out.println(a.get(1, 1));
        System.out.println(a.get(5, 5));
        Lector l = new Lector(args[0]);
        System.out.println("Lamenla"+l.getLine());
        System.out.println("Chorpala"+l.getPos());
        System.out.print(l.getCaracter());
        System.out.print(l.getCaracter());
        System.out.print(l.getCaracter());
        System.out.println("Chorpala: "+l.getPos());
        l.retrocederPosicion();
        
        System.out.println("Chorpala: "+l.getPos());
        AnalizadorLexico L = new AnalizadorLexico();
        L.imprimir();
        
        
        
        
    }
}
