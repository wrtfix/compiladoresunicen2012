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
    public Token(){
        
    }
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

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
