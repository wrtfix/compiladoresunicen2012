/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Reconoce simbolos tales como el < > = <= >= 
 * @author Mauricio
 */
public class AccionSemantica12 extends AccionSemantica{

    public AccionSemantica12(String m ){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS, int linea) {        
        Simbolo s = new Simbolo(lexema,lexema.toString());
        Token t = new Token(s.getTipo(),s);
        tablaS.addSimbolo(s);
        retroceder = true;
        lexema = new StringBuffer();
        return t;
    }
    
}
