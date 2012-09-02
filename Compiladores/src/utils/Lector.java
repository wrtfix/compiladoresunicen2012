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
    private int linea,pos;
    private ArrayList<String> fuente;

 /**
 * * Método que lee las líneas de un archivo txt una por una y las guarda en un arrayList de Strings.
 * @param  ruta Contiene la ruta en donde se encuentra el archivo txt.
 * @return Devuelve un arrayList que contine cada una de las líneas del archivo.
 * @author mauripiccolo
 */
     public Lector(String ruta){        
        fuente = new ArrayList();
        linea = 0;
        pos = 0;  
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader bf = new BufferedReader(fr); 
            String sCadena = "";
            //Agrega la línea leída en el arreglo lineas
            while ((sCadena = bf.readLine())!= null) {
                System.out.println(sCadena);
                fuente.add(sCadena);                
            } 
//            sCadena = "$";
//            fuente.add(sCadena);
	} catch (FileNotFoundException fnfe){fnfe.printStackTrace();} 
          catch (IOException ioe){ioe.printStackTrace();}			        
    }
    public char getCaracter(){
        char c = 0;     
        System.out.println(pos);
        if (pos < fuente.get(linea).length()){
        c = fuente.get(linea).charAt(pos);
        
        System.out.println(c);
        
        if(c == '\n'){
            linea++;
            pos = 0;
        }else
            pos++;
       }else
            c='$';
        return c; 
        
    }
    public void retrocederPosicion(){
        if (pos != 0)
            pos--;
        else
            if (linea != 0){
                linea--;
                pos = fuente.get(linea).length()-1; 
            }
    }

    public int getPos() {
        return pos;
    }

    public int getLine() {
        return linea;
    }
    public boolean esFinal(){
        return (linea == fuente.size() && pos == fuente.get(linea).length()-1);
    }
}
