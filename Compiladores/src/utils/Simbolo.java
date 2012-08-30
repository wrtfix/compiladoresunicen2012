/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Mauricio
 */
public class Simbolo {
    private String nombre;
    private String tipo;
    private Object valor;
    
    public Simbolo(String n, String t,Object v){
        nombre = n;
        tipo = t ;
        valor = v;
    }

    public String getNombre() {
        return nombre;
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

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
