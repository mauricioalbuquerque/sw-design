package br.edu.impacta.ads;

import java.util.*;
import static java.lang.System.out;


public class AgendaApp {
	private static Scanner entrada = new Scanner(System.in);
	//private static List<Contato> contatos = new ArrayList<>();
	//private static IContatoDao dao = new ContatoDao();
	private static IContatoDao dao = new ContatoDaoArquivo("contatos.txt");
	public static void main(String[] args){
		boolean sair= false;
		while(!sair){
			int opcao= apresantarMenuPrincipal();
			switch(opcao){
			case 1: inserirContato(); break;
			case 2: buscarContato(); break;
			case 3: sair= true; break;
			default: out.println("ERRO: opção invalida");
			
			}
		}
		out.println("\nFim do Programa");
	}
	private static void inserirContato() {
		out.println("\nINSERÇÃO DE NOVO CONTATO:");
		String nome= lerNome();
		String telefone = lerTelefone();
		Contato c = new Contato(nome,telefone);
		//if(contatos.contains(c)){
		if(dao.existe(c)){
			out.println("Este contato já esta cadastrado!");
		}else{
			//contatos.add(c);
			dao.inserir(c);
			out.println("contato inserido");
		}
		
	}
	private static String lerTelefone() {
		boolean valido = false;
		String telefone = "";
		while(!valido){
			out.print("telefone: ");
			telefone = entrada.nextLine();
			if(telefone.length() == 0 || telefone.length() >25){
				out.println("tamannho de telefone invalido");
			}else{
				valido = true;
			}
			
		}
		return telefone;
	}
	private static String lerNome() {
		boolean valido = false;
		String nome ="";
		while(!valido){
			out.println("Nome: ");
			nome = entrada.nextLine();
			if(nome.length()==0 || nome.length() >200){
				out.println("Erro: nome de tamanho invalido!");
			}else{valido = true;}
		}
		return nome;
	}
	private static int apresantarMenuPrincipal() {
		boolean inteiro = false;
		int opcao = 0;
		while(!inteiro){
			out.println("\nAGENDA TELEFONICA!");
			out.println("(1) Inserir");
			out.println("(2) Buscar");
			out.println("(3) Sair");
			out.print("Escolha uma opção");
			String s = entrada.nextLine();
			try{
				opcao = Integer.parseInt(s);
				inteiro= true;
			}catch(Exception e){
				out.println("ERRO: opção deve ser um valor inteiro");
			}
		}
		return opcao;
	}
	private static void buscarContato(){
		out.println("\nBUSCA DE CONTATOS:");
		String nome = lerNome();
	/*	List<Contato>resultado = new ArrayList<>();
	*	for(Contato c : contatos){
	*	if(nome.equals(c.getNome())){
	*	resultado.add(c);
	*	}
	*	}
	*/
		List<Contato>resultado = dao.buscar(nome);
		if(resultado.size() == 0){
		out.println("Não há contato com este Nome!");
		}else{
		out.println("\nResultado da busca: ");
		for(Contato c: resultado){
		out.println(c);
		}
		}
	}
}
