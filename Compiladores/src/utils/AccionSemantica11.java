/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Esta accion semanrica identifica el final de un archivo.
 * @author Mauricio
 */
public class AccionSemantica11 extends AccionSemantica{

    public AccionSemantica11(String m){
        super(m);
    }
    
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        Simbolo s = new Simbolo(lexema,"fin");
        Token t = new Token(s.getTipo(),s);
        return lexema;
        
    }
    
}
