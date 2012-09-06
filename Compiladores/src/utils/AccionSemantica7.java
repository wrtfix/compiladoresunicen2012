/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 * Esta accione semantica se utiliza cuando se encuentra luego de un : cualquier elemento distinto
 * al =
 * @author wrtfix
 */
public class AccionSemantica7 extends AccionSemantica{
    public AccionSemantica7(String m){
        super(m);
    }
    
    public String run(String lexema, char caracter, ArrayList<Simbolo> tablaS,int linea) {
        this.mensajeError = "Error "+linea+" caracter no valido "+caracter;
        this.error = true;
        return lexema;
    }
    
}
