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
public class AccionSemantica12 extends AccionSemantica{

    public AccionSemantica12(String m ){
        super(m);
    }
    @Override
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS) {        
        tipo ="Simbolo";
        return lexema + caracter;
    }
    
}
