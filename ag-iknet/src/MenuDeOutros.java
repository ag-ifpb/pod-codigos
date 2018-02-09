import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeOutros {
	private static GerenciadorDeCursos gerenciadorDeCursos = new GerenciadorDeCursos();
	private static GerenciadorDeProfessores gerenciadorDeProfessores = new GerenciadorDeProfessores();
	private static GerenciadorDeAlunos gerenciadorDeAlunos = new GerenciadorDeAlunos();
	private static GerenciadorDeDisciplinas gerenciadorDeDisciplinas = new GerenciadorDeDisciplinas();
	private static GerenciadorDeNotas gerenciadorDeNotas = new GerenciadorDeNotas();

	private static void processoListagemAlunosAprovados(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Listagem de alunos aprovados em uma disciplina...");
		System.out.println("--------------------------");
		System.out.println("Digite o id da disciplina que deseja pesquisar (texto): ");
		int disciplinaId = scanner.nextInt();
		scanner.nextLine();
		//
		Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
		if (disciplina != null){
			ArrayList<Notas> lista = gerenciadorDeNotas.listarAprovados(disciplina);
			for (Notas notas : lista) {
				System.out.println("Notas da disciplina " + disciplina.getNome());
				System.out.println("===========================================");
				System.out.println("Matrícula:\t\t " + notas.getAluno().getMatricula());
				System.out.println("Nome:\t\t " + notas.getAluno().getNome());
				System.out.println("Nota 1:\t\t " + notas.getNota1());
				System.out.println("Nota 2:\t\t " + notas.getNota2());
				System.out.println("Média:\t\t " + notas.media());
			}
		} else {
			System.out.println("Disciplina não encontrada!");
		}
		System.out.println();
	}
	
	private static void processoListagemProfessoresPorCurso(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Listando professores por curso...");
		System.out.println("--------------------------");
		System.out.println("Digite o id do curso que deseja pesquisar (numérico): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		//
		Curso curso = gerenciadorDeCursos.pesquisarPorId(id);
		if (curso == null){
			System.out.println("Curso não encontrado");
			System.out.println();
			return;
		}
		//
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
		System.out.println();
		
	}

	private static void processoListagemProfessoresPorAluno(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Listando professores por aluno...");
		System.out.println("--------------------------");
		System.out.println("Digite a matrícula do aluno que deseja pesquisar (texto): ");
		String matricula = scanner.nextLine();
		//
		Aluno aluno = gerenciadorDeAlunos.pesquisarPorMatricula(matricula);
		if (aluno == null){
			System.out.println("Aluno não encontrado");
			System.out.println();
			return;
		}
		//
		ArrayList<Professor> professores = gerenciadorDeProfessores.pesquisarPorAluno(aluno);
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

	private static void processoListagemProfessoresPorTitulo(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Listando professores por título...");
		System.out.println("--------------------------");
		System.out.println("Digite o título do professor que deseja pesquisar (texto): ");
		String titulo = scanner.nextLine();
		//
		ArrayList<Professor> professores = gerenciadorDeProfessores.pesquisarPorTitulo(titulo);
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

	private static void processoListagemAlunosPorSexo(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Listagem de alunos por sexo...");
		System.out.println("--------------------------");
		System.out.println("Digite o sexo do aluno que deseja pesquisar (texto): ");
		String sexo = scanner.nextLine();
		//
		ArrayList<Aluno> alunos = gerenciadorDeAlunos.pesquisarPorSexo(sexo);
		System.out.println("====================");
		for (Aluno aluno : alunos) {
			System.out.println("Matrícula:\t " + aluno.getMatricula());
			System.out.println("Nome:\t " + aluno.getNome());
			System.out.println("Nascimento:\t " + aluno.getDataNascimento());
			System.out.println("Sexo:\t " + aluno.getSexo());
			System.out.println("Endereço:\t " + aluno.getEndereco());
			System.out.println("Contato:\t " + aluno.getContato());
			System.out.println("Nome dos Pais:\t " + aluno.getNomeDosPais());
			System.out.println("Nome do Curso:\t " + aluno.getCurso().getNome());
			System.out.println("====================");
		}
		System.out.println();
		
	}

	private static void processoInserirNotas(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Notas dos alunos...");
		System.out.println("--------------------------");
		System.out.println("Digite o id da disciplina (numérico): ");
		int disciplinaId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Digite a matrícula do aluno (texto): ");
		String alunoMat = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Digite a primeira nota (numérico): ");
		double nota1 = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Digite a segunda nota (numérico): ");
		double nota2 = scanner.nextDouble();
		scanner.nextLine();
		//
		Disciplina disciplina = gerenciadorDeDisciplinas.pesquisarPorId(disciplinaId);
		if (disciplina == null){
			System.out.println("Disciplina não encontrada");
			System.out.println();		
			return;
		}
		Aluno aluno = gerenciadorDeAlunos.pesquisarPorMatricula(alunoMat);
		if (aluno == null){
			System.out.println("Aluno não encontrada");
			System.out.println();		
			return;
		}
		//
		Notas notas = new Notas(disciplina, aluno);
		notas.setNota1(nota1);
		notas.setNota2(nota2);
		//
		System.out.println("Notas inseridas!");
		System.out.println();		
	}
	
	public static void imprimirMenu(Scanner scanner){
		System.out.println("--------------------------");
		System.out.println("Outras Opções:");
		System.out.println("--------------------------");
		System.out.println("1 - Cadastrar notas de alunos por disciplina");
		System.out.println("2 - Listagem de alunos aprovados por disciplina");
		System.out.println("3 - Listagem de alunos por sexo");
		System.out.println("4 - Listagem de professor por titulação");
		System.out.println("5 - Listagem de professores por aluno");
		System.out.println("6 - Listagem de professores por curso");
		System.out.println("9 - Voltar");
		//
		int opcao = scanner.nextInt();
		scanner.nextLine();
		//
		boolean sair = false;
		if (opcao == 1){
			processoInserirNotas(scanner);
		} else if (opcao == 2){
			processoListagemAlunosAprovados(scanner);
		} else if (opcao == 3){
			processoListagemAlunosPorSexo(scanner);
		} else if (opcao == 4){
			processoListagemProfessoresPorTitulo(scanner);
		} else if (opcao == 5){
			processoListagemProfessoresPorAluno(scanner);
		} else if (opcao == 6){
			processoListagemProfessoresPorCurso(scanner);
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
