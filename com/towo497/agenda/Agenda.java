package com.towo497.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
	
	public Agenda carregar(File arquivo) {
		Agenda agenda = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(arquivo));
			agenda = (Agenda) oi.readObject();
			agenda.listar();			
			oi.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		}			
		return agenda;
	}

	public void incluir(Tarefa tarefa) {
		listaTarefas.add(tarefa);
		Collections.sort(listaTarefas);
	}
	
	public void listar() {
		System.out.println("==============================================================");
		System.out.println("Lista de tarefas (Tarefa n\u00b0. mês/dia - título: descrição)");
		System.out.println("==============================================================");
		for (int i = 1; i <= listaTarefas.size(); i++) {
			System.out.println("Tarefa n\u00b0 " + i + ". " + listaTarefas.get(i - 1));
			System.out.println("----------------------------------------------------------------------");
		}		
	}
	
	public void excluir(String numero) {
		
		if (numero.matches("\\d")) {
			int n = Integer.parseInt(numero);
			if (n > 0 && n <= listaTarefas.size()) {
				listaTarefas.remove(n - 1);
				System.out.println("Tarefa n\u00b0 " + numero + " excluída.");
			} else {
				System.out.println("Valor fora do intervalo da lista.");
			}				
		} else {
			if (!numero.equals("s")) {
				System.out.println("Valor inválido. Digite um número ou \"s\" para voltar.");	
			} else {
				return;
			}						
		}				
	}
	
	public void salvar(File arquivo) {
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivo));
			os.writeObject(this);
			os.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
			return;
		}
	}
	
	public void sair() {
		System.out.println("Programa finalizado.");
		return;
	}

}
