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
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        Simbolo s = new Simbolo(lexema.append(caracter),null);
        Token t = new Token(s.getTipo(),s);
        tablaS.add(s);
        lexema = new StringBuffer();
        return t;
    }
    
    
}
