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
        a.set(1, 1, 1);
        a.set(1, 2, 2);
        a.set(2, 1, 3);
        a.set(2, 2, 4);
        
        System.out.println(a.get(1, 1));
        System.out.println(a.get(1, 2));
        System.out.println(a.get(2, 1));
        System.out.println(a.get(2, 2));
        System.out.println(a.get(5, 5));
        
    }
}
