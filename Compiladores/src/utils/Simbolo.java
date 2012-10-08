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
    private StringBuffer valor;
    private String tipo;
    private int accesos;
    private String tipoVariable;
    private boolean declarado;

    public boolean isDeclarado() {
        return declarado;
    }

    public void setDeclarado(boolean declarado) {
        this.declarado = declarado;
    }
    public String getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(String tipoVariable) {
        this.tipoVariable = tipoVariable;
    }
    public int getAccesos() {
        return accesos;
    }

    public void incrementarAccesos() {
        this.accesos = this.accesos +1;
    }
    
    public void decrementarAccesos() {
        this.accesos = this.accesos - 1;
    }
    
    
    public Simbolo(StringBuffer v, String t){
        this.valor = new StringBuffer();
        this.valor.append(v);
        tipo = t ;
        accesos =1;
        tipoVariable = "";
        declarado =false;
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
        if (s != null)
            return s.getValor().toString().equals(valor.toString());
        return false;
    }
    
    public boolean esVacio(){
        return "".equals(tipoVariable);
    }
}
