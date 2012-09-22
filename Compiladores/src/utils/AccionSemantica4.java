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
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
        Simbolo s = null;
        if(lexema.length() != 0){  
            retroceder = true;
            s = new Simbolo(lexema,lexema.toString());        
            
        }
        else{
            s = new Simbolo(lexema.append(caracter),lexema.toString());        
        }
        Token t = new Token(s.getTipo(),s);
        tablaS.addSimbolo(s);
        lexema = new StringBuffer();
        return t;
    }
    
    
}
