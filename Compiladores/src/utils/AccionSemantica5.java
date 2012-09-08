/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Esta accion semantica identifica elementos dentro de un comentario los cuales no seran considerados.
 * @author Mauricio
 */
public class AccionSemantica5 extends AccionSemantica{

    public AccionSemantica5(String m){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        lexema.delete(0, lexema.length());
        return null;
    }
    
}
