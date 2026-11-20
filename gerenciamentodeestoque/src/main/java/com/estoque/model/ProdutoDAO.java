package com.estoque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estoque.util.ConnectionFactory;

public class ProdutoDAO {
    public static void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO TB_SGE_PRODUTO(id_categoria, id_fornecedor, nm_produto, qt_estoque_min) VALUES (?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setInt(1, produto.getIdCategoria());
                stmt.setInt(2, produto.getIdFornecedor());
                stmt.setString(3, produto.getNmProduto());
                stmt.setInt(4, produto.getEstoqueMin());
                stmt.executeUpdate();

                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        produto.getIdProduto();
                    }
                }
            }
    }

    public List<Produto> listarTodos() throws SQLException {
        List<Produto> produto = new ArrayList<>();

        String sql = "SELECT * FROM tb_sge_produto";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Produto p = new Produto(
                        rs.getInt("id_produto"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("nm_produto"),
                        rs.getInt("qt_estoque_min"));
                    produto.add(p);
                }
            }
        return produto;
    }

    public boolean atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE tb_sge_produto SET id_categoria = ?, id_fornecedor = ?, nm_produto = ?, qt_estoque_min = ? WHERE id_produto = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, produto.getIdCategoria()); // Foreign Key
                stmt.setInt(2, produto.getIdFornecedor()); // Foreign Key
                stmt.setString(3, produto.getNmProduto());
                stmt.setInt(5, produto.getEstoqueMin());
                stmt.setInt(6, produto.getIdProduto()); // Primary Key
                return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM tb_sge_produto WHERE id_produto = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
    }

    public Produto buscarPorID(int id) throws SQLException {
        String sql = "SELECT * FROM tb_sge_produto WHERE id_produto = ?";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return new Produto(
                        rs.getInt("id_produto"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_fornecedor"),
                        rs.getString("nm_produto"),
                        rs.getInt("qt_estoque_min"));
                }
            }
        }
        return null;
    }

  
}
