
import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeCursos {
	private static GerenciadorDeCursos gerenciadorDeCursos = new GerenciadorDeCursos();
	private static GerenciadorDeDisciplinas gerenciadorDeDisciplinas = new GerenciadorDeDisciplinas();

	private static void processoDePesquisarPorId(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um curso por id...");
		System.out.println("--------------------------");
		System.out.println("Digite o id do curso que deseja pesquisar (numérico): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		//
		Curso curso = gerenciadorDeCursos.pesquisarPorId(id);
		if (curso != null){
			System.out.println();
			System.out.println("Curso");
			System.out.println("====================");
			System.out.println("Id:\t " + curso.getId());
			System.out.println("Nome:\t " + curso.getNome());
			for (Disciplina disciplina : curso.getDisciplinas()) {
				System.out.println("Disciplina:\t " + disciplina.getNome());
			}
		} else {
			System.out.println("Curso não encontrada!");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorNome(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando curso por nome...");
		System.out.println("--------------------------");
		System.out.println("Digite o nome do curso que deseja pesquisar (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Curso> cursos = gerenciadorDeCursos.pesquisarPorNome(nome);
		System.out.println("====================");
		for (Curso curso : cursos) {
			System.out.println("Id:\t " + curso.getId());
			System.out.println("Nome:\t " + curso.getNome());
			System.out.println("====================");
		}
		System.out.println();
	}

	private static void processoDeExcluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Excluindo um cursoa...");
		System.out.println("--------------------------");
		System.out.println("Digite o id do curso que deseja excluir (numérico): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		//
		Curso curso = gerenciadorDeCursos.pesquisarPorId(id);
		if (curso != null){
			gerenciadorDeCursos.excluir(curso);
			System.out.println("Curso excluida!");
		} else {
			System.out.println("Curso não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeAlterar(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Alterando um curso...");
		System.out.println("--------------------------");
		System.out.println("Digite o id de um curso que deseja alterar (numérico): ");
		int id = scanner.nextInt(); scanner.nextLine();
		System.out.println("Digite o nome do curso (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		while(true){
			System.out.println("Digite o id da disciplina do curso ou 999 para finalizar (numérico): ");
			int disciplinaId = scanner.nextInt(); scanner.nextLine();
			//
			if (disciplinaId == 999){
				break;
			}
			//
			Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
			if (disciplina != null){
				disciplinas.add(disciplina);
				System.out.println("Disciplina adicionada ao curso");
			} else {
				System.out.println("Disciplina não encontrada");
			}
		}
		//
		Curso curso = new Curso();
		curso.setId(id);
		curso.setNome(nome);
		curso.setDisciplinas(disciplinas);
		//
		Curso cursoAntigo = gerenciadorDeCursos.pesquisarPorId(id);
		if (cursoAntigo != null){
			gerenciadorDeCursos.alterar(curso);
			System.out.println("Curso alterada!");
		} else {
			System.out.println("Curso não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeIncluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Inserindo um curso...");
		System.out.println("--------------------------");
		System.out.println("Digite o id de um curso que deseja alterar (numérico): ");
		int id = scanner.nextInt(); scanner.nextLine();
		System.out.println("Digite o nome do curso (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		while(true){
			System.out.println("Digite o id da disciplina do curso ou 999 para finalizar (numérico): ");
			int disciplinaId = scanner.nextInt(); scanner.nextLine();
			//
			if (disciplinaId == 999){
				break;
			}
			//
			Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
			if (disciplina != null){
				disciplinas.add(disciplina);
				System.out.println("Disciplina adicionada ao curso");
			} else {
				System.out.println("Disciplina não encontrada");
			}
		}
		//
		Curso curso = new Curso();
		curso.setId(id);
		curso.setNome(nome);
		curso.setDisciplinas(disciplinas);
		//
		gerenciadorDeCursos.inserir(curso);
		//
		System.out.println("Curso inserido!");
		System.out.println();
	}
	
	public static void imprimirMenu(Scanner scanner){
		System.out.println("--------------------------");
		System.out.println("Opções para Cursos:");
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
