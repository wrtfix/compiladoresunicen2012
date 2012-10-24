/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author wrtfix
 */
public class GeneradorCodigo {

    private Stack<String> pilaCodigo;
    private Logger log;
    private TablaSimbolo ts;
    private Hashtable<String, String> operadores = new Hashtable();
    ;
    private Stack<Integer> labels;
    private Integer cont;

    public GeneradorCodigo(TablaSimbolo tabla, Stack<Integer> l) {
        pilaCodigo = new Stack<String>();
        log = new Logger("codigo.asm");
        ts = tabla;
        log.addLogger(".386");
        log.addLogger(".model flat, stdcall");
        log.addLogger("option casemap :none");
        log.addLogger("include \\masm32\\include\\windows.inc");
        log.addLogger("include \\masm32\\include\\kernel32.inc");
        log.addLogger("include \\masm32\\include\\user32.inc");
        log.addLogger("includelib \\masm32\\lib\\kernel32.lib");
        log.addLogger("includelib \\masm32\\lib\\user32.lib");
        operadores.put("+", "+");
        operadores.put("-", "-");
        operadores.put("*", "*");
        operadores.put("/", "/");
        operadores.put(":=", ":=");
        operadores.put("&", "&");
        operadores.put("^", "^");
        operadores.put("JMP", "JMP");
        operadores.put("JLE", "JLE");
        operadores.put("JGE", "JGE");
        operadores.put("JE", "JE");
        operadores.put("JA", "JA");
        operadores.put("JL", "JL");
        labels = l;
        cont = 0;
    }

    public void imprimirCodigo() {
        for (int i = 0; i < pilaCodigo.size(); i++) {
            log.addLogger(pilaCodigo.get(i));
        }
    }

    public void ejecutarSuma(String der, String izq) {
        log.addLogger("MOV ax," + der);
        log.addLogger("ADD ax," + izq);
        log.addLogger("MOV aux" + cont.toString() + ", ax");
        cont++;
    }

    public void ejecutarResta(String der, String izq) {

        log.addLogger("MOV ax," + der);
        log.addLogger("SUB ax," + izq);
        log.addLogger("MOV aux" + cont.toString() + ", ax");
        cont++;

    }

    public void ejecutarMutiplicar(String der, String izq) {
        log.addLogger("MOV ax," + der);
        log.addLogger("IMUL" + izq);
        log.addLogger("MOV aux" + cont + ", ax");
        cont++;


    }

    public void ejecutarDividir(String der, String izq) {

        log.addLogger("MOV ax," + der);
        log.addLogger("DIV dx," );
        log.addLogger("IDIV" + izq);
        log.addLogger("MOV aux" + cont.toString() + ", ax");
        cont++;
        
    }

    public void ejecutarAsignacion(String der, String izq) {
        log.addLogger("MOV ax " + izq);
        log.addLogger("MOV dir "+der+", ax");
    }

    public void ejecutarSalto(String salto, String tipo) {

        log.addLogger("Label " + labels.firstElement().toString() + ": MOV edx, " + salto);
        log.addLogger(tipo + " edx");
        labels.remove(labels.firstElement());

    }

    public void recorrerPolaca(Vector<String> polaca) {

        for (int i = 0; i < polaca.size(); i++) {
            float result = 0;
            String varAux = polaca.get(i);
            if (esOperador(varAux)) {
                // sumo desapilando el ultimo y ante ultimo y el resultado se apila
                if ("+".equals(varAux)) {
                    String id1 = pilaCodigo.pop();
                    String id2 = pilaCodigo.pop();
                    ejecutarSuma(id1, id2);
                }
                //resta desapilando el ultimo y ante ultimo y el resultado se apila
                if("-".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarResta(id1,id2);
                }
                //multiplicar desapilando el ultimo y ante ultimo y el resultado se apila
                if("*".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarMutiplicar(id1,id2);
                }
                //dividir desapilando el ultimo y ante ultimo y el resultado se apila
                if("/".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarDividir(id1,id2);
                }                
                if (":=".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarAsignacion(id2,id1);
                }
                if ("JMP".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JGE".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JLE".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JNE".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JA".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JL".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("JE".equals(varAux)) {
                    String salto = pilaCodigo.pop();
                    ejecutarSalto(salto, varAux);
                }
                if ("^".equals(varAux)){
                    String base = pilaCodigo.pop();
                    StringBuffer vs = new StringBuffer(base);
                    Simbolo aux = new Simbolo(vs, "ARRAY FLOAT");
                    aux = ts.existeSimbolo(aux);
                    int limite = Integer.valueOf(aux.getTamanio());
                    
                }
                if ("&".equals(varAux)){
                    
                    
                }
                //apilar el resultado en la pila                
                pilaCodigo.push(String.valueOf(String.valueOf(result)));
            } else {
                pilaCodigo.push(varAux);//apilo;                            
            }
        }
    }

    public boolean esOperador(String varAux) {
        return (operadores.contains(varAux));
    }

    public int obtenerLimite(String operacion) {
//        String[] token = operacion.split(" ");
//        int i,op=0;
//        
//        while (i < token.length){
//            if(token[i].equals("+")){
//                
//            }
//            i++;
//        }
        return 0;
    }

    public void addTablaSimbolo() {
        ArrayList<Simbolo> elem = ts.getTabla();
        log.addLogger(".data");
        String tipo = "";
        String aux = "";
        for (int i = 0; i < elem.size(); i++) {
            tipo = elem.get(i).getTipoVariable();
            if (tipo.equals("FLOAT")) {
                log.addLogger(elem.get(i).getValor().toString() + " DD " + "0");
            }
            if (tipo.equals("ARRAY FLOAT")) {
                log.addLogger(elem.get(i).getValor().toString() +" "+ elem.get(i).getTamanio() + " DUP " + "0");
            }
            if (tipo.equals("STRING")) {
                aux = elem.get(i).getValor().toString().replace("'", "");
                log.addLogger(aux.replace(" ", "") + " db " + "\"" + aux + "\"");
            }
        }
    }

    public void imprimir() {
        log.imprimir();
    }
}
