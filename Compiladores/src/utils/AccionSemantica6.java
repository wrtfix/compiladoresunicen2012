/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Este accion semantica se utiliza para cuando se encuentra dentro de un automata >= o =
 * 
 * @author wrtfix
 */
public class AccionSemantica6 extends AccionSemantica{
    public AccionSemantica6(String m){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {

        String tipo = "";
        lexema.append(caracter);
        if (lexema.toString().equals(">=")) {
            tipo = "MAYOR_IGUAL";
        } else if (lexema.toString().equals("<=")) {
            tipo = "MENOR_IGUAL";
        } else if (lexema.toString().equals("<>")) {
            tipo = "DISTINTO";
        } else if (lexema.toString().equals(":=")) {
            tipo = "ASIGNACION";
        }

        Simbolo s = new Simbolo(lexema, tipo);

        tablaS.add(s);
        Token t = new Token(s.getTipo(), s);
        return t;
    }
    
}
