/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Hashtable;
import java.util.Stack;

/**
 *
 * @author wrtfix
 */
public class GeneradorCodigo {
    private Stack<String> pilaCodigo;
    private Logger log;
    private TablaSimbolo ts;
    private Hashtable<String,String> operadores = new Hashtable();;
    
    public GeneradorCodigo(TablaSimbolo tabla){
        pilaCodigo = new Stack<String>();
        log = new Logger("logcodigo.log");
        ts = tabla;        
        operadores.put("Suma", "+");
        operadores.put("Resta", "-");
        operadores.put("Multiplicacion", "*");
        operadores.put("Division", "/");
        operadores.put("Asignacion", "/");
        operadores.put("SaltoIcondicional", "/");
    }
    
    public void imprimirCodigo(){
        for (int i=0; i<pilaCodigo.size();i++){
            log.addLogger(pilaCodigo.get(i));
        }
    }
    
    public float ejecutarSuma(String der, String izq){
        /*
         *  MOV AX, der
         *  ADD AX, izq
         *  MOV aux, AX
         */
        return (Float.valueOf(der) + Float.valueOf(izq));
    }
    public float ejecutarResta(String der, String izq){
        /*
         *  MOV AX, der
         *  SUB AX, izq
         *  MOV aux, AX
         */
        return(Float.valueOf(der) - Float.valueOf(izq));
    }
    
    public float ejecutarMutiplicar(String der, String izq){
        /*
         *  MOV AX, der
         *  IMUL izq 
         *  MOV aux, AX
         */
        return (Float.valueOf(der) * Float.valueOf(izq));
    }
    
    public float ejecutarDividir(String der, String izq){
        /*
         *  MOV AX, der
         *  DIV DX, 0
         *  IDIV izq
         *  MOV aux, AX
         */
        return (Float.valueOf(der) / Float.valueOf(izq));
    }
    
    public float ejecutarAsignacion(String der, String izq){
        /* Mov Ax izq
         * Mov dir der, Ax         
         */             
        return (Float.valueOf(der));
    }
    
    
    public void ejecutarSaltoFalso(String salto){
        /*
         *  Los saltos deben ser cargados en el registro EDX
         */
    }
    
    public void ejecutarSaltoIncondicional(String salto){
        /*
         *  Preguntar
         */
    }
    
    
    
    public void recorrerPolaca(Stack<String> polaca){        
        for (int i=0; i<polaca.size();i++){
            float result = 0;
            String varAux = polaca.get(i);            
            if ( esOperador(varAux)){
                // sumo desapilando el ultimo y ante ultimo y el resultado se apila
                if("+".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();                   
                    result = ejecutarSuma(id1,id2);
                }
                //resta desapilando el ultimo y ante ultimo y el resultado se apila
                if("-".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    result = ejecutarResta(id1,id2);
                }
                //multiplicar desapilando el ultimo y ante ultimo y el resultado se apila
                if("*".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    result = ejecutarMutiplicar(id1,id2);
                }
                //dividir desapilando el ultimo y ante ultimo y el resultado se apila
                if("/".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    result = ejecutarDividir(id1,id2);
                }                
                if (":=".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    result = ejecutarAsignacion(id1,id2);
                }
                if ("BI".equals(varAux)){
                    String salto = pilaCodigo.pop();
                    ejecutarSaltoIncondicional(salto);
                }
                if ("BF".equals(varAux)){
                    String salto = pilaCodigo.pop();                
                    ejecutarSaltoFalso(salto);
                }
                //apilar el resultado en la pila                
                pilaCodigo.push(String.valueOf(String.valueOf(result)));
            }
            else
               pilaCodigo.push(varAux);//apilo;                            
        }
    }
    public boolean esOperador(String varAux){
        return (operadores.contains(varAux));
    }
}
