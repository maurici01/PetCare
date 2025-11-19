package com.petcare.application;

import java.time.LocalDate;

import com.petcare.dao.AnimalDAO;
import com.petcare.dao.ProprietarioDAO;
import com.petcare.model.Animal;
import com.petcare.model.Proprietario;

public class Main {

	public static void main(String[] args) {

		ProprietarioDAO dao = new ProprietarioDAO();
		
		//Criando 1 Proprietario
		Proprietario p = new Proprietario("Italo BETINHA", "52", "9589999", "Em casa", "Italo@ilo.com");
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
		
		//Read
		System.out.println("Animais");
		for(Animal a: animal.findAll()) {
			System.out.println(a.toString());
		}
	}
	
}
