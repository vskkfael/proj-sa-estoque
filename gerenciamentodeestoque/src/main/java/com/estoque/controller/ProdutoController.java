package com.estoque.controller;

import java.sql.SQLException;
import java.util.List;

import com.estoque.model.Produto;
import com.estoque.model.ProdutoDAO;

public class ProdutoController {
    private final ProdutoDAO produtoDAO;

    public ProdutoController(){
        this.produtoDAO = new ProdutoDAO();
    }

    public void cadastrarProduto(int idCategoria, int idFornecedor, String nmProduto, int qtProduto, int estoqueMin) throws SQLException {
        Produto produto = new Produto(idCategoria, idFornecedor, nmProduto, qtProduto, estoqueMin);
        produtoDAO.salvar(produto);
    }

    public List<Produto> listarProduto() throws SQLException {
        return produtoDAO.listarTodos();
    }

    public boolean atualizarProduto(int idProduto, int idCategoria, int idFornecedor, String nmProduto, int qtProduto, int estoqueMin) throws SQLException {
        Produto produto = new Produto(idProduto, idCategoria, idFornecedor, nmProduto, qtProduto, estoqueMin);
        return produtoDAO.atualizar(produto);
    }

    public boolean excluirProduto(int idProduto) throws SQLException {
        return produtoDAO.excluir(idProduto);
    }

    public Produto buscarPorID(int idProduto) throws SQLException {
        return produtoDAO.buscarPorID(idProduto);
    }
}
