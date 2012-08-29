/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Mauricio
 */
public class AccionSemantica {
    protected Boolean error;
    protected String mensajeError;
    /**
    * Metodo constructor de la clase.
    * @param mensaje contiene el mensaje de error de la acción semántica en caso de que haya uno.
    * @return Devuelve un String con error que se produce, en caso contrario lo devuelve vacío.
    * @author mauripiccolo
    */
    public AccionSemantica(String mensaje){
        error = false;
        mensajeError = mensaje;
    }
    /**
    * Metodo que devuelve el error correspondiente a la acción semántica si es que se produjo.
    * @return Devuelve un String con error que se produce, en caso contrario lo devuelve vacío.
    * @author mauripiccolo
    **/
    public String getMensajeError(){
        return mensajeError;
    }
    /**
    * Devuelve si hubo un error en el proceso ejecutado.
    * @return Devuelve true si se produjo un error.
    * @author mauripiccolo
    **/
    public Boolean getError(){
        return error;
    }
    /**
    * Metodo que dispara el proceso que tiene que realizar la acción semántica, se define en 
    * cada de las mismas.    
    * @return Devuelve un String con el lexema que va formando en caso contrario lo devuelve vacío.
    * @author mauripiccolo
    */    
}