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
	private Integer cte;
	private Integer limite;
        
	public GeneradorCodigo(TablaSimbolo tabla, Stack<Integer> l,String path) {
		pilaCodigo = new Stack<String>();
		log = new Logger(path.split("\\.")[0]+".asm");
		assembler = new Vector<String>();

		cte = 0;
		ts = tabla;
		limite = 0;
		posiciones = new Stack<Integer>();
		for (int i = 0; i < l.size(); i++) {
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
		operadores.put("+i", "+i");
		operadores.put("-i", "-i");
		operadores.put("*i", "*i");
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
		if (izq.contains("dword")){
			assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
			izq = "dword ptr [ebx]";
		}
		if (der.contains("dword")){
			assembler.add("MOV ecx, "+der.split("\\[")[1].split("\\]")[0]);
			der = "dword ptr [ecx]";
		}
				
		assembler.add("FLD " + izq);
		assembler.add("FADD " + der);
		assembler.add("FSTP _aux" + contaux);

		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;

	}

	public void ejecutarSumaEntera(String izq, String der) {
		assembler.add("MOV ebx," + izq);
		assembler.add("MOV eax, "+der);
		assembler.add("ADD ebx, eax");
		assembler.add("MOV _aux" + contaux+", ebx");
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
	}

	public void ejecutarResta(String der, String izq) {
		if (izq.contains("dword")){
			assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
			izq = "dword ptr [ebx]";
		}
		if (der.contains("dword")){
			assembler.add("MOV ecx, "+der.split("\\[")[1].split("\\]")[0]);
			der = "dword ptr [ecx]";
		}
				
		assembler.add("FLD " + izq );
		assembler.add("FSUB " + der);
		assembler.add("FST _aux" + contaux + " ; guarda el resultado");
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
	}

	public void ejecutarRestaEntera(String der, String izq) {
		if (izq.contains("dword")){
			assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
			izq = "dword ptr [ebx]";
		}
		assembler.add("MOV eax, " + izq);
		assembler.add("SUB eax, " + der);
		assembler.add("MOV _aux" + contaux+", eax");
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
	}

	public void ejecutarMutiplicar(String der, String izq) {
            	if (izq.contains("dword")){
			assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
			izq = "dword ptr [ebx]";
		}
		if (der.contains("dword")){
			assembler.add("MOV ecx, "+der.split("\\[")[1].split("\\]")[0]);
			der = "dword ptr [ecx]";
		}
		assembler.add("FLD " + izq );
		assembler.add("FMUL "+ der);
		assembler.add("FST "+ "_aux"+ contaux);
		assembler.add("FCOMP _oversuper");
		assembler.add("FSTSW ax");
		assembler.add("SAHF");
		assembler.add("JNBE ERROR_OVERFLOW");
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
	}

	public void ejecutarMultEntera(String izq, String der) {
		assembler.add("MOV eax, " + der);
		assembler.add("MOV ecx, " + izq);
		assembler.add("MUL ecx");
		assembler.add("MOV _aux" + contaux + ", ecx");
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
		
	}

	public void ejecutarDividir(String der, String izq) {
		if (izq.contains("dword")){
			assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
			izq = "dword ptr [ebx]";
		}
		if (der.contains("dword")){
			assembler.add("MOV ecx, "+der.split("\\[")[1].split("\\]")[0]);
			der = "dword ptr [ecx]";
		}
		
		assembler.add("FLD " + der);
		
		assembler.add("FCOMP _cero");
		assembler.add("FSTSW ax ");
		assembler.add("SAHF");
		assembler.add("JE ERROR_DIVISION");
		assembler.add("FDIV " + izq);
		assembler.add("FST _aux" + contaux);
		pilaCodigo.push("_aux" + contaux);
		log.addLogger("_aux" + contaux + " DD ?");
		contaux++;
	}

	public void ejecutarAsignacion(String der, String izq) {
               if (izq.contains("dword")){
                        assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
                        izq = "dword ptr[ebx]";                        
                }
                if (der.contains("dword")){
                       assembler.add("MOV eax, "+der.split("\\[")[1].split("\\]")[0]);
                       der = izq = "dword ptr[eax]";             
                }
                if(der.contains("cte")){
                    assembler.add("FLD " + der);
                    assembler.add("FSTP " + izq);
                }else{
                    assembler.add("FLD " + der);
                    assembler.add("FSTP " + izq);
                }
	}

	public void ejecutarSalto(String salto, String tipo) {

		assembler.add(tipo + " Label" + salto);
	}

	public void ejecutarControlArreglo(String indice, String lim, boolean con) {
		if (indice.contains("dword")){
			assembler.add("MOV ebx, "+indice.split("\\[")[1].split("\\]")[0]);
                        assembler.add(0,"FLD dword ptr [ebx]");
			assembler.add(1,"FISTP _aux"+contaux);
			log.addLogger("_aux" + contaux + " DD ?");
			
			// Verifico los limites
                        assembler.add("XOR eax, eax");
			assembler.add("MOV eax, _aux"+contaux);
			contaux++;
			assembler.add("CMP eax, " + lim);
			assembler.add("JG ERROR_LIMITESUP");
			assembler.add("XOR eax, eax");
                     	assembler.add("MOV edx, "+indice.split("\\[")[1].split("\\]")[0]);
                        assembler.add("MOV eax, dword ptr[edx]");
                        assembler.add("CMP eax, _uno");
			assembler.add("JL ERROR_LIMITEINF");
		}else
		{
                                if (indice.contains("aux")){
                                    assembler.add("FLD " + indice);
                                    assembler.add("FISTP " + indice);
                                }else{
                                    assembler.add(0,"FLD " + indice);
                                    assembler.add(1,"FISTP " + indice);
                                }

                                assembler.add("XOR eax, eax");
				assembler.add("MOV eax, " + indice);
				if (lim.contains("dword")){
                                    assembler.add("MOV edx, " + lim.split("\\[")[1].split("\\]")[0]);
                                    lim = "dowrd ptr [edx]";
                                }
                
                                assembler.add("CMP eax, " + lim);
				assembler.add("JG ERROR_LIMITESUP");
				assembler.add("XOR eax, eax");
                                assembler.add("MOV eax, "+indice);
				assembler.add("CMP eax, 1");
				assembler.add("JL ERROR_LIMITEINF");
		}
	}
        /*
         * Realiza las instrucciones necesarias para generar el assembler de una comparacion
         */
	public void ejecutarComparador(String der,String izq) {

		if (izq.contains("dword")) {
				if (der.contains("dword")){			
        				assembler.add("MOV eax, "+der.split("\\[")[1].split("\\]")[0]);
                                        assembler.add("FLD dword ptr [eax]");
                                        assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
					assembler.add("FLD dword ptr [ebx]");
					assembler.add("FCOMP");
					assembler.add("FSTSW ax");
					assembler.add("SAHF");
				}
				else
				{
					assembler.add("FLD "+der);	
                                        assembler.add("MOV ebx, "+izq.split("\\[")[1].split("\\]")[0]);
					assembler.add("FLD dword ptr [ebx] ");
					assembler.add("FCOMP");
					assembler.add("FSTSW ax");
					assembler.add("SAHF");
				}
				contaux++;
			}else{
				
				if (der.contains("dword")){	
					assembler.add("MOV ebx, "+der.split("\\[")[1].split("\\]")[0]);
					assembler.add("FLD dword ptr [ebx] ");
                                        assembler.add("FLD "+izq);	
					assembler.add("FCOMP");
					assembler.add("FSTSW ax ");
					assembler.add("SAHF ");
				}
				else
				{
					assembler.add("FLD "+der);	
					assembler.add("FLD "+izq);	
                                        assembler.add("FCOMP ");
					assembler.add("FSTSW ax ");
					assembler.add("SAHF ");
				}
			}
		
	}
        /*
         * Recorre la polaca inversa y genera el assembler de los elementos que va encontrando
         */
	public void recorrerPolaca(Vector<String> polaca) {
		int i;
		for (i = 0; i < polaca.size(); i++) {
			float result = 0;
			// agregar esto porque rompe
			if (!polaca.isEmpty()) {
				String varAux = polaca.get(i);
				if (!" ".equals(varAux)) {
					// realizo el etiquetado de saltos
					if (!posiciones.isEmpty() && posiciones.contains(i)) {
						assembler.add("");
                                                assembler.add("Label_" +i+ ":");
                                                
						for (int j = 0; j < posiciones.size(); j++)
							if (posiciones.get(j).equals(i)) {
								posiciones.remove(j);
							}
                                                

					}
					if (esOperador(varAux) && !pilaCodigo.empty()) {
						// sumo desapilando el ultimo y ante ultimo y el
						// resultado se apila

						if ("+".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarSuma(id1, id2);
						}
						if ("+i".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarSumaEntera(id1,id2);
						}
						// resta desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("-".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarResta(id1, id2);
						}
						if ("-i".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarRestaEntera(id1, id2);
						}
						// multiplicar desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("*".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarMutiplicar(id1, id2);
						}
						if ("*i".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarMultEntera(id1,id2);
						}
						// dividir desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("/".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarDividir(id1, id2);
						}
						if (":=".equals(varAux) && pilaCodigo.size() > 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarAsignacion(id1, id2);
						}
						if ("JNB".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNA".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNE".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNBE".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNGE".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JE".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JMP".equals(varAux) && pilaCodigo.size() > 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
                                                        
						}

						if ("^".equals(varAux) && pilaCodigo.size() > 0) {
							String base = pilaCodigo.pop();
							assembler.add("MOV _base" + base + ", OFFSET "+ base);

							String decl = "_base" + base + " DD ?";
							if (!log.existe(decl))
								log.addLogger(decl);
							pilaCodigo.push("_base" + base);
							
                                                        assembler.add(""); 

						}
						if ("&".equals(varAux) && pilaCodigo.size() > 0) {
							String aux = pilaCodigo.pop();// saca el resultado
															// de offset +
															// indice
							

							pilaCodigo.add(" dword ptr [" + aux + "]");
							assembler.add("");
							

						}
						if ("print".equals(varAux) && pilaCodigo.size() > 0) {
							String base = pilaCodigo.pop();
							imprimirCadena(base);
						}
						// Para todas las comparaciones
						if ("=".equals(varAux) || "<".equals(varAux)
								|| ">".equals(varAux) || ">=".equals(varAux)
								|| "<=".equals(varAux) || "<>".equals(varAux)
								&& pilaCodigo.size() > 1) {
							if (!labels.empty()) {
								String id1 = pilaCodigo.pop();
								String id2 = pilaCodigo.pop();
								ejecutarComparador(id1, id2);
								assembler.add("");
							}

						}
						if ("1".equals(varAux)) {
							pilaCodigo.push("_uno");

						}
						if ("4".equals(varAux)) {
							pilaCodigo.push("_cuatro");
						}

					} else {
						if (varAux.contains("#") && pilaCodigo.size() > 0) {
							String indice = pilaCodigo.pop();
							String res = varAux.split("#")[1];
							StringBuffer bs = new StringBuffer(res);
							Simbolo aux = new Simbolo(bs, "ARRAY FLOAT");
							aux = ts.existeSimbolo(aux);
							Integer lim = Integer.valueOf(aux.getTamanio()
									.split("\\.")[0]);
							String decl = "_limite_" + res + " DD " + lim;
							if (!log.existe(decl))
								log.addLogger(decl);
							boolean con=true;
//                                                        
                                                        ejecutarControlArreglo(indice, "_limite_" + res, con);
							pilaCodigo.push(indice);
							limite++;
							assembler.add("");
						} else {
							Simbolo aux = ts.existeSimbolo(new Simbolo(
									new StringBuffer(varAux), ""));
							if (aux != null	&& aux.getTipoVariable().equals("NUMERO")) {
								pilaCodigo.push("_cte" + cte);// apilo;
								log.addLogger("_cte" + cte + " DD " + varAux);
								cte++;
							} else
								pilaCodigo.push("_" + varAux);// apilo;
						}
					}
				}
			}
		}
		assembler.add("JMP TERMINAR");
		log.addLogger("_overflow db \"hay overflow en la operacion \", 0");
		log.addLogger("_limitesup db \"fuera del limite superior del arreglo\", 0");
                log.addLogger("_limiteinf db \"fuera del limite inferior del arreglo\", 0");
		log.addLogger("_division db \"division por cero\", 0");
		log.addLogger("_oversuper DD 3.40282347e+38");
		log.addLogger("_overinf DD 1.17549435e-38");
		log.addLogger("_cuatro DD 4");
		log.addLogger("_uno DD 1");
		log.addLogger("_cero DD 0");
		log.addLogger(".code");
		log.addLogger("start:");

		assembler.add("ERROR_DIVISION:");
		assembler
				.add("invoke MessageBox, NULL, addr _division, addr _division, MB_OK");
		assembler.add("JMP TERMINAR");
		assembler.add("ERROR_LIMITESUP:");
		assembler
				.add("invoke MessageBox, NULL, addr _limitesup, addr _limitesup, MB_OK");
		assembler.add("JMP TERMINAR");
		assembler.add("ERROR_LIMITEINF:");
		assembler
				.add("invoke MessageBox, NULL, addr _limiteinf, addr _limiteinf, MB_OK");
		assembler.add("JMP TERMINAR");
                assembler.add("ERROR_OVERFLOW:");
		assembler
				.add("invoke MessageBox, NULL, addr _overflow, addr _overflow, MB_OK");
		assembler.add("JMP TERMINAR");

		while (!posiciones.isEmpty())
			assembler.add("Label_" + posiciones.pop() + ": JMP TERMINAR");

		assembler.add("TERMINAR:");
		assembler.add("invoke ExitProcess, 0");
		assembler.add("end start");
		// se mete el codigo generado luego de leer toda la polaca
		for (int j = 0; j < assembler.size(); j++) {
			log.addLogger(assembler.get(j));
		}

	}

	public boolean esOperador(String varAux) {
		return (operadores.contains(varAux));
	}
        /*
         * Recorre la tabla de simbolos con el fin de declararla en assembler en el .data
         */
	public void addTablaSimbolo() {
		ArrayList<Simbolo> elem = ts.getTabla();
		log.addLogger(".data");

		String tipo = "";
		String aux = "";
		for (int i = 0; i < elem.size(); i++) {
			tipo = elem.get(i).getTipoVariable();
			if (tipo.equals("FLOAT")) {
				log.addLogger("_" + elem.get(i).getValor().toString() + " DD "
						+ "?");
			}
			if (tipo.equals("ARRAY FLOAT")) {
				log.addLogger("_" + elem.get(i).getValor().toString() + " DD "
						+ elem.get(i).getTamanio().split("\\.")[0] + " DUP(?)");
			}

			if (tipo.equals("STRING")) {
				String copia = "";
				aux = elem.get(i).getValor().toString().replace("'", "");
				copia = aux.replace(",", "");
				copia = copia.replace(" ", "");
                                
				log.addLogger("_" + copia + " db " + "\"" + aux + "\", 0");
			}

		}

	}
        /*
         * Genera el archivo .asm
         */
	public void imprimir() {
		log.imprimir();
	}
        /*
         * Realiza la instruccion assembler necesaria para imprimir un cartel por pantalla.
         */
	private void imprimirCadena(String cadena) {
		cadena = cadena.replace(",", "");
		cadena = cadena.replace(" ", "");
		cadena = cadena.replace("'", "");
		assembler.add("invoke MessageBox, NULL, addr " + cadena + ", addr "
				+ cadena + ", MB_OK");

	}
}
