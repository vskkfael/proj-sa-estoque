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

    public List<Produto> listarTodos() {
    List<Produto> produtos = new ArrayList<>();

    String sql = "SELECT * FROM vw_produto_situacao ORDER BY nm_produto";

                try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Produto p = new Produto();
                            p.setIdProduto(rs.getInt("id_produto"));
                            p.setIdCategoria(rs.getInt("id_categoria"));
                            p.setNmCategoria(rs.getString("nm_categoria"));
                            p.setIdFornecedor(rs.getInt("id_fornecedor"));
                            p.setRazaoSocial(rs.getString("razao_social"));
                            p.setNmProduto(rs.getString("nm_produto"));
                            p.setEstoqueMin(rs.getInt("estoque_atual"));
                            p.setSituacao(rs.getString("situacao"));
                        produtos.add(p);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return produtos;
}

    public boolean atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE tb_sge_produto SET id_categoria = ?, id_fornecedor = ?, nm_produto = ?, qt_estoque_min = ? WHERE id_produto = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, produto.getIdCategoria()); // Foreign Key
                stmt.setInt(2, produto.getIdFornecedor()); // Foreign Key
                stmt.setString(3, produto.getNmProduto());
                stmt.setInt(4, produto.getEstoqueMin());
                stmt.setInt(5, produto.getIdProduto()); // Primary Key
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
        Produto p = null;
        
        String sql = "SELECT * FROM vw_produto_situacao WHERE id_produto = ?";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Produto();
                    p.setIdProduto(rs.getInt("id_produto"));
                    p.setIdCategoria(rs.getInt("id_categoria"));
                    p.setNmCategoria(rs.getString("nm_categoria"));
                    p.setIdFornecedor(rs.getInt("id_fornecedor"));
                    p.setRazaoSocial(rs.getString("razao_social"));
                    p.setNmProduto(rs.getString("nm_produto"));
                    p.setEstoqueMin(rs.getInt("estoque_atual"));
                    p.setSituacao(rs.getString("situacao"));
                }
            }

        }
        return p;
    }
}
