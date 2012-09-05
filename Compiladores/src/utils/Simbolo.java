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
    private String valor;
    private String tipo;
    
    
    public Simbolo(String v, String t){
        valor = v;
        tipo = t ;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean equals(Simbolo s){
        return s.valor.equals(valor);
    }

}
