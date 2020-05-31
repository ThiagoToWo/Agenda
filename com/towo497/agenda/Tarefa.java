package com.towo497.agenda;

import java.io.Serializable;

public class Tarefa implements Comparable<Tarefa>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String mes;
	private String dia;
	private String data;
	private String titulo;
	private String descricao;
	
	public Tarefa(String mes, String dia, String titulo, String descricao) {
		this.mes = mes;
		this.dia = dia;
		this.data = mes + dia;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public String toString() {
		return mes + "/" + dia + " - " + titulo + ": " + descricao;
	}

	@Override
	public int compareTo(Tarefa outro) {		
		return data.compareTo(outro.data);
	}

}
