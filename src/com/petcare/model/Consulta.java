package com.petcare.model;

import java.time.LocalDateTime;

public class Consulta {
	/*
	 * data/hora, animal atendido, veterinário responsável, diagnóstico e valor;
	 * consulta por CPF
	 */
	private int id;
	private LocalDateTime dataHora;
	private String diagnostico;
	private double valor;
	private int idAnimal;
	private int idVeterinario;

	public Consulta() {
	}

	public Consulta(LocalDateTime dataHora, String diagnostico, double valor, int idAnimal, int idVeterinario) {
		this.dataHora = dataHora;
		this.diagnostico = diagnostico;
		this.valor = valor;
		this.idAnimal = idAnimal;
		this.idVeterinario = idVeterinario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public int getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", dataHora=" + dataHora + ", diagnostico=" + diagnostico + ", valor=" + valor
				+ ", idAnimal=" + idAnimal + ", idVeterinario=" + idVeterinario + "]";
	}
}
