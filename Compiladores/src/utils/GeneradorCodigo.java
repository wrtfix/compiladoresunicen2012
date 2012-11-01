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
    private Stack<Integer> posiciones;
    private Integer contaux;
    private Integer numero;
    private Integer cte;
    private Integer limite;
    
    
    public GeneradorCodigo(TablaSimbolo tabla, Stack<Integer> l) {
        pilaCodigo = new Stack<String>();
        log = new Logger("codigo.asm");
        assembler = new Vector<String>();
        numero = 0;
        cte = 0;
        ts = tabla;
        limite=0;
        posiciones = new Stack<Integer>();
        for (int i=0; i<l.size();i++){
        	System.out.println(l.get(i));
        	posiciones.add(l.get(i));
        }
        
        
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
        operadores.put("JNB", "JNB");
        operadores.put("JNA", "JNA");
        operadores.put("JNE", "JNE");
        operadores.put("JNBE", "JNBE");
        operadores.put("JNGE", "JNGE");
        operadores.put("JE", "JE");
        operadores.put("print", "print");
        operadores.put("1", "1");
        operadores.put("4", "4");
        labels = l;
        contaux = 0;
    }

    public void imprimirCodigo() {
        for (int i = 0; i < pilaCodigo.size(); i++) {
            log.addLogger(pilaCodigo.get(i));
        }
    }

    public void ejecutarSuma(String der, String izq) {
        assembler.add("FLD " + der +" ; carga el valor de la derecha en el st");
        if (izq.contains("[")){
        	assembler.add("FIADD " + izq+" ; suma el valor del st con el valor de la izq");
        }else{
        	assembler.add("FIADD " + izq+" ; suma el valor del st con el valor de la izq");
        }
        assembler.add("FST aux"+contaux+" ; guarda el resultado");
        pilaCodigo.push("aux"+contaux);
        log.addLogger("aux"+contaux+" DD ?");
        contaux++;
    }
    public void ejecutarResta(String der, String izq) {
        assembler.add("FLD " + izq +" ; carga el valor de la derecha en el st");
        assembler.add("FSUB " + der +" ; resta el valor del st con el valor de la izq");
        assembler.add("FST aux"+contaux+" ; guarda el resultado");
        pilaCodigo.push("aux"+contaux);
        log.addLogger("aux"+contaux+" DD ?");
        contaux++;
    }
    
    public void ejecutarMutiplicar(String der, String izq) {
        assembler.add("FLD " + izq +" ; carga el valor de la izq en el st");
        assembler.add("FMUL " + der +" ; multiplica el valor del st con el valor de la der y se guarda en st");
        assembler.add("FST " + "aux"+contaux +" ; multiplica el valor del st con el valor de la der y se guarda en st");
        assembler.add("FCOMP oversuper ; compara el resultado de la operacion con over");
        assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
        assembler.add("JNBE ERROR_OVERFLOW; toma los bits menos significativos del registro ax");
    	pilaCodigo.push("aux"+contaux);
        log.addLogger("aux"+contaux+" DD ?");
        contaux++;
    }
    public void ejecutarDividir(String der, String izq) {
    	assembler.add("FLD "+ der +" ; carga el el st el valor izq a comparar");
//    	assembler.add("FADD cero ; compara el st con el valor de la derecha"); 
    	assembler.add("FCOMP cero ; compara el st con el valor de la derecha");
        assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
    	assembler.add("JE ERROR_DIVISION");
//    	assembler.add("FLD "+ der +" ; carga el el st el valor izq a comparar");
    	assembler.add("FDIV " + izq +" ; resta el valor del st con el valor de la izq");
        assembler.add("FST aux"+contaux+" ; guarda el resultado");
        pilaCodigo.push("aux"+contaux);
        log.addLogger("aux"+contaux+" DD ?");
        contaux++;
    }



    public void ejecutarAsignacion(String der, String izq) {
        assembler.add("FLD " + der + " ; carga el valor de der en el st"); //guardo en el registro st(0) el valor que voy a asignar
        if (izq.contains("[")){
        	assembler.add("FIST "+izq + " ; carga el valor de st en izq");
        }else{
        	assembler.add("FST "+izq + " ; carga el valor de st en izq");
        }
    }

    public void ejecutarSalto(String salto, String tipo) {
    	if (!labels.isEmpty()){
	    	assembler.add(tipo +" Label"+labels.firstElement().toString());
	    	labels.remove(0);
    	}
    }
    
    
    public void finArreglo(String ofs, String nombre){
//    	assembler.add("FLD "+pos);
    	assembler.add("MOV ebx, "+ofs); // seteo el bx que es la posicion donde se guardara el valor a asignar
        pilaCodigo.add(nombre+"[ebx]"); //guardo en el arreglo el valor que esta en ax
    }

    public void ejecutarComparador(String izq,String der){
    	assembler.add("FLD "+ izq +" ; carga el el st el valor izq a comparar");
   		if (der.contains("[")){
   			assembler.add("FICOMP "+ der +" ; compara el st con el valor de la derecha");
   		}else{
    	assembler.add("FCOMP "+ der +" ; compara el st con el valor de la derecha");}
    	assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
    }
    
    
    public void recorrerPolaca(Vector<String> polaca) {
		int i;
		for (i = 0; i < polaca.size(); i++) {
			float result = 0;
			if (!polaca.isEmpty()) {
				String varAux = polaca.get(i);
				if (!" ".equals(varAux)) {
					if (!posiciones.isEmpty() && i == posiciones.firstElement()) {
						assembler
								.add("Label" + posiciones.firstElement() + ":");
						posiciones.remove(0);
					}
					if (esOperador(varAux)) {
						// sumo desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("+".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarSuma(id1, id2);
						}
						// resta desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("-".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarResta(id1, id2);
						}
						// multiplicar desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("*".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarMutiplicar(id1, id2);
						}
						// dividir desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("/".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarDividir(id1, id2);
						}
						if (":=".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarAsignacion(id1, id2);
						}
						if ("JNB".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNA".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNE".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNBE".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNGE".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JE".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JMP".equals(varAux)) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("^".equals(varAux)) {
							String base = pilaCodigo.pop();
							StringBuffer bs = new StringBuffer(base);
							Simbolo aux = new Simbolo(bs, "ARRAY FLOAT");
							aux = ts.existeSimbolo(aux);
							int lim = Integer.valueOf(aux.getTamanio());
							log.addLogger("limite" + limite + " DD " + lim);
							pilaCodigo.add(base);
							pilaCodigo.add("["+base+"]");
						}
						if ("&".equals(varAux)) {
							String ofs = pilaCodigo.pop();
							String nombre = pilaCodigo.pop();
							
							finArreglo(ofs,nombre);
							limite++;
							// finArreglo(pilaCodigo.pop(),"123");
						}
						if ("print".equals(varAux)) {
							String base = pilaCodigo.pop();
							imprimirCadena(base);
						}
						// Para todas las comparaciones
						if ("=".equals(varAux) || "<".equals(varAux)
								|| ">".equals(varAux) || ">=".equals(varAux)
								|| "<=".equals(varAux) || "<>".equals(varAux)) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarComparador(id1, id2);
						}
						if ("1".equals(varAux)){
							pilaCodigo.push("uno");
							
						}
						if ("4".equals(varAux)){
							pilaCodigo.push("cuatro");
						}	
						
					} else {
						
						Simbolo aux = ts.existeSimbolo(new Simbolo(
								new StringBuffer(varAux), ""));
						if (aux != null
								&& aux.getTipoVariable().equals("NUMERO")) {
							pilaCodigo.push("cte" + cte);// apilo;
							log.addLogger("cte" + cte + " DD " + varAux);
							cte++;
						} else
							pilaCodigo.push(varAux);// apilo;

					}
				}
			}
		}
		assembler.add("JMP TERMINAR");
		log.addLogger("overflow db \"hay overflow en la operacion \", 0");
		log.addLogger("limite db \"fuera del limite del arreglo\", 0");
		log.addLogger("division db \"division por cero\", 0");
		log.addLogger("oversuper DD 3.40282347e+38");
		log.addLogger("overinf DD 1.17549435e-38");
		log.addLogger("cuatro DD 4");
		log.addLogger("uno DD 1");
		log.addLogger("cero DD 0");
		log.addLogger(".code");
		log.addLogger("start:");

		assembler.add("ERROR_DIVISION:");
		assembler
				.add("invoke MessageBox, NULL, addr division, addr division, MB_OK");
		assembler.add("JMP TERMINAR");
		assembler.add("ERROR_LIMITE:");
		assembler
				.add("invoke MessageBox, NULL, addr limite, addr limite, MB_OK");
		assembler.add("JMP TERMINAR");
		assembler.add("ERROR_OVERFLOW:");
		assembler
				.add("invoke MessageBox, NULL, addr overflow, addr overflow, MB_OK");
		assembler.add("JMP TERMINAR");

		if (!posiciones.isEmpty() && posiciones.size() == 1)
			assembler.add("Label" + posiciones.pop() + ": JMP TERMINAR");

		assembler.add("TERMINAR:");
		assembler.add("invoke ExitProcess, 0");
		assembler.add("end start");

		for (int j = 0; j < assembler.size(); j++) {
			log.addLogger(assembler.get(j));
		}

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
