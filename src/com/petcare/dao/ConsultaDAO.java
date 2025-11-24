package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.petcare.connection.ConnectionFactory;
import com.petcare.model.Consulta;

public class ConsultaDAO {

	// Comando de criar (Inserindo dados na tabela)
	public void create(Consulta c) {
		// comando sql (do seu banco de dados)
		String sql = "INSERT INTO consulta (data_hora, diagnostico, valor, id_animal, id_veterinario) VALUES (?, ?, ?, ?, ?)";

		// try-with-resources (inserção)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {

			stat.setTimestamp(1, Timestamp.valueOf(c.getDataHora()));
			stat.setString(2, c.getDiagnostico());
			stat.setDouble(3, c.getValor());
			stat.setInt(4, c.getIdAnimal());
			stat.setInt(5, c.getIdVeterinario());

			stat.executeUpdate();
			System.out.println("Consulta criada");

		} catch (SQLException e) {
			System.err.println("Erro ao criar consulta: " + e.getMessage());
		}
	}

	// READ (Buscar historico por cpf)
	public List<String> findByCpf(String cpfDono) {
		List<String> hist = new ArrayList<String>();
		// comando sql (do seu banco de dados)
		String sql = "SELECT c.data_hora, c.diagnostico, c.valor, a.nome AS nome_animal, v.nome AS nome_vet "
				+ "FROM consulta c " + "JOIN animal a ON c.id_animal = a.id_animal " // Correção: a.id -> a.id_animal
				+ "JOIN proprietario p ON a.id_proprietario = p.id_proprietario " // Correção: p.id -> p.id_proprietario
				+ "JOIN veterinario v ON c.id_veterinario = v.id_veterinario " // Correção: v.id -> v.id_veterinario
				+ "WHERE p.cpf = ?";
		// try-with-resources (Busca/read)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, cpfDono);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				String linha = "DATA: " + rs.getTimestamp("data_hora").toLocalDateTime() + " | ANIMAL: "
						+ rs.getString("nome_animal") + " | VET: " + rs.getString("nome_vet") + " | DIAG: "
						+ rs.getString("diagnostico") + " | VALOR: R$ " + rs.getDouble("valor");
				hist.add(linha);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar histórico: " + e.getMessage());
		}

		return hist;
	}

	// 3. UPDATE (Atualizar Diagnóstico ou Valor)// 3. UPDATE (Atualizar Diagnóstico
	// ou Valor)
	public void update(Consulta c) {
		// comando sql (do seu banco de dados)
		String sql = "UPDATE consulta SET diagnostico = ?, valor = ? WHERE id = ?";
		// try-with-resources (Update)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, c.getDiagnostico());
			stat.setDouble(2, c.getValor());
			stat.setInt(3, c.getId());
			stat.executeUpdate();
			System.out.println("Consulta atualizada");
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar a consulta: " + e.getMessage());
		}
	}

	// 4. DELETE
	public void delete(int id) {
		// comando sql (do seu banco de dados)
		String sql = "DELETE FROM consulta WHERE id = ?";
		// try-with-resources (DELETE)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, id);
			stat.executeUpdate();
			System.out.println("Consulta apagada");
		} catch (Exception e) {
			System.err.println("Erro ao apagar consulta: " + e.getMessage());
		}
	}
}