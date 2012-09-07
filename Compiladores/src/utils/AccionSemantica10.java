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
    public Token run(StringBuffer lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        System.out.println("lexema"+lexema);
        Float a;
        Token t = null;
        float f = Float.valueOf(lexema.toString()).floatValue();
        if (f>1.17549435e-38 && f<3.40282347e38){
            this.error = true;
            this.mensajeError = "ERROR fuera de rango linea"+linea;
        }
        else
        {
            Simbolo s = new Simbolo(lexema,"Float");
            t = new Token(s.getTipo(),s);
            tablaS.add(s);
        }
        return t;
    }
    
}
