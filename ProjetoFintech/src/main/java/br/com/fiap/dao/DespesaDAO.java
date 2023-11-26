package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Despesa;
import br.com.fiap.bean.Recebimento;
import br.com.fiap.jdbc.DBManager;

public class DespesaDAO {

    private Connection conexao;

    public void cadastrar(Despesa despesa) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "INSERT INTO T_DESPESAS (ID_DESPESAS, ID_USUARIO, ID_CATEGORIA_DESPESA, NM_DESPESA, VL_DESPESA, DT_DESPESA, DS_DESPESA) VALUES (SEQ_DESPESA.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, despesa.getIdUsuario());
            stmt.setInt(2, despesa.getIdCategoria());
            stmt.setString(3, despesa.getNome());
            stmt.setDouble(4, despesa.getValor());
            java.sql.Date dataDespesa = new java.sql.Date(despesa.getData().getTimeInMillis());
            stmt.setDate(5, dataDespesa);
            stmt.setString(6, despesa.getDescricao());

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

    public List<Despesa> listar() {
        List<Despesa> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_DESPESAS";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idDespesa = rs.getInt("ID_DESPESAS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_DESPESA");
                String nome = rs.getString("NM_DESPESA");
                double valor = rs.getDouble("VL_DESPESA");
                java.sql.Date dataDespesa = rs.getDate("DT_DESPESA");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataDespesa.getTime());
                String descricao = rs.getString("DS_DESPESA");

                Despesa despesa = new Despesa(idDespesa, idUsuario, idCategoria, nome, valor, data, descricao);
                lista.add(despesa);
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
    
    public List<Despesa> listarFiltradoUsuario(int idUsuarioLogado) {
        List<Despesa> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_DESPESAS WHERE ID_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idDespesa = rs.getInt("ID_DESPESAS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_DESPESA");
                String nome = rs.getString("NM_DESPESA");
                double valor = rs.getDouble("VL_DESPESA");
                java.sql.Date dataDespesa = rs.getDate("DT_DESPESA");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataDespesa.getTime());
                String descricao = rs.getString("DS_DESPESA");

                Despesa despesa = new Despesa(idDespesa, idUsuario, idCategoria, nome, valor, data, descricao);
                lista.add(despesa);
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
    
    public void atualizar(Despesa despesa) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "UPDATE T_DESPESAS SET ID_USUARIO = ?, ID_CATEGORIA_DESPESA = ?, NM_DESPESA = ?, VL_DESPESA = ?, DT_DESPESA = ?, DS_DESPESA = ? WHERE ID_DESPESAS = ?";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, despesa.getIdUsuario());
            stmt.setInt(2, despesa.getIdCategoria());
            stmt.setString(3, despesa.getNome());
            stmt.setDouble(4, despesa.getValor());
            java.sql.Date dataDespesa = new java.sql.Date(despesa.getData().getTimeInMillis());
            stmt.setDate(5, dataDespesa);
            stmt.setString(6, despesa.getDescricao());
            stmt.setInt(7, despesa.getIdDespesa());

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

    public Despesa buscarPorId(int idDespesa) {
        Despesa despesa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_DESPESAS WHERE ID_DESPESAS = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDespesa);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCategoria = rs.getInt("ID_CATEGORIA_DESPESA");
                String nome = rs.getString("NM_DESPESA");
                double valor = rs.getDouble("VL_DESPESA");
                java.sql.Date dataDespesa = rs.getDate("DT_DESPESA");
                Calendar data = Calendar.getInstance();
                data.setTimeInMillis(dataDespesa.getTime());
                String descricao = rs.getString("DS_DESPESA");

                despesa = new Despesa(idDespesa, idUsuario, idCategoria, nome, valor, data, descricao);
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

        return despesa;
    }

    public void deletar(int idDespesa) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "DELETE FROM T_DESPESAS WHERE ID_DESPESAS = ?";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idDespesa);

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
