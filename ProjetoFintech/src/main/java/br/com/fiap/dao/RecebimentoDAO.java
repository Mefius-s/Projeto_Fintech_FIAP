package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Recebimento;
import br.com.fiap.jdbc.DBManager;

public class RecebimentoDAO {

    private Connection conexao;

    public void cadastrar(Recebimento recebimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "INSERT INTO T_RECEBIMENTOS (ID_RECEBIMENTOS, ID_USUARIO, ID_CATEGORIA_RECEBIMENTO, NM_RECEBIMENTO, VL_RECEBIMENTO, DT_RECEBIMENTO, DS_RECEBIMENTO) VALUES (SEQ_RECEBIMENTO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, recebimento.getIdUsuario());
            stmt.setInt(2, recebimento.getIdCategoria());
            stmt.setString(3, recebimento.getNome());
            stmt.setDouble(4, recebimento.getValor());
            java.sql.Date dataRecebimento = new java.sql.Date(recebimento.getData().getTimeInMillis());
            stmt.setDate(5, dataRecebimento);
            stmt.setString(6, recebimento.getDescricao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void atualizar(Recebimento recebimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "UPDATE T_RECEBIMENTOS SET ID_USUARIO = ?, ID_CATEGORIA_RECEBIMENTO = ?, NM_RECEBIMENTO = ?, VL_RECEBIMENTO = ?, DT_RECEBIMENTO = ?, DS_RECEBIMENTO = ? WHERE ID_RECEBIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, recebimento.getIdUsuario());
            stmt.setInt(2, recebimento.getIdCategoria());
            stmt.setString(3, recebimento.getNome());
            stmt.setDouble(4, recebimento.getValor());
            java.sql.Date dataRecebimento = new java.sql.Date(recebimento.getData().getTimeInMillis());
            stmt.setDate(5, dataRecebimento);
            stmt.setString(6, recebimento.getDescricao());
            stmt.setInt(7, recebimento.getIdRecebimento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Recebimento> listar() {
        List<Recebimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_RECEBIMENTOS";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRecebimento = rs.getInt("ID_RECEBIMENTOS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_RECEBIMENTO");
                String nome = rs.getString("NM_RECEBIMENTO");
                double valor = rs.getDouble("VL_RECEBIMENTO");
                java.sql.Date dataRecebimento = rs.getDate("DT_RECEBIMENTO");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataRecebimento.getTime());
                String descricao = rs.getString("DS_RECEBIMENTO");

                Recebimento recebimento = new Recebimento(idRecebimento, idUsuario, idCategoria, nome, valor, data, descricao);
                lista.add(recebimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
    
    public List<Recebimento> listarFiltradoUsuario(int idUsuarioLogado) {
        List<Recebimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_RECEBIMENTOS WHERE ID_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            rs = stmt.executeQuery();
            System.out.println(idUsuarioLogado);
            System.out.println(rs);

            while (rs.next()) {
                int idRecebimento = rs.getInt("ID_RECEBIMENTOS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_RECEBIMENTO");
                String nome = rs.getString("NM_RECEBIMENTO");
                double valor = rs.getDouble("VL_RECEBIMENTO");
                java.sql.Date dataRecebimento = rs.getDate("DT_RECEBIMENTO");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataRecebimento.getTime());
                String descricao = rs.getString("DS_RECEBIMENTO");

                Recebimento recebimento = new Recebimento(idRecebimento, idUsuario, idCategoria, nome, valor, data, descricao);
                lista.add(recebimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
    
    public Recebimento buscarRecebimentoPorId(int idRecebimento) {
        Recebimento recebimento = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_RECEBIMENTOS WHERE ID_RECEBIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRecebimento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_RECEBIMENTO");
                String nome = rs.getString("NM_RECEBIMENTO");
                double valor = rs.getDouble("VL_RECEBIMENTO");
                java.sql.Date dataRecebimento = rs.getDate("DT_RECEBIMENTO");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataRecebimento.getTime());
                String descricao = rs.getString("DS_RECEBIMENTO");

                recebimento = new Recebimento(idRecebimento, idUsuario, idCategoria, nome, valor, data, descricao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return recebimento;
    }
    
    public void deletar(int idRecebimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "DELETE FROM T_RECEBIMENTOS WHERE ID_RECEBIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idRecebimento);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
