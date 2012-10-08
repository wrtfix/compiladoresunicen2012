/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author wrtfix
 */
public class PolacaInversa {
        //private Vector<Object> posiciones;
	private Stack<Integer> pila;
	private TablaSimbolo ts;
	private Vector<Integer> labels = new Vector<Integer>();
        private Vector<String> pi;
        
	public PolacaInversa(){
		//ts = t;
		//posiciones = new Vector<Object>();
		pila = new Stack<Integer>();
                pi = new Vector<String>();
	}


        // Este metodo se utiliza para ir agregando los espacios cuando se encuentra una seleccion o bucle
	public void add(String  simbolo){
                pi.add(simbolo);
        }
	
	public void apilar(){
            System.out.println("entro");
		pila.add(pi.size()-2);
	}
        
        
        public void imprimirPolacaInversa() {
        for (int i = 0; i < pi.size(); i++) {
            System.out.println(pi.elementAt(i));
        }
    }

//	public void apilarBI(){
//		pila.add(posiciones.size()-2);
//	}
//	
//	public void desapilar(){
//		Integer aux=pila.pop();
//		Integer pos = posiciones.size()+1;
//		labels.add(pos);
//		posiciones.set(aux, pos);
//	}

	public void desapilar(){
//		Integer aux=pila.pop()+1;
//		Integer pos = posiciones.size()-2;
//		labels.add(aux);
//		posiciones.set(pos ,aux);
            
            Integer pos = (Integer) pila.elementAt(pila.size());
            pi.add(pos.intValue(),String.valueOf(pila.size()));
            pila.pop();
	}
	
	public String toString(){
		String show = "Polaca Inversa\n--------------------\n";
//		for(int i = 0; i < posiciones.size();i++){
//			Object elem = posiciones.get(i);
//			show += (i+1)+": ";
//			if(elem instanceof Token){
////				show += ((Token) elem).get(Constantes.VALOR);
//			}else{
//				show += elem;
//			}
//			show += "\n";
//		}
		return show.substring(0, show.length()-1) + "\nFin Polaca Inversa";
	}
	
//	public int size(){
//		return posiciones.size();
//	}
//	
//	public Object get(int i){
//		return posiciones.get(i);
//	}
//	
	public Vector<Integer> getLabels(){
		return labels;
	}


}
