package com.towo497.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.DefaultListModel;

public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
	public Tarefa hoje;
	
	public Agenda() {
		adicionarDataAtual();
	}
	
	public Agenda carregar(File arquivo) {
		Agenda agenda = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(arquivo));
			agenda = (Agenda) oi.readObject();
			//agenda.listar(); versão para linha de comando
			agenda.adicionarDataAtual();
			oi.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		}			
		return agenda;
	}
	//versão para linha de comando
	public void incluir(Tarefa tarefa) {
		listaTarefas.add(tarefa);
		Collections.sort(listaTarefas);
	}
	//versão para linha de comando
	public void listar() {
		System.out.println("==============================================================");
		System.out.println("Lista de tarefas (Tarefa n\u00b0. mês/dia - título: descrição)");
		System.out.println("==============================================================");
		for (int i = 1; i <= listaTarefas.size(); i++) {
			System.out.println("Tarefa n\u00b0 " + i + ". " + listaTarefas.get(i - 1));
			System.out.println("----------------------------------------------------------------------");
		}		
	}	
	//versão para linha de comando
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
		listaTarefas.remove(hoje);
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivo));
			os.writeObject(this);
			os.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
			return;
		}
	}
	//versão para linha de comando
	public void sair() {
		System.out.println("Programa finalizado.");
		return;
	}
	
	//métodos que usam DefaultListModel<Tarefa> de uma gui.
	public void listarNaListaSelecionavel(DefaultListModel<Tarefa> listModel) {	
		listModel.removeAllElements();
		listModel.addAll(listaTarefas);
	}

	public void incluirNaListaSelecionavel(Tarefa tar, DefaultListModel<Tarefa> listModel) {		
		listaTarefas.add(tar);
		Collections.sort(listaTarefas);
		listarNaListaSelecionavel(listModel);
	}

	public void excluirDaListaSelecionavel(int[] indices, DefaultListModel<Tarefa> listModel) {
		//listModel.removeElement(i);
		ArrayList<Tarefa> listaRemovidos = new ArrayList<Tarefa>();
		for (int i : indices) {
			listaRemovidos.add(listaTarefas.get(i));
		}
		listaTarefas.removeAll(listaRemovidos);				
		Collections.sort(listaTarefas);
		listarNaListaSelecionavel(listModel);
	}
	
	private void adicionarDataAtual() {
		Calendar data = Calendar.getInstance();	
		int mesAtualInt =  data.get(Calendar.MONTH) + 1;
		String mesAtual = null;
		
		if (mesAtualInt < 10) {
			mesAtual = "0" + mesAtualInt;
		} else {
			mesAtual = "" + mesAtualInt;
		}	
		
		int diaAtualInt = data.get(Calendar.DAY_OF_MONTH);
		String diaAtual = null;
		
		if (diaAtualInt < 10) {
			diaAtual = "0" + diaAtualInt;
		} else {
			diaAtual = "" + diaAtualInt;
		}	
		hoje = new Tarefa(mesAtual, diaAtual, "\u0394 HOJE", "FIQUE DE OLHO NAS PRÓXIMAS TAREFAS");
		listaTarefas.add(hoje);
		Collections.sort(listaTarefas);
	}
	
}
