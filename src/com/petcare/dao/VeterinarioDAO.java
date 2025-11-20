package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petcare.connection.ConnectionFactory;
import com.petcare.model.Veterinario;

public class VeterinarioDAO {

	// create
	public void create(Veterinario v) {
		// comando sql (do seu banco de dados)
		String sql = "INSERT INTO veterinario (nome, crmv, especialidade, telefone) VALUES (?, ?, ?, ?)";
		// try-with-resources (inserção)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, v.getNome());
			stat.setString(2, v.getCrmv());
			stat.setString(3, v.getEspecialidade());
			stat.setString(4, v.getTelefone());
			stat.executeUpdate();
			System.out.println("Veterinario cadastrado");
		} catch (SQLException e) {
			System.err.println("Erro ao cadastrar o veterinario" + e.getMessage());
		}
	}

	// READ (Listar Todos os veterinarios)
	public List<Veterinario> findAll() {
		// comando sql (do seu banco de dados)
		String sql = "SELECT * FROM veterinario";
		List<Veterinario> lista = new ArrayList<Veterinario>();
		// try-with-resources (Listar todos)
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery()) {
			while (rs.next()) {
				Veterinario v = new Veterinario();
				v.setId(rs.getInt("id_veterinario"));
				v.setNome(rs.getString("nome"));
				v.setCrmv(rs.getString("crmv"));
				v.setEspecialidade(rs.getString("especialidade"));
				v.setTelefone(rs.getString("telefone"));
				lista.add(v);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar veterinario :" + e.getMessage());
		}
		return lista;
	}

	// [Update] (Atualizar baseado no crmv)
	// Precisa ser refatorado
	public void update(Veterinario v) {
		// comando sql (do seu banco de dados)
		String sql = "UPDATE veterinario SET nome = ?, especialidade = ?, telefone = ? WHERE crmv = ?";
		// try-with-resources (update)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, v.getNome());
			stat.setString(2, v.getEspecialidade());
			stat.setString(3, v.getTelefone());
			stat.setString(4, v.getCrmv()); // crmv (WHERE)
			stat.executeUpdate();
			System.out.println("Veterinario atualizado!");
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar o veterinario: " + e.getMessage());
		}
	}

	// Delete pelo crmv
	public void delete(String crmv) {
		// comando sql (do seu banco de dados)
		String sql = "DELETE FROM veterinario WHERE crmv = ?";
		// try-with-resources (delete)
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, crmv);
			stat.executeUpdate();
			System.out.println("Veterinario deletado");
		} catch (SQLException e) {
			System.err.println("Erro ao deletar veterinario" + e.getMessage());
		}
	}

}
