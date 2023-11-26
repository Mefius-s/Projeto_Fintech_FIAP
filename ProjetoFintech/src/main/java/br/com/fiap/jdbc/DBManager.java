package br.com.fiap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

	public static Connection obterConexao() {
		Connection conexao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM98235", "140598");
			
			System.out.println("Conectado!");

		} catch (Exception e) {
			System.err.println("Não foi possível conectar ao banco!");
			e.printStackTrace();
		}
		return conexao;
	}

}
