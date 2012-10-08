/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author wrtfix
 */
public class TablaSimbolo {

    private ArrayList<Simbolo> tabla;

    public TablaSimbolo() {
        tabla = new ArrayList<Simbolo>();
    }

    public Simbolo existeSimbolo(Simbolo s) {
        Iterator<Simbolo> it = tabla.iterator();
        Simbolo a = null;

        while (it.hasNext() && !s.equals(a)) {
            a = it.next();
        }
        if (s.equals(a)) {
            return a;
        }
        return null;
    }

    public void addSimbolo(Simbolo s) {
        Simbolo aux = existeSimbolo(s);
        if (aux == null) {
                tabla.add(s);
            } else {
                s = aux;
                s.incrementarAccesos();
            }
     
    }

    public void eliminarSimbolo(Simbolo s) {
        s = existeSimbolo(s);
        s.decrementarAccesos();
        if (s.getAccesos() == 0) {
            tabla.remove(s);
        }
    }

    public boolean contains(Simbolo s) {
        return tabla.contains(s);
    }

    public ArrayList<Simbolo> getTabla() {
        return tabla;
    }

    public void imprimirTabla() {
        Logger logSimbolos = new Logger("tablasimbolo.log");
        System.out.println("TABLA DE SIMBOLOS");
        System.out.println("TIPO    VALOR   ACCESOS");
        for (int i = 0; i < tabla.size(); i++) {
            logSimbolos.addLogger(tabla.get(i).getTipoVariable() + " " + tabla.get(i).getTipo() + " " + tabla.get(i).getValor() + " " + tabla.get(i).getAccesos());
        }
        logSimbolos.imprimir();
    }

    public void addTipo(String valor, String variableTipo) {
      Simbolo s = existeSimbolo(new Simbolo(new StringBuffer(valor), ""));
        if (s.esVacio()) {
            s.setTipoVariable(variableTipo);
            s.setDeclarado(true);
        } else {
            System.out.println("ERROR ya se encuentra declarada esta variable");
        }

    }
    public boolean existeTipoVariable(String iden,String tipo){
        Simbolo s = existeSimbolo(new Simbolo(new StringBuffer(iden), ""));
        return s.isDeclarado() && tipo.equals(s.getTipoVariable());
    }
}
