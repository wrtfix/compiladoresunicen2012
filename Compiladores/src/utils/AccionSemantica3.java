/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Verifica que el tamaño del String del lexema sea menor o igual a 12; si no es así, trunca la cadena aceptando solo los primeros 12 caracteres e informa la corrección realizada.
 * Busca el lexema en la Tabla de Palabras Reservadas, si lo encuentra indica que es de este tipo; 
 * sino, lo busca en la Tabla de Símbolos (si no está, lo agrega a esta tabla) y luego lo informa como identificador.
 * Retorna el último carácter leído.
 * @author wrtfix
 */
public class AccionSemantica3 extends AccionSemantica {
    private ArrayList<String> palabras;
    public AccionSemantica3(String m){
        super(m);
        tipo="Identificador";
        //cargamos la palabras reservadas
        palabras = new ArrayList<String>();
        palabras.add("if");
        palabras.add("else");
        palabras.add("then");
        palabras.add("while");
        palabras.add("var");
        palabras.add("print");
        palabras.add("float");
        palabras.add("array");   
        palabras.add("do");   
    }
    
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
        
        //verificamos si es una palabra reservada
        Simbolo s = null;
        
        if (this.palabras.contains(lexema.toString())){
            s = new Simbolo(lexema,lexema.toString().toUpperCase());
//            if(!tablaS.contains(s))
//                tablaS.add(s);
//                
            tablaS.addSimbolo(s);
        } 
        else 
        {
            if (lexema.length() > 12) {
                lexema = lexema.delete(12, lexema.length()); // trunco el string
            }
            s = new Simbolo(lexema, "IDENTIFICADOR");
            //System.out.println();
//            if (!tablaS.contains(s)) {
//                tablaS.add(s);
//            } else {
//
//                Iterator<Simbolo> it = tablaS.iterator();
//                Simbolo a = null;
//
//                while (it.hasNext() && !a.equals(s)) {
//                    a = it.next();
//                }
//                s = a;
//            }
            tablaS.addSimbolo(s);
        }
        Token t = new Token(s.getTipo(),s);
        retroceder = true;
        return t;
    }
    
    
    
}
