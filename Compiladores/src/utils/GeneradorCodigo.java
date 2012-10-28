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
    private Vector<String> assembler;
    private Stack<Integer> labels;
    private Integer cont;
    private Integer numero;
    private Integer cte;
    
    
    public GeneradorCodigo(TablaSimbolo tabla, Stack<Integer> l) {
        pilaCodigo = new Stack<String>();
        log = new Logger("codigo.asm");
        assembler = new Vector<String>();
        numero = 0;
        cte = 0;
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
        operadores.put("=", "=");
        operadores.put("<=", "<=");
        operadores.put(">=", ">=");
        operadores.put("<", "<");
        operadores.put(">", ">");
        operadores.put("<>", "<>");
        operadores.put(":=", ":=");
        operadores.put("&", "&");
        operadores.put("^", "^");
        operadores.put("JMP", "JMP");
        operadores.put("JLE", "JLE");
        operadores.put("JGE", "JGE");
        operadores.put("JE", "JE");
        operadores.put("JNE", "JNE");
        operadores.put("JA", "JA");
        operadores.put("JL", "JL");
        operadores.put("print", "print");
        labels = l;
        cont = 0;
    }

    public void imprimirCodigo() {
        for (int i = 0; i < pilaCodigo.size(); i++) {
            log.addLogger(pilaCodigo.get(i));
        }
    }

    public void ejecutarSuma(String der, String izq) {
        assembler.add("FLD " + der +" ; carga el valor de la derecha");
        assembler.add("FADD " + izq+" ; suma el valor del st con el valor de la izq");
        assembler.add("FIST cte"+cte+" ; guarda el resultado");
        pilaCodigo.push("cte"+cte);
        log.addLogger("cte"+cte+" DD ?");
        cte++;
    }

//    public void ejecutarResta(String der, String izq) {
//
//        log.addLogger("MOV ax," + der);
//        log.addLogger("SUB ax," + izq);
//        log.addLogger("MOV aux" + cont.toString() + ", ax");
//        pilaCodigo.add("aux" + cont.toString());
//        cont++;
//
//    }

//    public void ejecutarMutiplicar(String der, String izq) {
//        log.addLogger("MOV ax," + der);
//        log.addLogger("IMUL " + izq);
//        log.addLogger("MOV aux" + cont + ", ax");
//        pilaCodigo.add("aux" + cont.toString());
//        cont++;
//
//
//    }

//    public void ejecutarDividir(String der, String izq) {
//
//        log.addLogger("MOV ax," + der);
//        log.addLogger("DIV dx," );
//        log.addLogger("IDIV" + izq);
//        log.addLogger("MOV aux" + cont.toString() + ", ax");
//        pilaCodigo.add("aux" + cont.toString());
//        cont++;
//        
//    }

    public void ejecutarAsignacion(String der, String izq) {
        assembler.add("FILD " + der + " ; carga el valor de der en el st"); //guardo en el registro st(0) el valor que voy a asignar
        assembler.add("FIST "+izq + " ; carga el valor de st en izq");
    }

    public void ejecutarSalto(String salto, String tipo) {
//    	log.addLogger(tipo+labels.firstElement().toString()+" DB "+labels.firstElement().toString());
//    	assembler.add("MOV edx, " + tipo+labels.firstElement().toString());
    	assembler.add(tipo +" Label"+labels.firstElement().toString());
    }
    
    
    public void verificarLimite(String valor, Integer l){
        //
        log.addLogger("MOV ax,"+valor);
        log.addLogger("CMP ax,"+l);
        log.addLogger("JLE _LIMOK");
        log.addLogger("CALL ERROR_LIMITE");
        log.addLogger("CALL TERMINAR");
        log.addLogger("_LIMOK:");
        
    }
    public void finArreglo(String ofs, String valor){
        log.addLogger("ADD bx, "+ofs); // seteo el bx que es la posicion donde se guardara el valor a asignar
        pilaCodigo.add("word ptr[bx]"); //guardo en el arreglo el valor que esta en ax

    }

    public void ejecutarComparador(String izq,String der,String varAux){
    	assembler.add("FILD "+ izq);
    	assembler.add("FCOMP "+ der);
    	assembler.add("FSTSW ax");
    	assembler.add("SAHF");
    }
    
    
    public void recorrerPolaca(Vector<String> polaca) {

        for (int i = 0; i < polaca.size(); i++) {
            float result = 0;
            String varAux = polaca.get(i);
            if (!labels.isEmpty() && i == labels.firstElement()){
            	assembler.add("Label"+i+":");
            }
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
//                    ejecutarResta(id1,id2);
                }
                //multiplicar desapilando el ultimo y ante ultimo y el resultado se apila
                if("*".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
//                    ejecutarMutiplicar(id1,id2);
                }
                //dividir desapilando el ultimo y ante ultimo y el resultado se apila
                if("/".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
//                    ejecutarDividir(id1,id2);
                }                
                if (":=".equals(varAux)){
                    String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarAsignacion(id1,id2);
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
                    StringBuffer bs = new StringBuffer(base);
                    Simbolo aux = new Simbolo(bs, "ARRAY FLOAT");
                    aux = ts.existeSimbolo(aux);
                    int limite = Integer.valueOf(aux.getTamanio());
                    verificarLimite(base, limite);
                    pilaCodigo.add("OFFSET "+base);
                    
                }
                if ("&".equals(varAux)){
                    finArreglo(pilaCodigo.pop(),"123");
                }
                if ("print".equals(varAux)){
                	String base = pilaCodigo.pop();
                       imprimirCadena(base);
                   }
                if ("=".equals(varAux)){
                	String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarComparador(id1,id2,varAux);
                }
                if ("<".equals(varAux)){
                	String id1 = pilaCodigo.pop();   
                    String id2 = pilaCodigo.pop();
                    ejecutarComparador(id1,id2,varAux);
                }
             
            } else {
            	   
                Simbolo aux = ts.existeSimbolo(new Simbolo(new StringBuffer(varAux),""));   
                if (aux !=null && aux.getTipoVariable().equals("NUMERO")){
                	pilaCodigo.push("cte"+cte);//apilo;
                	log.addLogger("cte"+cte+" DD "+varAux);
                	cte++;
                }else
                	pilaCodigo.push(varAux);//apilo;
                	
                                            
            }
        }
        log.addLogger(".code");
        log.addLogger("start:");
        for (int i=0; i<assembler.size();i++){
        	log.addLogger(assembler.get(i));	
        }
        
        log.addLogger("ERROR_LIMITE:");
        log.addLogger("TERMINAR:");
        log.addLogger("invoke ExitProcess, 0");
        log.addLogger("end start");

    }

    public boolean esOperador(String varAux) {
        return (operadores.contains(varAux));
    }

    public void addTablaSimbolo() {
        ArrayList<Simbolo> elem = ts.getTabla();
        log.addLogger(".data");

        String tipo = "";
        String aux = "";
        for (int i = 0; i < elem.size(); i++) {
            tipo = elem.get(i).getTipoVariable();
            if (tipo.equals("FLOAT")) {
                log.addLogger(elem.get(i).getValor().toString() + " DD " + "?");
            }
            if (tipo.equals("ARRAY FLOAT")) {
                log.addLogger(elem.get(i).getValor().toString() +" dw "+ elem.get(i).getTamanio() + " DUP " + "(0)");
            }
//            if (tipo.equals("NUMERO")) {
//            	for (int j=0; j< elem.get(i).getAccesos();j++){
//            	log.addLogger("cte"+numero+ " DD " + elem.get(i).getValor());
//            	numero++;
//            	}
//            }
            if (tipo.equals("STRING")) {
                String copia ="";
            	aux = elem.get(i).getValor().toString().replace("'", "");
                copia = aux.replace(",", "");
                copia = copia.replace(" ", "") ;
                log.addLogger(copia+ " db " + "\"" + aux + "\", 0");
            }
        
        }

    }

    public void imprimir() {
        log.imprimir();
    }

    private void imprimirCadena(String cadena) {
    	cadena = cadena.replace(",", "");
    	cadena = cadena.replace(" ", "");
    	cadena = cadena.replace("'", "");
    	assembler.add("invoke MessageBox, NULL, addr "+cadena+", addr "+cadena+", MB_OK");
    	
    }
}
