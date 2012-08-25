/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite manipular una matriz
 *
 * @author wrtfix
 */
public class Matriz {

    private Object celdas[];
    private int fila;
    private int columna;

    /**
     * Para la creacion de una matriz se necesitan los siguientes parametros
     *
     * @param fila
     * @param columna
     */
    public Matriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.celdas = new Object[fila*columna];
       
    }

    /**
     * Para la creacion de matriz con elemento seteados se necesitan los siguientes parametros
     *
     * @param celdas
     * @param fila
     * @param columna
     */
    public Matriz(Object celdas[], int fila, int columna) {
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
        if (fila <= this.fila & columna <= this.columna) {
            int pos = (columna*(this.columna - 1) / 2) + fila;
            this.celdas[pos]=elem;

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
        if (fila <= this.fila & columna <= this.columna) {
            int pos = (columna * (this.columna - 1) / 2) + fila;
            return this.celdas[pos];
        }
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fuera de rango...");

        return null;
    }
        
}
