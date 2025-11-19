package com.petcare.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/clinicaveterinariadb";// Conexão com banco de dados
	private static final String USER = "postgres"; 	// Usuario do banco de dados
	private static final String PASSWORD = "postgres"; 	// Senha do banco de dados

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException("Erro de conexão: ");
		}
	}
	
}
	
