package com.petcare.application;

import java.time.LocalDate;

import com.petcare.dao.AnimalDAO;
import com.petcare.dao.ProprietarioDAO;
import com.petcare.dao.VeterinarioDAO;
import com.petcare.model.Animal;
import com.petcare.model.Proprietario;
import com.petcare.model.Veterinario;

public class Main {

	public static void main(String[] args) {

		ProprietarioDAO dao = new ProprietarioDAO();
		
		//Criando 1 Proprietario
		Proprietario p = new Proprietario("Italo", "52", "9589999", "Em casa", "Italo@italo.com");
		dao.create(p);
		
		//Read
		System.out.println("Proprietários");
		for(Proprietario prop: dao.findAll()) {
			System.out.println(prop.toString());
		}
		
		//update
		p.setNome("Italo");
		p.setEmail("naotem@gmail.com");;
		dao.update(p);
		
		//delete
		//dao.deleteByCpf("52");
		
		//Instancia o animalDAO
		AnimalDAO animal = new AnimalDAO();
		
		//Registrando um animal
		Animal ani = new Animal("Bog", "Dog", "vira-lata", LocalDate.of(2025, 1, 24), 34.5 ,16 );
		animal.create(ani);
		
		//Read de animal (lista todos os animais)
		System.out.println("Animais");
		for(Animal a: animal.findAll()) {
			System.out.println(a.toString());
		}
		
		//Atualizando o animal 
		ani.setId(3);
		ani.setPeso(25.0);
		ani.setNome("Cavalo Marinho");
		ani.setEspecie("Aqua");
		ani.setRaca("Alien");
		ani.setDataNascimento(LocalDate.of(2023, 9, 29));
		//a parte que atualiza no banco de dados
		animal.update(ani);
		
		//deletando o animal pelo id
		//animal.delete(2); //deletando o animal passando o id
		
		//Declarando um veterinario
		System.out.println("Veterinario");
		VeterinarioDAO vetDao = new VeterinarioDAO();
		
		//Registrando um veterinario
		Veterinario vet = new Veterinario("Italo", "9575", "Adestrador", "719888888");
		vetDao.create(vet);
		
		//Listando os veterinarios
		for(Veterinario v: vetDao.findAll()) {
			System.out.println(v);
		}
		
		//Atualizar o veterinario
		
		Veterinario vetAt = new Veterinario();
		vetAt.setCrmv("24");
		vetAt.setNome("Cabeça");
		vetAt.setTelefone("711111");
		vetAt.setEspecialidade("Sei não");
		vetDao.update(vetAt);
		
		//delete
		//vetDao.delete("9575");;
	}
	
}
