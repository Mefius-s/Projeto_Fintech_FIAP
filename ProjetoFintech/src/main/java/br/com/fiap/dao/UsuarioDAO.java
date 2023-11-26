package br.com.fiap.dao;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Usuario;
import br.com.fiap.jdbc.DBManager;

public class UsuarioDAO {
	
	private Connection conexao;
	
	public void cadastrar(Usuario usuario) {
		PreparedStatement stmt = null;
		
		try {
			conexao = DBManager.obterConexao();
			String sql = "INSERT INTO T_USUARIO (ID_USUARIO, NM_USUARIO, EMAIL, SENHA, DT_NASCIMENTO, NR_TELEFONE, DS_ENDERECO) VALUES (SEQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			java.sql.Date dataNascimento = new java.sql.Date(new java.util.Date().getTime());
			stmt.setDate(4, dataNascimento);
			stmt.setString(5, usuario.getTelefone());
			stmt.setString(6, usuario.getEndereco());
			
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
	
	public List<Usuario> listar() {

	    List<Usuario> lista = new ArrayList<Usuario>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	conexao = DBManager.obterConexao();
		    stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO");
		    rs = stmt.executeQuery();
		    while (rs.next()) {
			    int idUsuario = rs.getInt("ID_USUARIO");
			    String nome = rs.getString("NM_USUARIO");
			    String email = rs.getString("EMAIL");
			    String senha = rs.getString("SENHA");
			    java.sql.Date dataNascimento = rs.getDate("DT_NASCIMENTO");
			    Calendar dataNascimentoCalendar = Calendar.getInstance();
			    dataNascimentoCalendar.setTimeInMillis(dataNascimento.getTime());
			    String telefone = rs.getString("NR_TELEFONE");
			    String endereco = rs.getString("DS_ENDERECO");
			    Usuario usuario = new Usuario(idUsuario, nome, email, senha, dataNascimentoCalendar, telefone, endereco);
		        lista.add(usuario);
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

	public Usuario autenticar(String email, String senha) {
	    Usuario usuario = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conexao = DBManager.obterConexao();
	        stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE EMAIL = ? AND SENHA = ?");
	        stmt.setString(1, email);
	        stmt.setString(2, senha);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
			    int idUsuario = rs.getInt("ID_USUARIO");
			    String nome = rs.getString("NM_USUARIO");
			    java.sql.Date dataNascimento = rs.getDate("DT_NASCIMENTO");
			    Calendar dataNascimentoCalendar = Calendar.getInstance();
			    dataNascimentoCalendar.setTimeInMillis(dataNascimento.getTime());
			    String telefone = rs.getString("NR_TELEFONE");
			    String endereco = rs.getString("DS_ENDERECO");
			    usuario = new Usuario(idUsuario, nome, email, senha, dataNascimentoCalendar, telefone, endereco);
	        }
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
	    return usuario;
	}
	
}
