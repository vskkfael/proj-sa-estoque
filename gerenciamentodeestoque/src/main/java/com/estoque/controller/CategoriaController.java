package com.estoque.controller;

import java.sql.SQLException;
import java.util.List;

import com.estoque.model.Categoria;
import com.estoque.model.CategoriaDAO;

public class CategoriaController {
    private final CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public void cadastrarCategoria(String nomeCategoria, String dsCategoria) throws SQLException {
        Categoria categoria = new Categoria(nomeCategoria, dsCategoria);
        CategoriaDAO.salvar(categoria);
    }

    public List<Categoria> listarCategoria() throws SQLException {
        return categoriaDAO.listarTodos();
    }

    public boolean atualizarCategoria(int idCategoria, String nomeCategoria, String dsCategoria) throws SQLException {
        Categoria categoria = new Categoria(idCategoria, nomeCategoria, dsCategoria);
        return categoriaDAO.atualizar(categoria);
    }

    public boolean excluirCategoria(int idFornecedor) throws SQLException {
        return categoriaDAO.excluir(idFornecedor);
    }

    public Categoria buscarPorID(int id) throws SQLException {
        return categoriaDAO.buscarPorID(id);
    }
}   
