package com.estoque.model;

import java.time.LocalDateTime;

public class Movimentacao {
    Integer idMovimentacao;
    Integer idProduto;
    String tpMovimentacao;
    Integer qtMovimentacao;
    LocalDateTime dtMovimentacao;

    public Movimentacao() {
    }

    public Movimentacao(Integer idMovimentacao, Integer idProduto, String tpMovimentacao, Integer qtMovimentacao, LocalDateTime dtMovimentacao) {
        this.idMovimentacao = idMovimentacao;
        this.idProduto = idProduto;
        this.tpMovimentacao = tpMovimentacao;
        this.qtMovimentacao = qtMovimentacao;
        this.dtMovimentacao = dtMovimentacao;
    }

    public Movimentacao(Integer idProduto, String tpMovimentacao, Integer qtMovimentacao, LocalDateTime dtMovimentacao) {
        this.idProduto = idProduto;
        this.tpMovimentacao = tpMovimentacao;
        this.qtMovimentacao = qtMovimentacao;
        this.dtMovimentacao = dtMovimentacao;
    }

    public Integer getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(Integer idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTpMovimentacao() {
        return tpMovimentacao;
    }

    public void setTpMovimentacao(String tpMovimentacao) {
        this.tpMovimentacao = tpMovimentacao;
    }

    public Integer getQtMovimentacao() {
        return qtMovimentacao;
    }

    public void setQtMovimentacao(Integer qtMovimentacao) {
        this.qtMovimentacao = qtMovimentacao;
    }

    public LocalDateTime getDtMovimentacao() {
        return dtMovimentacao;
    }

    public void setDtMovimentacao(LocalDateTime dtMovimentacao) {
        this.dtMovimentacao = dtMovimentacao;
    }

    @Override
    public String toString() {
        return String.format("ID: %-2d | Produto: %-5.60s | Tipo: %-5.20s | Quantidade: %-2d | Data: %-5.20s", idMovimentacao, idProduto, tpMovimentacao, qtMovimentacao, dtMovimentacao);
    }



}
