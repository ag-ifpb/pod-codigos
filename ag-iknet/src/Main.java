import java.util.Scanner;


public class Main {
	
	private static void imprimirMenu(Scanner scanner){
		//
		System.out.println("Digite um número referente a uma das opções:");
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Disciplina");
		System.out.println("4 - Curso");
		System.out.println("5 - Outros");
		System.out.println("9 - Sair");
		//
		int opcao = scanner.nextInt();
		scanner.nextLine();
		//
		boolean sair = false;
		if (opcao == 1){
			MenuDeAlunos.imprimirMenu(scanner);
		} else if (opcao == 2){
			MenuDeProfessores.imprimirMenu(scanner);
		} else if (opcao == 3){
			MenuDeDisciplinas.imprimirMenu(scanner);
		} else if (opcao == 4){
			MenuDeCursos.imprimirMenu(scanner);
		} else if (opcao == 5){
			MenuDeOutros.imprimirMenu(scanner);
		} else if (opcao == 9){
			sair = true;
		} else {
			System.out.println("Opção inválida. Tente novamente.");
		}
		//
		if (sair){
			System.out.println("Saindo...");
		} else {
			imprimirMenu(scanner);
		}
	}

    public static void main(String args[]){
    		Scanner scanner = new Scanner(System.in);
    		imprimirMenu(scanner);
    		scanner.close();
    }

}