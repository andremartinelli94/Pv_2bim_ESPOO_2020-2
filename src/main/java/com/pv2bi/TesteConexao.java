package com.pv2bi;

import java.sql.Connection;

public class TesteConexao {
	
	public static void main(String[] args) {
		ConexaoHSQLDB conn =  new ConexaoHSQLDB();
		Connection connection =  conn.connectar();
		System.out.println(connection);
	}
	
}
