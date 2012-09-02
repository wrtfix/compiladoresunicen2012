/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author wrtfix
 */
public class AccionSemantica2 extends AccionSemantica{
        
    public AccionSemantica2(String m){
        super(m);        
    }
    
    @Override
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS) {
        return lexema + caracter;
    }
    
}
