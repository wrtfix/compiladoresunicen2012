/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Stack;

/**
 *
 * @author wrtfix
 */
public class GeneradorCodigo {
    private Stack<String> codigo;
    private Logger log;
    private TablaSimbolo ts;
    public GeneradorCodigo(TablaSimbolo tabla){
        codigo = new Stack<String>();
        log = new Logger("logcodigo.log");
        ts = tabla;
    }
    
    public void imprimirCodigo(){
        for (int i=0; i<codigo.size();i++){
            log.addLogger(codigo.get(i));
        }
    }
    
    public void ejecutarSuma(String der, String izq){
        /*
         *  MOV AX, der
         *  ADD AX, izq
         *  MOV aux, AX
         */
    }
    public void ejecutarResta(String der, String izq){
        /*
         *  MOV AX, der
         *  SUB AX, izq
         *  MOV aux, AX
         */
        
    }
    
    public void ejecutarMutiplicar(String der, String izq){
        /*
         *  MOV AX, der
         *  IMUL izq 
         *  MOV aux, AX
         */
    }
    
    public void ejecutarDividir(String der, String izq){
        /*
         *  MOV AX, der
         *  DIV DX, 0
         *  IDIV izq
         *  MOV aux, AX
         */
    }
    
    public void ejecutarAsignacion(String der, String izq){
        /*
         * Buscar en la tabla de simbolos y se carga en el regitro EAX
         */
    }
    
    
    public void ejecutarCondicion(String der, String izq){
        /*
         *  Los saltos deben ser cargados en el registro EDX
         */
    }
    
    public void ejecutarIncondicional(String der, String izq){
        /*
         *  Preguntar
         */
    }
    
    
    
    public void recorrerPolaca(Stack<String> polaca){
        
        for (int i=0; i<polaca.size();i++){
            
        }
    }
}
