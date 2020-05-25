package com.towo497.agenda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MecanicaAgenda {
	private Scanner input = new Scanner(System.in);
	private ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
	
	private String mes;
	private String dia;
	private String titulo;
	private String descricao;
	
	public void iniciar() {		
		String option;
		do {
			System.out.println("Escolha uma opção: Incluir = 1, Listar = 2, Excluir = 3, Sair = 4");
			option = input.nextLine();
			
			switch (option) {
			case "1":
				incluir();
				break;
			case "2":
				listar();
				break;
			case "3":
				excluir();
				break;
			case "4":
				sair();
				break;
			default:
				System.out.println("Valor inválido!");
				break;
			}
		} while(!option.equals("4"));

	}

	private void incluir() {
		String continuar;
		do {
			System.out.print("Digite o mês (n\u00b0 de 01 a 12): ");
			mes = input.nextLine();
			while (!mes.matches("0[1 - 2]") && !mes.matches("1[0 - 2]")) {
				System.out.println("Valor inválido!");
				System.out.print("Digite o mês (n\u00b0 de 01 a 12): ");
				mes = input.nextLine();
			}
			System.out.print("Digite o dia (n\u00b0 de 01 a 31): ");
			dia = input.nextLine();
			while (!dia.matches("[0 - 2]\\d") && !dia.matches("3[0 -1]")) {
				System.out.println("Valor inválido!");
				System.out.print("Digite o dia (n\u00b0 de 01 a 31): ");
				dia = input.nextLine();
			}
			System.out.print("Digite um título para a tarefa: ");
			titulo = input.nextLine();
			System.out.print("Digite uma pequena descrição: ");
			descricao = input.nextLine();
			Tarefa tar = new Tarefa(mes, dia, titulo, descricao);
			listaTarefas.add(tar);
			Collections.sort(listaTarefas);
			System.out.print("Deseja incluir outra tarefa? (sim = 1, não = 2) ");
			continuar = input.nextLine();
		} while(continuar.equals("1"));		
	}
	
	private void listar() {
		for (int i = 1; i <= listaTarefas.size(); i++) {
			System.out.println("Tarefa n\u00b0 " + i + ". " + listaTarefas.get(i - 1));
		}		
	}
	
	private void excluir() {
		System.out.print("Digite o número da tarefa a ser excluída ou \"s\" para terminar: ");
		String numero = input.nextLine();
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
	
	private void sair() {
		System.out.println("Programa finalizado.");
		return;
	}
}
