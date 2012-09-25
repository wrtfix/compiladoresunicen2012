/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class AccionSemantica13 extends AccionSemantica{
    public AccionSemantica13(String m){
        super(m);
        
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
        //lexema = new StringBuffer();  
        lexema.deleteCharAt(0);
        linea++;
        this.mensajeError = "Error en linea "+linea+" número mal especificado '.'";
        this.error = true;
        this.retroceder = true;
        return null;
    }
}
