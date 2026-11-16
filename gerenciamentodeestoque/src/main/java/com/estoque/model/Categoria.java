package com.estoque.model;

public class Categoria {
    Integer idCategoria;
    String nomeCategoria;
    String dsCategoria;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nomeCategoria, String dsCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.dsCategoria = dsCategoria;
    }

    public Categoria(String nomeCategoria, String dsCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.dsCategoria = dsCategoria;
    }

    // Getters e Setters

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public void setDsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
    }

    @Override
    public String toString() {
        return String.format("ID: %-2d | Categoria: %-5.80s | Descrição: %-5.200s", idCategoria, nomeCategoria, dsCategoria);
    }

}
