/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author wrtfix
 */
public class Logger {

    private ArrayList<String> lineas;
    private String direccion;

    public Logger(String dir) {
        
        lineas = new ArrayList<String>();
        direccion = dir;

    }

    public void addLogger(String linea) {
        lineas.add(linea);
    }
    public boolean existe(String elem){
        return lineas.contains(elem);
    }

    public void imprimir() {
        // abrir el archivo
        System.out.println("Se encontraron "+lineas.size()+" entradas");
        try {
            FileWriter fichero = new FileWriter(direccion);
            
            String resultado;
            for (int i = 0; i < lineas.size(); i++) {
                resultado = lineas.get(i);
                System.out.println(resultado);
                fichero.write(resultado + "\r\n");
            }
            fichero.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public boolean estaVacio(){
        return lineas.isEmpty();
    }
}
