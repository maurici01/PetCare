package com.petcare.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.petcare.connection.ConnectionFactory;

public class DatabaseCleaner {

	public void clearAllTables() {
        // O comando TRUNCATE limpa tudo e reseta os IDs para 1 (RESTART IDENTITY)
        // O CASCADE garante que apague na ordem certa sem dar erro de chave estrangeira
        String sql = "TRUNCATE TABLE consulta, animal, veterinario, proprietario RESTART IDENTITY CASCADE";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) { // <--- MUDANÇA AQUI: use createStatement()

            // Agora sim, podemos passar a string 'sql' aqui dentro
            stmt.executeUpdate(sql); 
            
            System.out.println(">>> BANCO DE DADOS LIMPO E IDs RESETADOS! <<<");

        } catch (SQLException e) {
            System.err.println("Erro ao limpar banco: " + e.getMessage());
        }
    }
}