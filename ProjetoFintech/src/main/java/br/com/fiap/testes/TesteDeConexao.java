package br.com.fiap.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteDeConexao {
	public static void main(String[] args) {
		
		System.out.println("Tentando Conectar...");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM98235", "140598");
			
			System.out.println("Conectado!");
			
			PreparedStatement preparedQuery = conexao.prepareStatement("SELECT * FROM T_PRODUTO WHERE cd_produto = ?");
			preparedQuery.setInt(1, 3);
			ResultSet result = preparedQuery.executeQuery();
			
			while(result.next()) {
				System.out.println("Recebeu o registro com código: " + result.getInt("cd_produto"));
			}
			
			conexao.close();
		} catch (SQLException e) {
			System.err.println("Não foi possível conectar ao banco!");
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado!!");
		}
	}
}