package com.estoque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estoque.util.ConnectionFactory;

public class FornecedorDAO {

    public static void salvar(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO TB_SGE_FORNECEDOR(razao_social, nr_cnpj, nr_tel) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, fornecedor.getRazaoSocial());
                stmt.setString(2, fornecedor.getNumCNPJ());
                stmt.setString(3, fornecedor.getNumTel());
                stmt.executeUpdate();

                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        fornecedor.setIdFornecedor(1);
                    }
                }
            }
    }

    public List<Fornecedor> listarTodos() throws SQLException {
        List<Fornecedor> fornecedor = new ArrayList<>();
        String sql = "SELECT * FROM tb_sge_fornecedor";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Fornecedor f = new Fornecedor(
                        rs.getInt("id_fornecedor"),
                        rs.getString("razao_social"),
                        rs.getString("nr_cnpj"),
                        rs.getString("nr_tel"));
                    fornecedor.add(f);
                }
            }
        return fornecedor;
    }

    public boolean atualizar(Fornecedor fornecedor) throws SQLException {
        String sql = "UPDATE tb_sge_fornecedor SET razao_social = ?, nr_cnpj = ?, nr_tel = ? WHERE id_fornecedor = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, fornecedor.getRazaoSocial());
                stmt.setString(2, fornecedor.getNumCNPJ());
                stmt.setString(3, fornecedor.getNumTel());
                stmt.setInt(4, fornecedor.getIdFornecedor());
                return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM tb_sge_fornecedor WHERE id_fornecedor = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
    }

    public Fornecedor buscarPorCNPJ(String cnpj) throws SQLException {
        String sql = "SELECT * from tb_sge_fornecedor WHERE nr_cnpj = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, cnpj);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        return new Fornecedor(
                            rs.getInt("id_fornecedor"),
                            rs.getString("razao_social"),
                            rs.getString("nr_cnpj"),
                            rs.getString("nr_tel"));
                    }
                }
            }
        return null;
    }

    public Fornecedor buscarPorID(int id) throws SQLException {
        String sql = "SELECT * FROM tb_sge_fornecedor WHERE id_fornecedor = ?";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return new Fornecedor(
                        rs.getInt("id_fornecedor"),
                        rs.getString("razao_social"),
                        rs.getString("nr_cnpj"),
                        rs.getString("nr_tel")
                    );
                }
            }
        }
        return null;
    }
}
