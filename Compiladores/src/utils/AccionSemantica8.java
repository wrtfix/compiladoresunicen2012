/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Este accion semantica vacia el string en donde se fue formando el lexema
 * @author wrtfix
 */
public class AccionSemantica8 extends AccionSemantica {
    public AccionSemantica8(String m){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        lexema = new StringBuffer();
        //lexema.delete(0, lexema.length()-1);
        return null;
    }
    
}
