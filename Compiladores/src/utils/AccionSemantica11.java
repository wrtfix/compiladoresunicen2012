/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Esta accion semantica identifica el final de un archivo.
 * @author Mauricio
 */
public class AccionSemantica11 extends AccionSemantica{

    public AccionSemantica11(String m){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
        Simbolo s = new Simbolo(lexema,"FIN");
        Token t = new Token(s.getTipo(),s);
        return t;
        
    }
    
}
