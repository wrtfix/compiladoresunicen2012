/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Pasa el valor del lexema a float y verifica que no exceda los límites, en caso contrario se informa el error.
 * Identifica al lexema como un token, depende lo que se decidió en el paso anterior.
 * Busca la constante en la tabla de símbolos; si no está, la agrega.
 * Retorna el último carácter leído.
 * @author wrtfix
 */
public class AccionSemantica10 extends AccionSemantica{
    public AccionSemantica10(String m){
        super(m);
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
//        System.out.println("lexema"+lexema);
        Float a;
        Token t = null;
        Float f = Float.valueOf(lexema.toString()).floatValue();
        
        if (f.compareTo(new Float(1.17549435e-38))== -1 || f.compareTo(new Float(3.40282347e+38)) == 1){
            this.error = true;
            linea++;
            this.mensajeError = "ERROR fuera de rango linea "+linea;
            //lexema = new StringBuffer();

            
        }
        else
        {
            Simbolo s = new Simbolo(lexema,"NUMERO");
            t = new Token(s.getTipo(),s);
            tablaS.addSimbolo(s);
            
        }
        lexema = new StringBuffer();               
        retroceder = true;
            
        //    lexema.setLength(0);
//            lexema.delete(0, lexema.length());
        return t;
    }
    
}
