/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Informa que se encontro un caracter no valido en el codigo
 * @author wrtfix
 */
public class AccionSemantica1 extends AccionSemantica{
    public AccionSemantica1(String m){
        super(m);
        
    }
    @Override
    public Token run(StringBuffer lexema, char caracter, TablaSimbolo tablaS,int linea) {
           linea++;
           if(caracter == '@')
               this.mensajeError = "Error lexico en linea "+linea+ " salto de línea inesperado";
           else
               if  (caracter == '$')
                   this.mensajeError = "Error lexico en linea "+linea+" se esperaba un cierre de cadena";
               else
                   this.mensajeError = "Error lexico en linea "+linea+" caracter no valido "+caracter;
           this.error = true;
           return null;
    }
    
}
