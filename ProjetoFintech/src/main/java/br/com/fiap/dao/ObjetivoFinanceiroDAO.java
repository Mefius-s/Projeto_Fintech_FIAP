package br.com.fiap.dao;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.ObjetivoFinanceiro;
import br.com.fiap.jdbc.DBManager;

public class ObjetivoFinanceiroDAO {
	
	private Connection conexao;
	
	public void cadastrar(ObjetivoFinanceiro objetivo) {
		PreparedStatement stmt = null;
		
		try {
			conexao = DBManager.obterConexao();
			String sql = "INSERT INTO T_OBJETIVO_FINANCEIRO (ID_OBJETIVO, FK_ID_USUARIO, VL_OBJETIVO, DT_INICIO_OBJETIVO, DT_FIM_OBJETIVO, DS_OBJETIVO) VALUES (SEQ_PRODUTO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, objetivo.getIdUsuario());
			stmt.setDouble(2, objetivo.getValor());
	        java.sql.Date dataInicio = new java.sql.Date(objetivo.getDataInicio().getTimeInMillis());
	        stmt.setDate(3, dataInicio);
	        java.sql.Date dataFim = new java.sql.Date(objetivo.getDataFim().getTimeInMillis());
	        stmt.setDate(4, dataFim);
			stmt.setString(5, objetivo.getDescricao());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<ObjetivoFinanceiro> listar() {

	    List<ObjetivoFinanceiro> lista = new ArrayList<ObjetivoFinanceiro>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	conexao = DBManager.obterConexao();
		    stmt = conexao.prepareStatement("SELECT * FROM T_OBJETIVO_FINANCEIRO");
		    rs = stmt.executeQuery();
		    while (rs.next()) {
			    int idObjetivo = rs.getInt("ID_OBJETIVO");
			    int idUsuario = rs.getInt("FK_ID_USUARIO");
			    double valor = rs.getDouble("VL_OBJETIVO");
			    String descricao = rs.getString("DS_OBJETIVO");
			    java.sql.Date dataInicio = rs.getDate("DT_INICIO_OBJETIVO");
			    Calendar dataInicioCalendar = Calendar.getInstance();
			    dataInicioCalendar.setTimeInMillis(dataInicio.getTime());
			    java.sql.Date dataFim= rs.getDate("DT_FIM_OBJETIVO");
			    Calendar dataFimCalendar = Calendar.getInstance();
			    dataFimCalendar.setTimeInMillis(dataFim.getTime());
			    ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro(idObjetivo, idUsuario, valor, dataInicioCalendar, dataFimCalendar, descricao);
		        lista.add(objetivo);
		      }
	    }catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        stmt.close();
	        rs.close();
	        conexao.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return lista;
	  }

	public List<ObjetivoFinanceiro> listarFiltradoUsuario(int idUsuarioLogado) {

	    List<ObjetivoFinanceiro> lista = new ArrayList<ObjetivoFinanceiro>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	conexao = DBManager.obterConexao();
            String sql = "SELECT * FROM T_OBJETIVO_FINANCEIRO WHERE FK_ID_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuarioLogado);
            rs = stmt.executeQuery();
		    while (rs.next()) {
			    int idObjetivo = rs.getInt("ID_OBJETIVO");
			    int idUsuario = rs.getInt("FK_ID_USUARIO");
			    double valor = rs.getDouble("VL_OBJETIVO");
			    String descricao = rs.getString("DS_OBJETIVO");
			    java.sql.Date dataInicio = rs.getDate("DT_INICIO_OBJETIVO");
			    Calendar dataInicioCalendar = Calendar.getInstance();
			    dataInicioCalendar.setTimeInMillis(dataInicio.getTime());
			    java.sql.Date dataFim= rs.getDate("DT_FIM_OBJETIVO");
			    Calendar dataFimCalendar = Calendar.getInstance();
			    dataFimCalendar.setTimeInMillis(dataFim.getTime());
			    ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro(idObjetivo, idUsuario, valor, dataInicioCalendar, dataFimCalendar, descricao);
		        lista.add(objetivo);
		      }
	    }catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        stmt.close();
	        rs.close();
	        conexao.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return lista;
	  }

	public void atualizar(ObjetivoFinanceiro objetivo) {
	    PreparedStatement stmt = null;

	    try {
	        conexao = DBManager.obterConexao();
	        String sql = "UPDATE T_OBJETIVO_FINANCEIRO SET FK_ID_USUARIO = ?, VL_OBJETIVO = ?, DT_INICIO_OBJETIVO = ?, DT_FIM_OBJETIVO = ?, DS_OBJETIVO = ? WHERE ID_OBJETIVO = ?";
	        stmt = conexao.prepareStatement(sql);
	        
	        stmt.setInt(1, objetivo.getIdUsuario());
	        stmt.setDouble(2, objetivo.getValor());
	        java.sql.Date dataInicio = new java.sql.Date(objetivo.getDataInicio().getTimeInMillis());
	        stmt.setDate(3, dataInicio);
	        java.sql.Date dataFim = new java.sql.Date(objetivo.getDataFim().getTimeInMillis());
	        stmt.setDate(4, dataFim);
	        stmt.setString(5, objetivo.getDescricao());
	        stmt.setInt(6, objetivo.getIdObjetivo());

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

	public ObjetivoFinanceiro buscarPorId(int idObjetivo) {
	    ObjetivoFinanceiro objetivo = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conexao = DBManager.obterConexao();
	        String sql = "SELECT * FROM T_OBJETIVO_FINANCEIRO WHERE ID_OBJETIVO = ?";
	        stmt = conexao.prepareStatement(sql);
	        stmt.setInt(1, idObjetivo);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            int idUsuario = rs.getInt("FK_ID_USUARIO");
	            double valor = rs.getDouble("VL_OBJETIVO");
	            String descricao = rs.getString("DS_OBJETIVO");
	            java.sql.Date dataInicio = rs.getDate("DT_INICIO_OBJETIVO");
	            Calendar dataInicioCalendar = Calendar.getInstance();
	            dataInicioCalendar.setTimeInMillis(dataInicio.getTime());
	            java.sql.Date dataFim= rs.getDate("DT_FIM_OBJETIVO");
	            Calendar dataFimCalendar = Calendar.getInstance();
	            dataFimCalendar.setTimeInMillis(dataFim.getTime());

	            objetivo = new ObjetivoFinanceiro(idObjetivo, idUsuario, valor, dataInicioCalendar, dataFimCalendar, descricao);
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

	    return objetivo;
	}

	public void deletar(int idObjetivo) {
	    PreparedStatement stmt = null;

	    try {
	        conexao = DBManager.obterConexao();
	        String sql = "DELETE FROM T_OBJETIVO_FINANCEIRO WHERE ID_OBJETIVO = ?";
	        stmt = conexao.prepareStatement(sql);

	        stmt.setInt(1, idObjetivo);

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
