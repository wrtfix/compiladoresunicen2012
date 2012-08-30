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
    private String lexema;
    private String tipo;
    
    public Token(String l, String t){
        lexema = l;
        tipo = t;
    }
    public String getLexema(){
        return lexema;
    }
    public String getTipo(){
        return tipo;
    }
}
