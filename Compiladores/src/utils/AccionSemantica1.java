/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Informa que se encontro un caracter no valido en el codigo
 * @author wrtfix
 */
public class AccionSemantica1 extends AccionSemantica{
    public AccionSemantica1(String m){
        super(m);
        
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
           this.mensajeError = "Error "+linea+" caracter no valido "+caracter;
           this.error = true;
           return null;
    }
    
}
