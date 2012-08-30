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
public class AccionSemantica4 extends AccionSemantica {
    
    public AccionSemantica4(String m){
        super(m);
        tipo = "simbolo";
    }
    
    @Override
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS) {
        return lexema + caracter;
    }
    
}
