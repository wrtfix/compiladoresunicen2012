package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Esta clase me permitira realizar la lectura de un archivo de texto
 * linea por lina.
 * @author wrtfix
 */
public class Lector {
 /**
 * * Método que lee las líneas de un archivo txt una por una y las guarda en un arrayList de Strings.
 * @param  ruta Contiene la ruta en donde se encuentra el archivo txt.
 * @return Devuelve un arrayList que contine cada una de las líneas del archivo.
 * @author mauripiccolo
 */
     public ArrayList<String> leerArchivo(String ruta){        
        ArrayList<String> lineas = new ArrayList();
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader bf = new BufferedReader(fr); 
            String sCadena = "";
            //Agrega la línea leída en el arreglo lineas
            while ((sCadena = bf.readLine())!= null) {
                lineas.add(sCadena);                
            } 
	} catch (FileNotFoundException fnfe){fnfe.printStackTrace();} 
          catch (IOException ioe){ioe.printStackTrace();}			
        return lineas;
    }
}
