/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Mauricio
 */
public class AnalizadorLexico {
    private Matriz estados;
    private Matriz accionesSemanticas;
    public AnalizadorLexico()
    {
        estados = new Matriz(14,18);
        accionesSemanticas = new Matriz(14,18);
        //FILA1
        estados.set(0, 0, 1);
        estados.set(0, 1, 2);
        estados.set(0, 2, 10);
        estados.set(0, 3, 3);
        estados.set(0, 4, 1);
        estados.set(0, 5, 15);
        estados.set(0, 6, 1);
        estados.set(0, 7, 4);
        estados.set(0, 8, 5);
        estados.set(0, 9, 1);
        estados.set(0, 10, 1);
        estados.set(0, 11, 1);
        estados.set(0, 12, 15);
        estados.set(0, 13, 15);
        estados.set(0, 14, 6);
        estados.set(0, 15, 8);
        estados.set(0, 16, 12);
        estados.set(0, 17, 8);
        estados.set(0, 18, 15);
        //FILA2
        estados.set(1, 0, 15);
        estados.set(1, 1, 2);
        estados.set(1, 2, 2);        
        estados.set(1, 3, 15);
        estados.set(1, 4, 15);
        estados.set(1, 5, 15);
        estados.set(1, 6, 15);
        estados.set(1, 7, 15);
        estados.set(1, 8, 15);
        estados.set(1, 9, 15);
        estados.set(1, 10, 15);
        estados.set(1, 11, 15);
        estados.set(1, 12, 15);
        estados.set(1, 13, 15);
        estados.set(1, 14, 15);
        estados.set(1, 15, 15);
        estados.set(1, 16, 15);
        estados.set(1, 17, 15);
        //e3
        estados.set(2, 0, 1);
        estados.set(2, 1, 1);
        estados.set(2, 2, 1);
        estados.set(2, 3, 1);
        estados.set(2, 4, 15);
        estados.set(2, 5, 1);
        estados.set(2, 6, 1);
        estados.set(2, 7, 1);
        estados.set(2, 8, 1);
        estados.set(2, 9, 1);
        estados.set(2, 10, 1);
        estados.set(2, 11, 1);
        estados.set(2, 12, 1);
        estados.set(2, 13, 1);
        estados.set(2, 14, 1);
        estados.set(2, 15, 1);
        estados.set(2, 16, 1);
        estados.set(2, 17, 1);
        //FILA4
        estados.set(3, 0, 15);
        estados.set(3, 1, 15);
        estados.set(3, 2, 15);
        estados.set(3, 3, 15);
        estados.set(3, 4, 15);
        estados.set(3, 5, 15);
        estados.set(3, 6, 15);
        estados.set(3, 7, 15);
        estados.set(3, 8, 15);
        estados.set(3, 9, 15);
        estados.set(3, 10, 15);
        estados.set(3, 11, 15);
        estados.set(3, 12, 15);
        estados.set(3, 13, 15);
        estados.set(3, 14, 15);
        estados.set(3, 15, 15);
        estados.set(3, 16, 15);
        estados.set(3, 17, 15);
        //e5
        estados.set(4, 0, 15);
        estados.set(4, 1, 15);
        estados.set(4, 2, 15);
        estados.set(4, 3, 15);
        estados.set(4, 4, 15);
        estados.set(4, 5, 15);
        estados.set(4, 6, 15);
        estados.set(4, 7, 15);
        estados.set(4, 8, 15);
        estados.set(4, 9, 15);
        estados.set(4, 10, 15);
        estados.set(4, 11, 15);
        estados.set(4, 12, 15);
        estados.set(4, 13, 15);
        estados.set(4, 14, 15);
        estados.set(4, 15, 15);
        estados.set(4, 16, 15);
        estados.set(4, 17, 15);        
        //FILA6
        estados.set(5, 0, 15);
        estados.set(5, 1, 15);
        estados.set(5, 2, 15);
        estados.set(5, 3, 15);
        estados.set(5, 4, 15);
        estados.set(5, 5, 15);
        estados.set(5, 6, 15);
        estados.set(5, 7, 15);
        estados.set(5, 8, 15);
        estados.set(5, 9, 15);
        estados.set(5, 10, 15);
        estados.set(5, 11, 15);
        estados.set(5, 12, 15);
        estados.set(5, 13, 15);
        estados.set(5, 14, 15);
        estados.set(5, 15, 15);
        estados.set(5, 16, 15);
        estados.set(5, 17, 15);
        //FILA7
        estados.set(6, 0, 7);
        estados.set(6, 1, 7);
        estados.set(6, 2, 7);
        estados.set(6, 3, 7);
        estados.set(6, 4, 7);
        estados.set(6, 5, 7);
        estados.set(6, 6, 7);
        estados.set(6, 7, 7);
        estados.set(6, 8, 7);
        estados.set(6, 9, 15);
        estados.set(6, 10, 7);
        estados.set(6, 11, 7);
        estados.set(6, 12, 7);
        estados.set(6, 13, 7);
        estados.set(6, 14, 8);
        estados.set(6, 15, 8);
        estados.set(6, 16, 8);
        estados.set(6, 17, 7);
        //e8
        estados.set(7, 0, 8);
        estados.set(7, 1, 8);
        estados.set(7, 2, 8);
        estados.set(7, 3, 8);
        estados.set(7, 4, 8);
        estados.set(7, 5, 8);
        estados.set(7, 6, 9);
        estados.set(7, 7, 8);
        estados.set(7, 8, 8);
        estados.set(7, 9, 15);
        estados.set(7, 10, 8);
        estados.set(7, 11, 8);
        estados.set(7, 12, 8);
        estados.set(7, 13, 8);
        estados.set(7, 14, 15);
        estados.set(7, 15, 8);
        estados.set(7, 16, 8);
        estados.set(7, 17, 8);
        
        //FILA9
        estados.set(8, 0, 9);
        estados.set(8, 1, 9);
        estados.set(8, 2, 9);
        estados.set(8, 3, 9);
        estados.set(8, 4, 9);
        estados.set(8, 5, 9);
        estados.set(8, 6, 8);
        estados.set(8, 7, 9);
        estados.set(8, 8, 9);
        estados.set(8, 9, 9);
        estados.set(8, 10, 9);
        estados.set(8, 11, 9);
        estados.set(8, 12, 9);
        estados.set(8, 13, 9);
        estados.set(8, 14, 9);
        estados.set(8, 15, 9);
        estados.set(8, 16, 9);
        estados.set(8, 17, 9);
        //FILA1
        estados.set(9, 0, 1);
        estados.set(9, 1, 1);
        estados.set(9, 2, 10);
        estados.set(9, 3, 1);
        estados.set(9, 4, 1);
        estados.set(9, 5, 1);
        estados.set(9, 6, 1);
        estados.set(9, 7, 1);
        estados.set(9, 8, 1);
        estados.set(9, 9, 1);
        estados.set(9, 10, 1);
        estados.set(9, 11, 1);
        estados.set(9, 12, 1);
        estados.set(9, 13, 1);
        estados.set(9, 14, 1);
        estados.set(9, 15, 13);
        estados.set(9, 16, 11);
        estados.set(9, 17, 1);
        //FILA11
        estados.set(10, 0, 1);
        estados.set(10, 1, 1);
        estados.set(10, 2, 14);
        estados.set(10, 3, 1);
        estados.set(10, 4, 1);
        estados.set(10, 5, 1);
        estados.set(10, 6, 1);
        estados.set(10, 7, 1);
        estados.set(10, 8, 1);
        estados.set(10, 9, 1);
        estados.set(10, 10, 1);
        estados.set(10, 11, 1);
        estados.set(10, 12, 1);
        estados.set(10, 13, 1);
        estados.set(10, 14, 1);
        estados.set(10, 15, 1);
        estados.set(10, 16, 1);
        estados.set(10, 17, 14);
        //FILA12
        estados.set(11, 0, 1);
        estados.set(11, 1, 1);
        estados.set(11, 2, 1);
        estados.set(11, 3, 1);
        estados.set(11, 4, 1);
        estados.set(11, 5, 1);
        estados.set(11, 6, 1);
        estados.set(11, 7, 1);
        estados.set(11, 8, 1);
        estados.set(11, 9, 1);
        estados.set(11, 10, 1);
        estados.set(11, 11, 1);
        estados.set(11, 12, 1);
        estados.set(11, 13, 1);
        estados.set(11, 14, 1);
        estados.set(11, 15, 13);
        estados.set(11, 16, 1);
        estados.set(11, 17, 1);
        //FILA13
        estados.set(12, 0, 1);
        estados.set(12, 1, 1);
        estados.set(12, 2, 1);
        estados.set(12, 3, 1);
        estados.set(12, 4, 1);
        estados.set(12, 5, 1);
        estados.set(12, 6, 1);
        estados.set(12, 7, 1);
        estados.set(12, 8, 1);
        estados.set(12, 9, 1);
        estados.set(12, 10, 1);
        estados.set(12, 11, 1);
        estados.set(12, 12, 1);
        estados.set(12, 13, 1);
        estados.set(12, 14, 1);
        estados.set(12, 15, 1);
        estados.set(12, 16, 11);
        estados.set(12, 17, 15);
        //FILA1
        estados.set(13, 0, 1);
        estados.set(13, 1, 15);
        estados.set(13, 2, 14);
        estados.set(13, 3, 1);
        estados.set(13, 4, 1);
        estados.set(13, 5, 1);
        estados.set(13, 6, 1);
        estados.set(13, 7, 15);
        estados.set(13, 8, 1);
        estados.set(13, 9, 1);
        estados.set(13, 10, 1);
        estados.set(13, 11, 1);
        estados.set(13, 12, 1);
        estados.set(13, 13, 1);
        estados.set(13, 14, 1);
        estados.set(13, 15, 1);
        estados.set(13, 16, 1);
        estados.set(13, 17, 1);
    }
    public void imprimir(){
        for(int i = 0 ; i < 14 ; i++){
            for(int j = 0 ; j < 18 ; j++)
                System.out.print(estados.get(i,j));
            System.out.println("");
        }
            
                
    }
}