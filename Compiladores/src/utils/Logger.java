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

    public void imprimir() {
        // abrir el archivo
        System.out.println("");
        try {

            //Creamos un Nuevo objeto FileWriter dandole
            //como parámetros la ruta y nombre del fichero
            FileWriter fichero = new FileWriter("./log/"+direccion);

            //Insertamos el texto creado y si trabajamos
            //en Windows terminaremos cada línea con "\r\n"
            for (int i = 0; i < lineas.size(); i++) {
                String resultado = lineas.get(i);
                System.out.println(resultado);
                fichero.write(resultado + "\r\n");
            }

            //cerramos el fichero
            fichero.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
