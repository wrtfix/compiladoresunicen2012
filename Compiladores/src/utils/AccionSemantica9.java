/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Esta accion semantica cuando encuentra un comilla simple se va al estado final y detecta el token
 * como una string y busca el string en la tabla de simbolos y si no esta lo agrega.
 * @author wrtfix
 */
public class AccionSemantica9 extends AccionSemantica{
    

    public AccionSemantica9(String m){
        super(m);
    }
    
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        Simbolo s = new Simbolo(lexema.append(caracter), "CADENA");
        if (!tablaS.contains(s))
           tablaS.add(s);
        Token t = new Token(s.getTipo(),s);
        return t;
    }
    
    
}
