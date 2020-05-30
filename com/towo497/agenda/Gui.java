package com.towo497.agenda;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Gui extends JFrame {		

	private static final long serialVersionUID = 1L;

	Agenda agenda = new Agenda();
	
	DefaultListModel<Tarefa> listModel;
	JList<Tarefa> listaDeTarefas;

	public void construir() {
		//setLayout(new FlowLayout());
		
		//cria uma listModel
		listModel = new DefaultListModel<Tarefa>();
		//cria um JList de tarefas com a listModel 
		listaDeTarefas = new JList<Tarefa>(listModel);
		//configura a apresentação da JList
		listaDeTarefas.setVisibleRowCount(5);
		listaDeTarefas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//cria os botões com os comandos da agenda
		//Carregar, Incluir, Excluir, Salvar
		JButton botCarregar = new JButton("Carregar");
		botCarregar.addActionListener(new CarregarListener());
		JButton botIncluir = new JButton("Incluir");
		botIncluir.addActionListener(new IncluirListener());
		JButton botExcluir = new JButton("Excluir");
		botExcluir.addActionListener(new ExcluirListener());
		JButton botSalvar = new JButton("Salvar");
		botSalvar.addActionListener(new SalvarListener());
		//cria um painel para adicionar os botões
		JPanel painelDeBotoes = new JPanel();
		painelDeBotoes.add(botCarregar);		
		painelDeBotoes.add(botIncluir);
		painelDeBotoes.add(botExcluir);
		painelDeBotoes.add(botSalvar);
		//adiciona no frame a brra de rolagem que contém a lista e o painel de botões
		add(new JScrollPane(listaDeTarefas));
		getContentPane().add(BorderLayout.SOUTH, painelDeBotoes);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocation(700, 500);
		setVisible(true);
	}
	
	public class CarregarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser load = new JFileChooser();
			load.showOpenDialog(getParent());
			agenda = agenda.carregar(load.getSelectedFile());
			agenda.listarNaListaSelecionavel(listModel);			
		}

	}
	
	public class IncluirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String mes = JOptionPane.showInputDialog("Digite o mês (n\u00b0 de 01 a 12): ");
			
			while (!mes.matches("0\\d") && !mes.matches("1[012]")) {

				JOptionPane.showMessageDialog(getParent(), null, "Valor inválido!", JOptionPane.ERROR_MESSAGE);
				mes = JOptionPane.showInputDialog("Digite o mês (n\u00b0 de 01 a 12): ");

			}

			String dia = JOptionPane.showInputDialog("Digite o dia (n\u00b0 de 01 a 31): ");
			
			while (!dia.matches("[012]\\d") && !dia.matches("3[01]")) {

				JOptionPane.showMessageDialog(getParent(), null, "Valor inválido!", JOptionPane.ERROR_MESSAGE);
				dia = JOptionPane.showInputDialog("Digite o dia (n\u00b0 de 01 a 31): ");

			}

			String titulo = JOptionPane.showInputDialog("Digite um título para a tarefa: ");
			String descricao = JOptionPane.showInputDialog("Digite uma pequena descrição: ");	
			Tarefa tar = new Tarefa(mes, dia, titulo, descricao);
			agenda.incluirNaListaSelecionavel(tar, listModel);

		}

	}
	
	public class ExcluirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			agenda.excluirDaListaSelecionavel(listaDeTarefas.getSelectedIndices(), listModel);

		}

	}	

	public class SalvarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser save = new JFileChooser();
			save.showSaveDialog(getParent());			
			agenda.salvar(save.getSelectedFile());			
		}

	}

}
