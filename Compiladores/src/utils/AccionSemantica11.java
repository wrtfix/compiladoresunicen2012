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
public class AccionSemantica11 extends AccionSemantica{

    public AccionSemantica11(String m){
        super(m);
    }
    @Override
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS) {
        tipo = "FIN";
        return lexema;
    }
    
}
