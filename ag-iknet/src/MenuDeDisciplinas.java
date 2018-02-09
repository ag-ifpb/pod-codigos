import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeDisciplinas {
	private static GerenciadorDeDisciplinas gerenciadorDeDisciplinas = new GerenciadorDeDisciplinas();

	private static void processoDePesquisarPorId(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando uma disciplina por id...");
		System.out.println("--------------------------");
		System.out.println("Digite o id da disciplina que deseja pesquisar (numérico): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		//
		Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(id);
		if (disciplina != null){
			System.out.println();
			System.out.println("Disciplina");
			System.out.println("====================");
			System.out.println("Id:\t " + disciplina.getId());
			System.out.println("Nome:\t " + disciplina.getNome());
			System.out.println("Carga Horária:\t " + disciplina.getCargaHoraria());
		} else {
			System.out.println("Disciplina não encontrada!");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorNome(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando disciplinas por nome...");
		System.out.println("--------------------------");
		System.out.println("Digite o nome da disciplina que deseja pesquisar (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Disciplina> disciplinas = gerenciadorDeDisciplinas.pesquisarPorNome(nome);
		System.out.println("====================");
		for (Disciplina disciplina : disciplinas) {
			System.out.println("Id:\t " + disciplina.getId());
			System.out.println("Nome:\t " + disciplina.getNome());
			System.out.println("Carga Horária:\t " + disciplina.getCargaHoraria());
			System.out.println("====================");
		}
		System.out.println();
	}

	private static void processoDeExcluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Excluindo uma disciplina...");
		System.out.println("--------------------------");
		System.out.println("Digite o id da disciplina que deseja excluir (numérico): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		//
		Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(id);
		if (disciplina != null){
			gerenciadorDeDisciplinas.excluir(disciplina);
			System.out.println("Disciplina excluida!");
		} else {
			System.out.println("Disciplina não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeAlterar(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Alterando uma disciplina...");
		System.out.println("--------------------------");
		System.out.println("Digite o id da disciplina que deseja alterar (numérico): ");
		int id = scanner.nextInt(); scanner.nextLine();
		System.out.println("Digite o nome da disciplina (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite a carga horária (numérico): ");
		int carga = scanner.nextInt(); scanner.nextLine();
		//
		Disciplina disciplina = new Disciplina();
		disciplina.setId(id);
		disciplina.setNome(nome);
		disciplina.setCargaHoraria(carga);
		//
		Disciplina disciplinaAntiga = gerenciadorDeDisciplinas.pesquisarPorId(id);
		if (disciplinaAntiga != null){
			gerenciadorDeDisciplinas.alterar(disciplina);
			System.out.println("Disciplina alterada!");
		} else {
			System.out.println("Disciplina não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeIncluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Incluindo uma disciplina...");
		System.out.println("--------------------------");
		System.out.println("Digite o id (numérico): ");
		int id = scanner.nextInt(); scanner.nextLine();
		System.out.println("Digite o nome da disciplina (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite a carga horária (numérico): ");
		int carga = scanner.nextInt(); scanner.nextLine();
		//
		Disciplina disciplina = new Disciplina();
		disciplina.setId(id);
		disciplina.setNome(nome);
		disciplina.setCargaHoraria(carga);
		//
		gerenciadorDeDisciplinas.inserir(disciplina);
		//
		System.out.println("Disciplina inserida!");
		System.out.println();
	}
	
	public static void imprimirMenu(Scanner scanner){
		System.out.println("--------------------------");
		System.out.println("Opções para Disciplinas:");
		System.out.println("--------------------------");
		System.out.println("1 - Incluir");
		System.out.println("2 - Alterar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Pesquisar por id");
		System.out.println("5 - Pesquisar por nome");
		System.out.println("9 - Voltar");
		//
		int opcao = scanner.nextInt();
		scanner.nextLine();
		//
		boolean sair = false;
		if (opcao == 1){
			processoDeIncluir(scanner);
		} else if (opcao == 2){
			processoDeAlterar(scanner);
		} else if (opcao == 3){
			processoDeExcluir(scanner);
		} else if (opcao == 4){
			processoDePesquisarPorId(scanner);
		} else if (opcao == 5){
			processoDePesquisarPorNome(scanner);
		} else if (opcao == 9){
			sair = true;
		} else {
			System.out.println("Opção inválida. Tente novamente.");
		}
		//
		if (sair){
			System.out.println("Voltando...");
		} else {
			imprimirMenu(scanner);
		}
	}
}
