package com.pv2bi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHSQLDB {

	private String usuario = "SA";
	private String senha = "";
	private String PathBase = "C:\\Users\\andre\\eclipse-workspace\\prova2bi\\Dados";
	private String URL = "jdbc:hsqldb:file:" + PathBase +";";
	
	public Connection connectar() {
		
		try {
			System.out.println("Conexao OK");
			return DriverManager.getConnection(URL, usuario, senha);			
			
		} catch (SQLException e) {
			System.out.println("Falha na conexão");
			throw new Error("Sql Exception");
			
			} 
	}
	
}
