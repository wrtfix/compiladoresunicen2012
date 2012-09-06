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
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        lexema = lexema + caracter;
        Simbolo s = new Simbolo(lexema,null);
        Token t = new Token(s.getTipo(),s);
        tablaS.add(s);        
        return lexema;
    }
    
    
}
