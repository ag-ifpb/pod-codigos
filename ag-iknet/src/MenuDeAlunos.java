import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeAlunos {
	private static GerenciadorDeCursos gerenciadorDeCursos = new GerenciadorDeCursos();
	private static GerenciadorDeAlunos gerenciadorDeAlunos = new GerenciadorDeAlunos();

	private static void processoDePesquisarPorId(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um aluno por matrícula...");
		System.out.println("--------------------------");
		System.out.println("Digite a matrícula do aluno que deseja pesquisar (texto): ");
		String matricula = scanner.nextLine();
		//
		Aluno aluno = gerenciadorDeAlunos.pesquisarPorMatricula(matricula);
		if (aluno != null){
			System.out.println();
			System.out.println("Aluno");
			System.out.println("====================");
			System.out.println("Matrícula:\t " + aluno.getMatricula());
			System.out.println("Nome:\t " + aluno.getNome());
			System.out.println("Nascimento:\t " + aluno.getDataNascimento());
			System.out.println("Sexo:\t " + aluno.getSexo());
			System.out.println("Endereço:\t " + aluno.getEndereco());
			System.out.println("Contato:\t " + aluno.getContato());
			System.out.println("Nome dos Pais:\t " + aluno.getNomeDosPais());
			System.out.println("Nome do Curso:\t " + aluno.getCurso().getNome());
		} else {
			System.out.println("Professor não encontrada!");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorCurso(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um aluno por curso...");
		System.out.println("--------------------------");
		System.out.println("Digite o id do curso que deseja pesquisar (numérico): ");
		int cursoId = scanner.nextInt();
		scanner.nextLine();
		//
		Curso curso = gerenciadorDeCursos.pesquisarPorId(cursoId);
		if (curso != null){
			ArrayList<Aluno> alunos = gerenciadorDeAlunos.pesquisarPorCurso(curso);
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
		} else {
			System.out.println("Curso não encontrado");
		}
		System.out.println();
	}
	
	private static void processoDePesquisarPorNome(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Pesquisando um aluno por Nome...");
		System.out.println("--------------------------");
		System.out.println("Digite o nome do aluno que deseja pesquisar (texto): ");
		String nome = scanner.nextLine();
		//
		ArrayList<Aluno> alunos = gerenciadorDeAlunos.pesquisarPorNome(nome);
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

	private static void processoDeExcluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Excluindo um aluno...");
		System.out.println("--------------------------");
		System.out.println("Digite a matrícula do aluno que deseja excluir (texto): ");
		String matricula = scanner.nextLine();
		//
		Aluno aluno = gerenciadorDeAlunos.pesquisarPorMatricula(matricula);
		if (aluno != null){
			gerenciadorDeAlunos.excluir(aluno);
			System.out.println("Aluno excluido!");
		} else {
			System.out.println("Aluno não encontrado!");
		}
		//
		System.out.println();
	}

	private static void processoDeAlterar(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Alterando um aluno...");
		System.out.println("--------------------------");
		System.out.println("Digite a matrícula do aluno que deseja alterar (texto): ");
		String matricula = scanner.nextLine();
		System.out.println("Digite o nome do aluno (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite o endereço do aluno (texto): ");
		String endereco = scanner.nextLine();
		System.out.println("Digite o contato do aluno (texto): ");
		String contato = scanner.nextLine();
		System.out.println("Digite o sexo do aluno (texto): ");
		String sexo = scanner.nextLine();
		System.out.println("Digite o nome dos pais do aluno (texto): ");
		String nomeDosPais = scanner.nextLine();
		System.out.println("Digite a data de nascimento do aluno (dia/mes/ano)(texto): ");
		String dataNascimento = scanner.nextLine();
		//
		Curso curso = null;
		while(true){
			System.out.println("Digite o id do curso ou 999 para cancelar a alteração (numérico): ");
			int cursoId = scanner.nextInt();
			scanner.nextLine();
			//
			if (cursoId == 999){
				System.out.println("Processo de alteração cancelado.");
				System.out.println();
				return;
			}
			//
			curso = gerenciadorDeCursos.pesquisarPorId(cursoId);
			if (curso != null){
				break;
			} else {
				System.out.println("Curso não encontrado. Tente novamente.");
			}
		}
		//
		Aluno aluno = new Aluno();
		aluno.setContato(contato);
		aluno.setCurso(curso);
		aluno.setDataNascimento(dataNascimento);
		aluno.setEndereco(endereco);
		aluno.setMatricula(matricula);
		aluno.setNome(nome);
		aluno.setNomeDosPais(nomeDosPais);
		aluno.setSexo(sexo);
		//
		Aluno alunoAntigo = gerenciadorDeAlunos.pesquisarPorMatricula(matricula);
		if (alunoAntigo != null){
			gerenciadorDeAlunos.alterar(aluno);
			System.out.println("Aluno alterada!");
		} else {
			System.out.println("Aluno não encontrada!");
		}
		//
		System.out.println();
	}

	private static void processoDeIncluir(Scanner scanner) {
		//
		System.out.println("--------------------------");
		System.out.println("Inserindo um aluno...");
		System.out.println("--------------------------");
		System.out.println("Digite a matrícula do aluno que deseja alterar (texto): ");
		String matricula = scanner.nextLine();
		System.out.println("Digite o nome do aluno (texto): ");
		String nome = scanner.nextLine();
		System.out.println("Digite o endereço do aluno (texto): ");
		String endereco = scanner.nextLine();
		System.out.println("Digite o contato do aluno (texto): ");
		String contato = scanner.nextLine();
		System.out.println("Digite o sexo do aluno (texto): ");
		String sexo = scanner.nextLine();
		System.out.println("Digite o nome dos pais do aluno (texto): ");
		String nomeDosPais = scanner.nextLine();
		System.out.println("Digite a data de nascimento do aluno (dia/mes/ano)(texto): ");
		String dataNascimento = scanner.nextLine();
		//
		Curso curso = null;
		while(true){
			System.out.println("Digite o id do curso ou 999 para cancelar a inclusão (numérico): ");
			int cursoId = scanner.nextInt();
			scanner.nextLine();
			//
			if (cursoId == 999){
				System.out.println("Processo de inclusão cancelado.");
				System.out.println();
				return;
			}
			//
			curso = gerenciadorDeCursos.pesquisarPorId(cursoId);
			if (curso != null){
				break;
			} else {
				System.out.println("Curso não encontrado. Tente novamente.");
			}
		}
		//
		Aluno aluno = new Aluno();
		aluno.setContato(contato);
		aluno.setCurso(curso);
		aluno.setDataNascimento(dataNascimento);
		aluno.setEndereco(endereco);
		aluno.setMatricula(matricula);
		aluno.setNome(nome);
		aluno.setNomeDosPais(nomeDosPais);
		aluno.setSexo(sexo);
		//
		gerenciadorDeAlunos.inserir(aluno);
		System.out.println("Aluno inserido!");
		System.out.println();
	}
	
	public static void imprimirMenu(Scanner scanner){
		System.out.println("--------------------------");
		System.out.println("Opções para Alunos:");
		System.out.println("--------------------------");
		System.out.println("1 - Incluir");
		System.out.println("2 - Alterar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Pesquisar por matricula");
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
