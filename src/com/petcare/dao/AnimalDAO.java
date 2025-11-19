package com.petcare.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petcare.connection.ConnectionFactory;
import com.petcare.model.Animal;

public class AnimalDAO {

	// Create (Inserindo um animal na tabela)
	public void create(Animal animal) {
		// comando sql (do seu banco de dados)
		String sql = "INSERT INTO animal (nome, especie, raca, data_nascimento, peso, id_proprietario) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {

			stat.setString(1, animal.getNome());
			stat.setString(2, animal.getEspecie());
			stat.setString(3, animal.getRaca());
			// Convertendo um LocalDate pra Date
			stat.setDate(4, Date.valueOf(animal.getDataNascimento()));
			stat.setDouble(5, animal.getPeso());
			stat.setInt(6, animal.getIdProprietario());
			stat.executeUpdate();
			System.out.println("Animal registrado");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o animal: " + e.getMessage());
		}
	}

	// READ (Listar Todos os animais)
	public List<Animal> findAll() {
		// comando sql (do seu banco de dados)
		String sql = "SELECT * FROM animal";
		List<Animal> ani = new ArrayList<Animal>();

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery()) {

			while (rs.next()) {
				Animal a = new Animal();
				a.setId(rs.getInt("id_animal"));
				a.setNome(rs.getString("nome"));
				a.setEspecie(rs.getString("especie"));
				a.setRaca(rs.getString("raca"));
				// conversão novamente
				a.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				a.setPeso(rs.getDouble("peso"));
				a.setIdProprietario(rs.getInt("id_proprietario"));
				ani.add(a);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar os animais: " + e.getMessage());
		}
		return ani;
	}
}
