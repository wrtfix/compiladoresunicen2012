/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite manipular una matriz
 *
 * @author wrtfix
 */
public class Matriz {

    private Object celdas[][];
    private int fila;
    private int columna;
    private Hashtable<Object,Integer> tabla; // elemento al que tenes que ir estado
    
    /**
     * Para la creacion de una matriz se necesitan los siguientes parametros
     *
     * @param fila
     * @param columna
     */
    public Matriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.celdas = new Object[fila][columna];        
    }
    /**
     * Este método devuelve la celda que corresponde al estado en que estamos actualmente y el caracter leído.
     * @param caracter
     * @param eActual
     * @return estados y o acciones samanticas
     */
    public Object getCelda(char caracter, int eActual){                
        Object celda = null;
        char EOF = (char)26; 

        if (Character.isLetter(caracter)&& (eActual == 1 || eActual ==0) && (eActual !=5)  )
            return get(eActual,1);//Es una letra
        else if (Character.isDigit(caracter) && (eActual !=5))
                return get(eActual,2);//Es un digito
        else if (caracter ==  '$')
                return get(eActual,12);
        else 
                    switch(caracter){
                        case ':' :  celda = get(eActual,3); break;
                        case '=' :  celda = get(eActual,4); break;
                        case '{' :  celda = get(eActual,5); break;
                        case '}' :  celda = get(eActual,5); break;
                        case '[' :  celda = get(eActual,5); break;
                        case ']' :  celda = get(eActual,5); break;
                        case '(' :  celda = get(eActual,5); break;
                        case ')' :  celda = get(eActual,5); break;
                        case ',' :  celda = get(eActual,5); break;
                        case ';' :  celda = get(eActual,5); break;
                        case '/' :  celda = get(eActual,6); break;
                        case '<' :  celda = get(eActual,7); break;
                        case '>' :  celda = get(eActual,8); break;
                        case '\n':  celda = get(eActual,9); break;                        
                        case ' ' :  celda = get(eActual,10); break;                            
                        case '\t':  celda = get(eActual,11);break;
                        case '*' :  celda = get(eActual,13); break;
                        case '\'':  celda = get(eActual,14); break;
                        case '.' :  celda = get(eActual,15); break;
                        case 'E' :  celda = get(eActual,16); break;                        
                        case '+' :  celda = get(eActual,17); break;
                        case '-' :  celda = get(eActual,17); break;
                }
        if(celda == null)
            celda = get(eActual, 0);
        if(Character.isWhitespace(caracter))
            celda = get(eActual,10);
        return celda;
    }
    /**
     * Para la creacion de matriz con elemento seteados se necesitan los siguientes parametros
     *
     * @param celdas
     * @param fila
     * @param columna
     */
    public Matriz(Object celdas[][], int fila, int columna) {
        this.celdas = celdas;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Este metodo permite setear elementos dentro de la matriz y controlar que
     * no te vayas de rango y requiere los siguientes parametros
     *
     * @param fila
     * @param columna
     * @param elem
     */
    public void set(int fila, int columna, Object elem) {
        // Verifico que no me vaya de rango
        if (fila < this.fila & columna < this.columna) {
            this.celdas[fila][columna]=elem;

        }
        else
        {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "La posicion "+fila+":"+columna+" se encuentra fuera de rango ");
        }
    }
    
    /**
     * Este metodo verifica que no te vayas de rango y obtiene un elemento de la
     * matriz y requiere los siguientes parametros
     *
     * @param fila
     * @param columna
     * @return
     */
    public Object get(int fila, int columna) {
        // Verifico que no me vaya de rango
        if (fila < this.fila & columna < this.columna) {
            return this.celdas[fila][columna];
        }
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "La posicion "+fila+":"+columna+" se encuentra fuera de rango ");
        return null;
    }
        
}
