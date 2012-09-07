/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Agregar un caracter leido al lexema
 * @author wrtfix
 */
public class AccionSemantica2 extends AccionSemantica{
        
    public AccionSemantica2(String m){
        super(m);        
    }
    
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
    lexema.append(caracter);
      
        return null;
    }
    
}
