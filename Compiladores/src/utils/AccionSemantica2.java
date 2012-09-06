/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Agregar un caracter leido al lexema
 * @author wrtfix
 */
public class AccionSemantica2 extends AccionSemantica{
        
    public AccionSemantica2(String m){
        super(m);        
    }
    
    @Override
    public Token run(String lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        String aux = lexema;
        lexema = aux + caracter;
        return null;
    }
    
}
