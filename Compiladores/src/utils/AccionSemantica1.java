/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wrtfix
 */
public class AccionSemantica1 extends AccionSemantica{
    public AccionSemantica1(String m){
        super(m);
        
    }
    @Override
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS) {
           this.mensajeError = "Caracter no valido"+caracter;
           this.error = true;
           return lexema;
    }
    
}
