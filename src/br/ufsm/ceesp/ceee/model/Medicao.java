package br.ufsm.ceesp.ceee.model;

import java.util.ArrayList;

/**
 * Created by Alisson on 15/11/2016.
 */
public class Medicao {

    private int idTrf;
    private int idConsumidor;
    private String tipo;
    private double valor;
    private ArrayList<Curva> curva = new ArrayList<Curva>();

    public int getIdTrf() {
        return idTrf;
    }

    public void setIdTrf(int idTrf) {
        this.idTrf = idTrf;
    }

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ArrayList<Curva> getCurva() {
        return curva;
    }

    public void setCurva(ArrayList<Curva> curva) {
        this.curva = curva;
    }
}
