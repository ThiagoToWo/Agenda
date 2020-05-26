package com.towo497.agenda;

import java.io.File;
import java.util.Scanner;

public class MecanicaAgenda {
	
	private Scanner input = new Scanner(System.in);	
	private Agenda ag;
	private String mes;
	private String dia;
	private String titulo;
	private String descricao;
	
	public void iniciar() {		
		String option;
		ag = new Agenda();
		do {
			System.out.println("Escolha uma opção: Carregar = 0, Incluir = 1, Listar = 2, Excluir = 3, Salvar = 4, Sair = 5");
			option = input.nextLine();
			
			switch (option) {
			case "0": //carregar a agenda
				System.out.println("Digite o endereço do arquivo: ");
				String pathnameLoad = input.nextLine();		
				File arquivoLoad = new File(pathnameLoad);
				ag = ag.carregar(arquivoLoad);
				break;
			case "1": //incluir tarefas
				String continuar;
				do {
					System.out.print("Digite o mês (n\u00b0 de 01 a 12): ");
					mes = input.nextLine();
					while (!mes.matches("0\\d") && !mes.matches("1[012]")) {
						System.out.println("Valor inválido!");
						System.out.print("Digite o mês (n\u00b0 de 01 a 12): ");
						mes = input.nextLine();
					}
					System.out.print("Digite o dia (n\u00b0 de 01 a 31): ");
					dia = input.nextLine();
					while (!dia.matches("[012]\\d") && !dia.matches("3[01]")) {
						System.out.println("Valor inválido!");
						System.out.print("Digite o dia (n\u00b0 de 01 a 31): ");
						dia = input.nextLine();
					}
					System.out.print("Digite um título para a tarefa: ");
					titulo = input.nextLine();
					System.out.print("Digite uma pequena descrição: ");
					descricao = input.nextLine();
					Tarefa tar = new Tarefa(mes, dia, titulo, descricao);
					ag.incluir(tar);
					System.out.print("Deseja incluir outra tarefa? (sim = 1, não = 2) ");
					continuar = input.nextLine();
				} while(continuar.equals("1"));				
				break;
			case "2": //listar tarefas
				ag.listar();
				break;
			case "3": //excluir tarefas
				System.out.print("Digite o número da tarefa a ser excluída ou \"s\" para terminar: ");
				String numero = input.nextLine();
				ag.excluir(numero);
				break;
			case "4": //salvar agenda
				System.out.println("Digite o endereço do arquivo: ");
				String pathnameSave = input.nextLine();		
				File arquivoSave = new File(pathnameSave);
				ag.salvar(arquivoSave);
				break;
			case "5":
				ag.sair();
				break;
			default:
				System.out.println("Valor inválido!");
				break;
			}
		} while(!option.equals("5"));
	}	
}
