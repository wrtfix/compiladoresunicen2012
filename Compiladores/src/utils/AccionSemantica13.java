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
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        //lexema = new StringBuffer();  
        lexema.deleteCharAt(0);
        this.mensajeError = "Error en linea"+linea+" caracter no valido '.'";
           this.error = true;
           this.retroceder = true;
           
           return null;
    }
}
