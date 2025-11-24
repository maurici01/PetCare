package com.petcare.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.petcare.dao.AnimalDAO;
import com.petcare.dao.ConsultaDAO;
import com.petcare.dao.ProprietarioDAO;
import com.petcare.dao.VeterinarioDAO;
import com.petcare.model.Animal;
import com.petcare.model.Consulta;
import com.petcare.model.Proprietario;
import com.petcare.model.Veterinario;
import com.petcare.util.DatabaseCleaner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("=== SISTEMA PETCARE - INÍCIO DOS TESTES ===");

        //LIMPEZA DO BANCO (Para começar do zero)
        System.out.println("\n>>> [SETUP] Limpando dados antigos...");
        DatabaseCleaner cleaner = new DatabaseCleaner();
        cleaner.clearAllTables();
        System.out.println(">>> [SETUP] Banco limpo.");

        //Instanciando os DAOs
        ProprietarioDAO propDAO = new ProprietarioDAO();
        VeterinarioDAO vetDAO = new VeterinarioDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        ConsultaDAO consultaDAO = new ConsultaDAO();

        //TESTES DE PROPRIETÁRIO
        System.out.println("1. TESTES DE PROPRIETÁRIO");

        //Inserção Válida
        System.out.println("-> Tentando inserir 'Carlos Silva' (Válido)...");
        Proprietario p1 = new Proprietario("Carlos Silva", "11111111111", "71999991111", "Rua A", "carlos@email.com");
        propDAO.create(p1);

        //Teste de Erro: CPF Duplicado
        System.out.println("\n-> Tentando inserir CPF DUPLICADO (Deve dar erro no DAO)...");
        Proprietario p2 = new Proprietario("Carlos Clone", "11111111111", "7188888888", "Rua B", "clone@email.com");
        propDAO.create(p2);

        //TESTES DE VETERINÁRIO
        System.out.println("2. TESTES DE VETERINÁRIO");

        //Inserção Válida
        System.out.println("-> Tentando inserir 'Dra. Ana' (Válido)...");
        Veterinario v1 = new Veterinario("Dra. Ana", "CRMV-1234", "Dermatologia", "71988882222");
        vetDAO.create(v1);

        //Teste de Erro: CRMV Duplicado
        System.out.println("\n-> Tentando inserir CRMV DUPLICADO (Deve dar erro no DAO)...");
        Veterinario v2 = new Veterinario("Dr. Evil", "CRMV-1234", "Geral", "00000000");
        vetDAO.create(v2); 

        //TESTES DE ANIMAL
        System.out.println("3. TESTES DE ANIMAL");

        //Inserção Válida (Dono ID 1 existe)
        System.out.println("-> Tentando inserir 'Rex' para Dono ID 1 (Válido)...");
        Animal a1 = new Animal("Rex", "Cachorro", "Labrador", LocalDate.of(2021, 5, 20), 25.5, 1);
        animalDAO.create(a1);

        //Teste de Erro: Dono Inexistente (FK)
        System.out.println("\n-> Tentando inserir Animal para Dono ID 99 (Deve dar erro de FK)...");
        Animal aErro = new Animal("Fantasma", "Gato", "Persa", LocalDate.now(), 5.0, 99);
        animalDAO.create(aErro);

        //TESTES DE CONSULTA
        System.out.println("4. TESTES DE CONSULTA");

        //Inserção Válida (Animal 1 e Vet 1 existem)
        System.out.println("-> Agendando consulta válida...");
        Consulta c1 = new Consulta(LocalDateTime.now(), "Vacinação Anual", 150.00, 1, 1);
        consultaDAO.create(c1);

        //Teste de Erro: Animal Inexistente
        System.out.println("\n-> Tentando agendar para Animal ID 50 (Deve dar erro de FK)...");
        Consulta cErro = new Consulta(LocalDateTime.now(), "Erro", 0.0, 50, 1);
        consultaDAO.create(cErro);

        //RELATÓRIO (REQUISITO DA PROVA)
        System.out.println("5. RELATÓRIO DE CONSULTAS POR CPF");
        
        String cpfBusca = "11111111111";
        System.out.println("Buscando histórico do CPF: " + cpfBusca);
        
        List<String> relatorio = consultaDAO.findByCpf(cpfBusca);
        
        if (relatorio.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (String linha : relatorio) {
                System.out.println("[REGISTRO] " + linha);
            }
        }

        //TESTE DE INTEGRIDADE (DELETE)
        System.out.println("6. TESTE DE EXCLUSÃO (INTEGRIDADE)");

        //Tentar apagar Dono que tem Animal (Deve ser bloqueado)
        System.out.println("-> Tentando apagar o Dono (ID 1) que possui Animais...");
        // O DAO deve mostrar erro de violação de Foreign Key
        propDAO.deleteByCpf("11111111111"); 

        System.out.println("\n-> Teste Finalizado. Verifique as mensagens de erro acima.");
	}
	
}
