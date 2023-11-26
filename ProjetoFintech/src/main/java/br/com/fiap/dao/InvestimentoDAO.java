package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Investimento;
import br.com.fiap.jdbc.DBManager;

public class InvestimentoDAO {

    private Connection conexao;

    public void cadastrar(Investimento investimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "INSERT INTO T_INVESTIMENTOS (ID_INVESTIMENTOS, ID_USUARIO, ID_CORRETORA, NM_INVESTIMENTO, VL_INICIAL_INVESTIMENTO, TAXA_INVESTIMENTO) VALUES (SEQ_INVESTIMENTO.NEXTVAL, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, investimento.getIdUsuario());
            stmt.setInt(2, investimento.getIdCorretora());
            stmt.setString(3, investimento.getNome());
            stmt.setDouble(4, investimento.getValorInicial());
            stmt.setDouble(5, investimento.getTaxa());

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

    public List<Investimento> listar() {
        List<Investimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_INVESTIMENTOS";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idInvestimento = rs.getInt("ID_INVESTIMENTOS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCorretora = rs.getInt("ID_CORRETORA");
                String nome = rs.getString("NM_INVESTIMENTO");
                double valorInicial = rs.getDouble("VL_INICIAL_INVESTIMENTO");
                double taxa = rs.getDouble("TAXA_INVESTIMENTO");

                Investimento investimento = new Investimento(idInvestimento, idUsuario, idCorretora, nome, valorInicial, taxa);
                lista.add(investimento);
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
    
    public List<Investimento> listarFiltradoUsuario(int idUsuarioLogado) {
        List<Investimento> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_INVESTIMENTOS WHERE ID_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idInvestimento = rs.getInt("ID_INVESTIMENTOS");
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCorretora = rs.getInt("ID_CORRETORA");
                String nome = rs.getString("NM_INVESTIMENTO");
                double valorInicial = rs.getDouble("VL_INICIAL_INVESTIMENTO");
                double taxa = rs.getDouble("TAXA_INVESTIMENTO");

                Investimento investimento = new Investimento(idInvestimento, idUsuario, idCorretora, nome, valorInicial, taxa);
                lista.add(investimento);
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
    
    public void atualizar(Investimento investimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "UPDATE T_INVESTIMENTOS SET ID_USUARIO = ?, ID_CORRETORA = ?, NM_INVESTIMENTO = ?, VL_INICIAL_INVESTIMENTO = ?, TAXA_INVESTIMENTO = ? WHERE ID_INVESTIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, investimento.getIdUsuario());
            stmt.setInt(2, investimento.getIdCorretora());
            stmt.setString(3, investimento.getNome());
            stmt.setDouble(4, investimento.getValorInicial());
            stmt.setDouble(5, investimento.getTaxa());
            stmt.setInt(6, investimento.getIdInvestimento());

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

    public Investimento buscarPorId(int idInvestimento) {
        Investimento investimento = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_INVESTIMENTOS WHERE ID_INVESTIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idInvestimento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                int idCorretora = rs.getInt("ID_CORRETORA");
                String nome = rs.getString("NM_INVESTIMENTO");
                double valorInicial = rs.getDouble("VL_INICIAL_INVESTIMENTO");
                double taxa = rs.getDouble("TAXA_INVESTIMENTO");

                investimento = new Investimento(idInvestimento, idUsuario, idCorretora, nome, valorInicial, taxa);
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

        return investimento;
    }

    public void deletar(int idInvestimento) {
        PreparedStatement stmt = null;

        try {
            conexao = DBManager.obterConexao();
            String sql = "DELETE FROM T_INVESTIMENTOS WHERE ID_INVESTIMENTOS = ?";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idInvestimento);

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
