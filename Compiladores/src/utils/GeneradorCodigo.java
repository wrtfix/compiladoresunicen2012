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
    private Integer contaux, contBase;
    private Integer numero;
    private Integer cte;
    private Integer limite;
    
    private boolean arreglo=false;
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
        contBase = 0;
    }

    public void imprimirCodigo() {
        for (int i = 0; i < pilaCodigo.size(); i++) {
            log.addLogger(pilaCodigo.get(i));
        }
    }

    public void ejecutarSuma(String der, String izq) {
//    	assembler.add("FLD " + der +" ; carga el valor de la derecha en el st");
//        assembler.add("FADD " + izq+" ; suma el valor del st con el valor de la izq");
//        assembler.add("FST _aux"+contaux+" ; guarda el resultado");
//  	 	if (izq.contains("OFFSET")){ 
//                        //assembler.add("PUSH "+izq);
//                         assembler.add("MOV _base"+ contBase+", "+ izq);
//                         log.addLogger("_base"+contBase+" DD ?");                 
//                         assembler.add("FLD base "+contBase);
//                         contBase++;                    
//  	 	}else{
//  	 		assembler.add("FLD "+izq);
//  	 	}  	 		
//  	if (contBase>0){
//            assembler.add("FLD "+izq);
//            assembler.add("FADD "+der);
//            assembler.add("FSTP _aux"+contaux);
//            contBase--;
//        }else{
            assembler.add("FLD "+izq);
        assembler.add("FADD "+der);
  	assembler.add("FSTP _aux"+contaux);
        
        pilaCodigo.push("_aux"+contaux);
        log.addLogger("_aux"+contaux+" DD ?");
        contaux++;

      	
    }
    public void ejecutarSumaEntera(String izq) {        
        assembler.add("MOV ebx,"+ izq);    
        assembler.add("ADD ebx, eax");        
        pilaCodigo.push("ebx");
    }
    public void ejecutarResta(String der, String izq) {
        assembler.add("FLD " + izq +" ; carga el valor de la derecha en el st");
        assembler.add("FSUB " + der +" ; resta el valor del st con el valor de la izq");
        assembler.add("FST _aux"+contaux+" ; guarda el resultado");
        pilaCodigo.push("_aux"+contaux);
        log.addLogger("_aux"+contaux+" DD ?");
        contaux++;
    }
    public void ejecutarRestaEntera(String der, String izq) {
        assembler.add("MOV eax, "+ izq);
        assembler.add("SUB eax, "+ der);
    }
    public void ejecutarMutiplicar(String der, String izq) {
        assembler.add("FLD " + izq +" ; carga el valor de la izq en el st");
        assembler.add("FMUL " + der +" ; multiplica el valor del st con el valor de la der y se guarda en st");
        assembler.add("FST " + "_aux"+contaux +" ; multiplica el valor del st con el valor de la der y se guarda en st");
        assembler.add("FCOMP _oversuper ; compara el resultado de la operacion con over");
        assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
        assembler.add("JNBE ERROR_OVERFLOW; toma los bits menos significativos del registro ax");
    	pilaCodigo.push("_aux"+contaux);
        log.addLogger("_aux"+contaux+" DD ?");
        contaux++;
    }
    public void ejecutarMultEntera(String izq) {        
        assembler.add("MOV ecx,"+ izq);    
        assembler.add("MUL ecx");
    }
    public void ejecutarDividir(String der, String izq) {
    	assembler.add("FLD "+ der +" ; carga el el st el valor izq a comparar");
//    	assembler.add("FADD cero ; compara el st con el valor de la derecha"); 
    	assembler.add("FCOMP _cero ; compara el st con el valor de la derecha");
        assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
    	assembler.add("JE ERROR_DIVISION");
//    	assembler.add("FLD "+ der +" ; carga el el st el valor izq a comparar");
    	assembler.add("FDIV " + izq +" ; resta el valor del st con el valor de la izq");
        assembler.add("FST _aux"+contaux+" ; guarda el resultado");
        pilaCodigo.push("_aux"+contaux);
        log.addLogger("_aux"+contaux+" DD ?");
        contaux++;
    }



    public void ejecutarAsignacion(String der, String izq) {

    		assembler.add( "FLD "+der);
    		assembler.add( "FSTP "+izq);
    }

    public void ejecutarSalto(String salto, String tipo) {

    	assembler.add(tipo +" Label"+salto);
    }
    
    
    public void finArreglo(String ofs,String lim, String base){
    	 
    }
    public void  ejecutarControlArreglo(String indice, String lim){
         //Realizamos una conversion de flotante a entero
         assembler.add("FLD "+indice);
         assembler.add("FIST "+indice);
         // Verifico los limites 
         assembler.add("MOV eax, "+ indice);
         assembler.add("CMP eax, " + lim);
         assembler.add("JG ERROR_LIMITE");
         assembler.add("CMP eax, 1");         
         assembler.add("JL ERROR_LIMITE");


//    	 assembler.add("FCOMP "+ lim +"; compara el st con el valor de la derecha");
//    	 assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
//    	 assembler.add("SAHF ; toma los bits menos significativos del registro ax");
//    	 assembler.add("JNBE ERROR_LIMITE");
    }
    
    public void ejecutarComparador(String izq,String der){
    	assembler.add("FLD "+ der +" ; carga el el st el valor izq a comparar");
    	assembler.add("FCOMP "+ izq +" ; compara el st con el valor de la derecha");
    	assembler.add("FSTSW ax ; guarda el resultado de la operacion en el registro ax");
    	assembler.add("SAHF ; toma los bits menos significativos del registro ax");
    }
    
    
    public void recorrerPolaca(Vector<String> polaca) {
		int i;
		for (i = 0; i < polaca.size(); i++) {
			float result = 0;
                        //agregar esto porque rompe                                                    
			if (!polaca.isEmpty()) {
				String varAux = polaca.get(i);
				if (!" ".equals(varAux)) {
					// realizo el etiquetado de saltos
					if (!posiciones.isEmpty() && posiciones.contains(i)) {
						assembler
								.add("Label_" + posiciones.firstElement() + ":");
						for (int j=0; j<posiciones.size();j++)
							if (posiciones.get(j).equals(i)){
								posiciones.remove(j);
							}
						
					}
					if (esOperador(varAux) && !pilaCodigo.empty()) {
						// sumo desapilando el ultimo y ante ultimo y el
						// resultado se apila
                                            
						if ("+".equals(varAux) && pilaCodigo.size()> 1 ) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarSuma(id1, id2);
						}
                                                if ("+i".equals(varAux) && pilaCodigo.size()> 1 ) {
							String id1 = pilaCodigo.pop();							
							ejecutarSumaEntera(id1);
						}
						// resta desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("-".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarResta(id1, id2);
						}
                                                if ("-i".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarRestaEntera(id1, id2);
						}
						// multiplicar desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("*".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarMutiplicar(id1, id2);
						}
                                                if ("*i".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();							
							ejecutarMultEntera(id1);
						}
						// dividir desapilando el ultimo y ante ultimo y el
						// resultado se apila
						if ("/".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarDividir(id1, id2);
						}
						if (":=".equals(varAux)&& pilaCodigo.size()> 1) {
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							ejecutarAsignacion(id1, id2);
						}
						if ("JNB".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNA".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNE".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNBE".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JNGE".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JE".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
						if ("JMP".equals(varAux)&& pilaCodigo.size()> 0) {
							String salto = pilaCodigo.pop();
							ejecutarSalto(salto, varAux);
						}
                                               
						if ("^".equals(varAux)&& pilaCodigo.size()> 0) {
                                                        contBase++;
							String base = pilaCodigo.pop();// el nombre del arreglo
                                                        assembler.add("MOV _base"+ base+", OFFSET "+ base);
                                                        //log.addLogger("_base"+contBase+ " DD ?");                                                                         
                                                        String decl = "_base"+base+ " DD ?";                 
                                                        if(!log.existe(decl))
                                                            log.addLogger(decl);
                                                        //log.addLogger("_base"+base+ " DD ?");                 
                                                        //assembler.add("FLD _base"+base);                                                                                                                   
                                                        pilaCodigo.push("_base"+base);//guarda el offset
                                                        //contBase++;     
							//String res = base.split("_")[1];
							//StringBuffer bs = new StringBuffer(res);
							//Simbolo aux = new Simbolo(bs, "ARRAY FLOAT");
							//aux = ts.existeSimbolo(aux);
							//int lim = Integer.valueOf(aux.getTamanio());
							//log.addLogger("_limite" + limite + " DD " + lim+".");
//							pilaCodigo.push(base);
//							pilaCodigo.push("_limite" + limite);
//							pilaCodigo.push("OFFSET "+base);
							
							
						}
						if ("&".equals(varAux)&& pilaCodigo.size()> 0) {
                                                    String aux = pilaCodigo.pop();// saca el resultado de offset + indice

							pilaCodigo.add(" dword ptr ["+aux+"]");


						}
						if ("print".equals(varAux)&& pilaCodigo.size()> 0) {
							String base = pilaCodigo.pop();
							imprimirCadena(base);
						}
						// Para todas las comparaciones
						if ("=".equals(varAux) || "<".equals(varAux)
								|| ">".equals(varAux) || ">=".equals(varAux)
								|| "<=".equals(varAux) || "<>".equals(varAux)
                                                        && pilaCodigo.size()> 1) {
							if(!labels.empty()){
                                                            String l = labels.firstElement().toString();
							
							String id1 = pilaCodigo.pop();
							String id2 = pilaCodigo.pop();
							//assembler.add("Label_"+l+":");
							labels.remove(0);
							ejecutarComparador(id1, id2);
							//pilaCodigo.push("Label"+l);
                                                        }
							
						}
						if ("1".equals(varAux)){
							pilaCodigo.push("_uno");
							
						}
						if ("4".equals(varAux)){
							pilaCodigo.push("_cuatro");
						}	
						
					} else {
						 if (varAux.contains("#")&& pilaCodigo.size()> 0) {
							String indice = pilaCodigo.pop();// recupera el indice del arreglo
							String res = varAux.split("#")[1];
							StringBuffer bs = new StringBuffer(res);
							Simbolo aux = new Simbolo(bs, "ARRAY FLOAT");
							aux = ts.existeSimbolo(aux);
							Integer lim = Integer.valueOf(aux.getTamanio().split("\\.")[0]);                                                        
                                                        String decl = "_limite_" + res + " DD " + lim;
                                                        if(!log.existe(decl))
                                                            log.addLogger(decl);
                                                        ejecutarControlArreglo(indice, "_limite_"+ res);
                                                        pilaCodigo.push(indice);
                                                        limite++;
						}else{
						Simbolo aux = ts.existeSimbolo(new Simbolo(
								new StringBuffer(varAux), ""));
						if (aux != null
								&& aux.getTipoVariable().equals("NUMERO")) {
							pilaCodigo.push("_cte" + cte);// apilo;
							log.addLogger("_cte" + cte + " DD " + varAux);
							cte++;
						} else
							pilaCodigo.push("_"+varAux);// apilo;
                                                 }
					}
				}
			}
		}
		assembler.add("JMP TERMINAR");
		log.addLogger("_overflow db \"hay overflow en la operacion \", 0");
		log.addLogger("_limite db \"fuera del limite del arreglo\", 0");
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
		assembler.add("ERROR_LIMITE:");
		assembler.add("invoke MessageBox, NULL, addr _limite, addr _limite, MB_OK");
		assembler.add("JMP TERMINAR");
		assembler.add("ERROR_OVERFLOW:");
		assembler
				.add("invoke MessageBox, NULL, addr _overflow, addr _overflow, MB_OK");
		assembler.add("JMP TERMINAR");

		if (!posiciones.isEmpty() && posiciones.size() == 1)
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

    public void addTablaSimbolo() {
        ArrayList<Simbolo> elem = ts.getTabla();
        log.addLogger(".data");

        String tipo = "";
        String aux = "";
        for (int i = 0; i < elem.size(); i++) {
            tipo = elem.get(i).getTipoVariable();
            if (tipo.equals("FLOAT")) {
                log.addLogger("_"+elem.get(i).getValor().toString() + " DD " + "?");
            }
            if (tipo.equals("ARRAY FLOAT")) {
                log.addLogger("_"+elem.get(i).getValor().toString() +" DD "+ elem.get(i).getTamanio().split("\\.")[0] + " DUP(?)");
            }

            if (tipo.equals("STRING")) {
                String copia ="";
            	aux = elem.get(i).getValor().toString().replace("'", "");
                copia = aux.replace(",", "");
                copia = copia.replace(" ", "") ;
                log.addLogger("_"+copia+ " db " + "\"" + aux + "\", 0");
            }
        
        }

    }

    public void imprimir() {
        log.imprimir();
    }

    private void consumirArreglo(String base){
    	assembler.add("MOV ax, 3");
    	assembler.add("SUB ax, 1");
    	assembler.add("MUL ax, 4");
    	assembler.add("MOV ebx, dword prt [base]");
    	assembler.add("ADD ax, dword prt [base]");
    	
    	
    	
    }
    
    private void imprimirCadena(String cadena) {
    	cadena = cadena.replace(",", "");
    	cadena = cadena.replace(" ", "");
    	cadena = cadena.replace("'", "");
    	assembler.add("invoke MessageBox, NULL, addr "+cadena+", addr "+cadena+", MB_OK");
    	
    }
}
