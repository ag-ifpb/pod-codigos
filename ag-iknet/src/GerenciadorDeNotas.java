import java.util.ArrayList;

public class GerenciadorDeNotas {
	private static ArrayList<Notas> lista = new ArrayList<>();
	
	public void inserir(Notas notas){
		lista.add(notas);
	}
	
	public ArrayList<Notas> listarAprovados(Disciplina disciplina){
		ArrayList<Notas> resultado = new ArrayList<>();
		for (Notas notas : lista) {
			if (notas.getDisciplina().getId() == disciplina.getId() && notas.aprovado()){
				resultado.add(notas);
			}
		}
		return resultado;
	}
	
	
	
}
