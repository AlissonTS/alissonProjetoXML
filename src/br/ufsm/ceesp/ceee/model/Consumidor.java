package br.ufsm.ceesp.ceee.model;

/**
 * Created by Alisson on 15/11/2016.
 */
public class Consumidor {

    private int id;
    private String desc;
    private String grupo;
    private String subgrupo;
    private String classe;
    private String categoria;
    private int idTrf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
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

    public int getIdTrf() {
        return idTrf;
    }

    public void setIdTrf(int idTrf) {
        this.idTrf = idTrf;
    }
}
