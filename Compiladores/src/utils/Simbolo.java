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
public class Simbolo {
    private StringBuffer valor;
    private String tipo;
    
    
    public Simbolo(StringBuffer v, String t){
        this.valor = new StringBuffer();
        this.valor.append(v);
        tipo = t ;
    }

    public String getTipo() {
        return tipo;
    }

    public StringBuffer getValor() {
        return valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean equals(Simbolo s){
        //System.out.println("valor"+s.getValor().toString());
        return valor.equals(s.getValor());
    }

}
