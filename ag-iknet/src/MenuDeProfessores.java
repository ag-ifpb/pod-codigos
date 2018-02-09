import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeProfessores {
	private static GerenciadorDeCursos gerenciadorDeCursos = new GerenciadorDeCursos();
	private static GerenciadorDeProfessores gerenciadorDeProfessores = new GerenciadorDeProfessores();
	private static GerenciadorDeDisciplinas gerenciadorDeDisciplinas = new GerenciadorDeDisciplinas();

	private static void processoDePesquisarPorId(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um professor por RG...");
		System.out.println("--------------------------");
		System.out.println("Digite o rg do professor que deseja pesquisar (texto): ");
		String rg = scanner.nextLine();
		//
		Professor professor = gerenciadorDeProfessores.pesquisarPorRg(rg);
		if (professor != null){
			System.out.println();
			System.out.println("Professor");
			System.out.println("====================");
			System.out.println("RG:\t " + professor.getRg());
			System.out.println("Nome:\t " + professor.getNome());
			System.out.println("Nascimento:\t " + professor.getDataNascimento());
			System.out.println("Sexo:\t " + professor.getSexo());
			System.out.println("Endereço:\t " + professor.getEndereco());
			System.out.println("Contato:\t " + professor.getContato());
			System.out.println("Graduacao:\t " + professor.getGraduacao());
			for (Disciplina disciplina : professor.getDisciplinas()) {
				System.out.println("Disciplina:\t " + disciplina.getNome());
			}
		} else {
			System.out.println("Professor não encontrada!");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorCurso(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um professor por curso...");
		System.out.println("--------------------------");
		System.out.println("Digite o id do curso que deseja pesquisar (numérico): ");
		int cursoId = scanner.nextInt();
		scanner.nextLine();
		//
		Curso curso = gerenciadorDeCursos.pesquisarPorId(cursoId);
		if (curso != null){
			ArrayList<Professor> professores = gerenciadorDeProfessores.pesquisarPorCurso(curso);
			System.out.println("====================");
			for (Professor professor : professores) {
				System.out.println("RG:\t " + professor.getRg());
				System.out.println("Nome:\t " + professor.getNome());
				System.out.println("Nascimento:\t " + professor.getDataNascimento());
				System.out.println("Sexo:\t " + professor.getSexo());
				System.out.println("Endereço:\t " + professor.getEndereco());
				System.out.println("Contato:\t " + professor.getContato());
				System.out.println("Graduacao:\t " + professor.getGraduacao());
				System.out.println("====================");
			}
		} else {
			System.out.println("Curso não encontrado");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorNome(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um professor por Nome...");
		System.out.println("--------------------------");
		System.out.println("Digite o nome do professor que deseja pesquisar (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Professor> professores = gerenciadorDeProfessores.pesquisarPorNome(nome);
		System.out.println("====================");
		for (Professor professor : professores) {
			System.out.println("RG:\t " + professor.getRg());
			System.out.println("Nome:\t " + professor.getNome());
			System.out.println("Nascimento:\t " + professor.getDataNascimento());
			System.out.println("Sexo:\t " + professor.getSexo());
			System.out.println("Endereço:\t " + professor.getEndereco());
			System.out.println("Contato:\t " + professor.getContato());
			System.out.println("Graduacao:\t " + professor.getGraduacao());
			System.out.println("====================");
		}
		System.out.println();
	}

	private static void processoDeExcluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Excluindo um professor...");
		System.out.println("--------------------------");
		System.out.println("Digite o rg do professor que deseja excluir (texto): ");
		String rg = scanner.nextLine();
		//
		Professor professor = gerenciadorDeProfessores.pesquisarPorRg(rg);
		if (professor != null){
			gerenciadorDeProfessores.excluir(professor);
			System.out.println("Professor excluida!");
		} else {
			System.out.println("Professor não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeAlterar(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Alterando um professor...");
		System.out.println("--------------------------");
		System.out.println("Digite o rg do professor que deseja alterar (texto): ");
		String rg = scanner.nextLine();
		System.out.println("Digite o nome do professor (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite o endereço do professor (texto): ");
		String endereco = scanner.nextLine();
		System.out.println("Digite o contato do professor (texto): ");
		String contato = scanner.nextLine();
		System.out.println("Digite o sexo do professor (texto): ");
		String sexo = scanner.nextLine();
		System.out.println("Digite o graduação do professor (texto): ");
		String graduacao = scanner.nextLine();
		System.out.println("Digite a data de nascimento do professor (dia/mes/ano)(texto): ");
		String dataNascimento = scanner.nextLine();
		//
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		while(true){
			System.out.println("Digite o id da disciplina do professor ou 999 para finalizar (numérico): ");
			int disciplinaId = scanner.nextInt(); scanner.nextLine();
			//
			if (disciplinaId == 999){
				break;
			}
			//
			Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
			if (disciplina != null){
				disciplinas.add(disciplina);
				System.out.println("Disciplina adicionada ao professor");
			} else {
				System.out.println("Disciplina não encontrada");
			}
		}
		//
		Professor professor = new Professor();
		professor.setRg(rg);
		professor.setSexo(sexo);
		professor.setContato(contato);
		professor.setDataNascimento(dataNascimento);
		professor.setEndereco(endereco);
		professor.setGraduacao(graduacao);
		professor.setNome(nome);
		professor.setDisciplinas(disciplinas);
		//
		Professor professorAntigo = gerenciadorDeProfessores.pesquisarPorRg(rg);
		if (professorAntigo != null){
			gerenciadorDeProfessores.alterar(professor);
			System.out.println("Professor alterada!");
		} else {
			System.out.println("Professor não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeIncluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Inserindo um professor...");
		System.out.println("--------------------------");
		System.out.println("Digite o rg do professor que deseja alterar (texto): ");
		String rg = scanner.nextLine();
		System.out.println("Digite o nome do professor (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite o endereço do professor (texto): ");
		String endereco = scanner.nextLine();
		System.out.println("Digite o contato do professor (texto): ");
		String contato = scanner.nextLine();
		System.out.println("Digite o sexo do professor (texto): ");
		String sexo = scanner.nextLine();
		System.out.println("Digite o graduação do professor (texto): ");
		String graduacao = scanner.nextLine();
		System.out.println("Digite a data de nascimento do professor (dia/mes/ano)(texto): ");
		String dataNascimento = scanner.nextLine();
		//
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		while(true){
			System.out.println("Digite o id da disciplina do professor ou 999 para finalizar (numérico): ");
			int disciplinaId = scanner.nextInt(); scanner.nextLine();
			//
			if (disciplinaId == 999){
				break;
			}
			//
			Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
			if (disciplina != null){
				disciplinas.add(disciplina);
				System.out.println("Disciplina adicionada ao professor");
			} else {
				System.out.println("Disciplina não encontrada");
			}
		}
		//
		Professor professor = new Professor();
		professor.setRg(rg);
		professor.setSexo(sexo);
		professor.setContato(contato);
		professor.setDataNascimento(dataNascimento);
		professor.setEndereco(endereco);
		professor.setGraduacao(graduacao);
		professor.setNome(nome);
		professor.setDisciplinas(disciplinas);
		//
		gerenciadorDeProfessores.inserir(professor);
		System.out.println("Professor inserido!");
		System.out.println();
	}
	
	public static void imprimirMenu(Scanner scanner){
		System.out.println("--------------------------");
		System.out.println("Opções para Professores:");
		System.out.println("--------------------------");
		System.out.println("1 - Incluir");
		System.out.println("2 - Alterar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Pesquisar por rg");
		System.out.println("5 - Pesquisar por nome");
		System.out.println("6 - Pesquisar por curso");
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
		} else if (opcao == 6){
			processoDePesquisarPorCurso(scanner);
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
