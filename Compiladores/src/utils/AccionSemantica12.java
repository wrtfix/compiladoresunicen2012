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
    public Token run(String lexema, char caracter, ArrayList<Simbolo> tablaS, int linea) {        
        Simbolo s = new Simbolo(lexema+caracter,null);
        Token t = new Token(s.getTipo(),s);
        tablaS.add(s);
        retroceder = true;
        return t;
    }
    
}
