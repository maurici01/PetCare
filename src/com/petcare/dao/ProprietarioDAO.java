package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petcare.connection.ConnectionFactory;
import com.petcare.model.Proprietario;

public class ProprietarioDAO {

	// Comando de criar (Inserindo dados na tabela)
	public void create(Proprietario p) {
		// comando sql (do seu banco de dados)
		String sql = "INSERT INTO proprietario (nome, cpf, telefone, email, endereco) VALUES (? , ? , ?, ? , ?)";

		// try-with-resources (inserção)
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, p.getNome());
			stat.setString(2, p.getCpf());
			stat.setString(3, p.getTelefone());
			stat.setString(4, p.getEmail());
			stat.setString(5, p.getEndereco());
			stat.executeUpdate();
			System.out.println("Proprietario criado");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// READ (Listar Todos os proprietarios)
	public List<Proprietario> findAll() {
		// comando sql (do seu banco de dados)
		String sql = "SELECT * FROM proprietario";
		// Lista de ...
		List<Proprietario> prop = new ArrayList<Proprietario>();

		// try-with-resources (da busca por todos)
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Proprietario p = new Proprietario();
				p.setId(rs.getInt("id_proprietario"));
				p.setNome(rs.getString("nome"));
				p.setCpf(rs.getString("cpf"));
				p.setTelefone(rs.getString("telefone"));
				p.setEmail(rs.getString("email"));
				p.setEndereco(rs.getString("endereco"));
				prop.add(p);	
			}
			System.out.println("Busca Realizada");
		} catch (SQLException e) {
			System.err.println("Erro ao listar : " + e.getMessage());
		}
		return prop;
	}
	
	//findbycpf (passando o cpf do usuario)
	
	// Update
	public void update(Proprietario prop) {
		// comando sql (do seu banco de dados)
		String sql = "UPDATE proprietario SET nome = ? , telefone = ?, email = ?, endereco = ? WHERE cpf = ?";
		try (Connection conn = ConnectionFactory.getConnection();
			 PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, prop.getNome());
			stat.setString(2, prop.getTelefone());
			stat.setString(3, prop.getEmail());
			stat.setString(4, prop.getEndereco());
			stat.setString(5, prop.getCpf());
			stat.executeUpdate();
			System.out.println("Update realizado com sucesso");
		}catch (SQLException e) {
			System.err.println("Erro ao listar : " + e.getMessage());
		}
	}
	
	//Delete pelo cpf
	public void deleteByCpf(String cpf) {
		// comando sql (do seu banco de dados)
		String sql = "DELETE FROM proprietario WHERE cpf = ?";
		
		//try-catch-resources (para a remoção)
		try (Connection conn = ConnectionFactory.getConnection();
			  PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, cpf);
			stat.executeUpdate();
			System.out.println("Proprietário removido");
		} catch (SQLException e) {
			System.err.println("Erro ao remover: " + e.getMessage());
		}
		
	}
}
