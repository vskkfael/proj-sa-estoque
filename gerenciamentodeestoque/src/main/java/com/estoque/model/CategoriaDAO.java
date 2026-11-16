package com.estoque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estoque.util.ConnectionFactory;

public class CategoriaDAO {

    public static void salvar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO TB_SGE_CATEGORIA(nm_categoria, ds_categoria) VALUES (?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, categoria.getNomeCategoria());
                stmt.setString(2, categoria.getDsCategoria());
                stmt.executeUpdate();

                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        categoria.setIdCategoria(1);
                    }
                }
            }
    }

    public List<Categoria> listarTodos() throws SQLException {
        List<Categoria> categoria = new ArrayList<>();
        String sql = "SELECT * FROM TB_SGE_CATEGORIA";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Categoria c = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nm_categoria"),
                        rs.getString("ds_categoria"));
                    categoria.add(c);
                }
            }
        return categoria;
    }

    public boolean atualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE TB_SGE_CATEGORIA SET nm_categoria = ?, ds_categoria = ? WHERE id_categoria = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, categoria.getNomeCategoria());
                stmt.setString(2, categoria.getDsCategoria());
                stmt.setInt(3, categoria.getIdCategoria());
                return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM TB_SGE_CATEGORIA WHERE id_categoria = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
    }
}
