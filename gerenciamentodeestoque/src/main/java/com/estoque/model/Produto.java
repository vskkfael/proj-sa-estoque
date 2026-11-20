package com.estoque.model;

public class Produto {
    Integer idProduto;
    Integer idCategoria;
    Integer idFornecedor;
    String nmProduto;
    Integer qtProduto;
    Integer estoqueMin;

    public Produto(){

    }

    public Produto(Integer idProduto, Integer idCategoria, Integer idFornecedor, String nmProduto, int estoqueMin) {
        this.idProduto = idProduto;
        this.idCategoria = idCategoria;
        this.idFornecedor = idFornecedor;
        this.nmProduto = nmProduto;
        this.estoqueMin = estoqueMin;
    }
    
    public Produto(Integer idCategoria, Integer idFornecedor, String nmProduto, int estoqueMin) {
        this.idCategoria = idCategoria;
        this.idFornecedor = idFornecedor;
        this.nmProduto = nmProduto;
        this.estoqueMin = estoqueMin;
    }
    
    // Getters e Setters
    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public Integer getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(Integer estoqueMin) {
        this.estoqueMin = estoqueMin;
    }   
     
    @Override
    public String toString() {
        return String.format("ID: %-2d | Categoria: %-2d | Fornecedor: %-2d | Produto: 5-80s | Situação: ", idProduto, idCategoria, idFornecedor, nmProduto, estoqueMin);
    }
}
