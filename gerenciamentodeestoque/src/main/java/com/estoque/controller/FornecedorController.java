package com.estoque.controller;

import java.sql.SQLException;
import java.util.List;

import com.estoque.model.Fornecedor;
import com.estoque.model.FornecedorDAO;

public class FornecedorController {
    private final FornecedorDAO fornecedorDAO;

    public FornecedorController() {
        this.fornecedorDAO = new FornecedorDAO();
    }

    public void cadastrarFornecedor(String razaoSocial, String NumCNPJ, String NumTel) throws SQLException {
        Fornecedor fornecedor = new Fornecedor(razaoSocial, NumCNPJ, NumTel);
        FornecedorDAO.salvar(fornecedor);
    }

    public List<Fornecedor> listarFornecedor() throws SQLException {
        return fornecedorDAO.listarTodos();
    }

    public boolean atualizarFornecedor(int idFornecedor, String razaoSocial, String NumCNPJ, String NumTel) throws SQLException {
        Fornecedor fornecedor = new Fornecedor(idFornecedor, razaoSocial, NumCNPJ, NumTel);
        return fornecedorDAO.atualizar(fornecedor);
    }

    public boolean excluirFornecedor(int idFornecedor) throws SQLException {
        return fornecedorDAO.excluir(idFornecedor);
    }

    public Fornecedor buscarCNPJ(String cnpj) throws SQLException {
        return fornecedorDAO.buscarPorCNPJ(cnpj);
    }

    public Fornecedor buscarPorID(int id) throws SQLException {
        return fornecedorDAO.buscarPorID(id);
    }
}
