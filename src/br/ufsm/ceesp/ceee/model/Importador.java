package br.ufsm.ceesp.ceee.model;

import java.util.ArrayList;

/**
 * Created by Alisson on 15/11/2016.
 */
public class Importador {

    private int idConsumidor;
    private int idTrafo;
    private String desc;
    private String grupo;
    private String subGrupo;
    private String classe;
    private String categoria;
    private String tipoMed;
    private double valor;
    private ArrayList<Curva> curva = new ArrayList<Curva>();


    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public int getIdTrafo() {
        return idTrafo;
    }

    public void setIdTrafo(int idTrafo) {
        this.idTrafo = idTrafo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoMed() {
        return tipoMed;
    }

    public void setTipoMed(String tipoMed) {
        this.tipoMed = tipoMed;
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
