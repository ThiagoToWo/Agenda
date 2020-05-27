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
		System.out.println("Agenda Simples");
		do {
			System.out.println("Escolha uma opção: Carregar = 0, Incluir = 1, Listar = 2, Excluir = 3, Salvar = 4, Sair = 5");
			option = input.nextLine();
			
			switch (option) {
			case "0": //carregar a agenda					
				File arquivoLoad = new File("C:\\Users\\Thiago ToWo\\Documents\\Agenda.ser");				
				ag = ag.carregar(arquivoLoad);				
				break;
			case "1": //incluir tarefas
				String continuar;
				loopPrincipal: do {
					System.out.print("Digite o mês (n\u00b0 de 01 a 12) ou -1 para sair: ");
					mes = input.nextLine();
					
					while (!mes.matches("0\\d") && !mes.matches("1[012]")) {
						
						if (!mes.equals("-1")) {
							System.out.println("Valor inválido!");
							System.out.print("Digite o mês (n\u00b0 de 01 a 12) ou -1 para sair: ");
							mes = input.nextLine();
						} else {
							break loopPrincipal;
						}
						
					}
					
					System.out.print("Digite o dia (n\u00b0 de 01 a 31) ou -1 para sair: ");
					dia = input.nextLine();
					
					while (!dia.matches("[012]\\d") && !dia.matches("3[01]")) {
						
						if (!dia.equals("-1")) {
							System.out.println("Valor inválido!");
							System.out.print("Digite o dia (n\u00b0 de 01 a 31) ou -1 para sair: ");
							dia = input.nextLine();
						} else {
							break loopPrincipal;
						}
						
					}
					
					System.out.print("Digite um título para a tarefa ou -1 para sair: ");
					titulo = input.nextLine();
					if (titulo.equals("-1")) {
						break loopPrincipal;
					}
					System.out.print("Digite uma pequena descrição ou -1 para sair: ");
					descricao = input.nextLine();
					if (descricao.equals("-1")) {
						break loopPrincipal;
					}
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
				File arquivoSave = new File("C:\\Users\\Thiago ToWo\\Documents\\Agenda.ser");				
				ag.salvar(arquivoSave);
				System.out.println("Agenda salva com sucesso.");
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
