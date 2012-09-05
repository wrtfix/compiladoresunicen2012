/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Mauricio
 */
public class Token {
    private Simbolo simb;
    private String tipo;
    public Token(){
        
    }
    public Token(String t,Simbolo s){
        simb = s;
        tipo = t;
    }
    public Simbolo getPuntero(){
        return simb;
    }
    public String getTipo(){
        return tipo;
    }

    public void setPuntero(Simbolo s) {
        this.simb = s;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
