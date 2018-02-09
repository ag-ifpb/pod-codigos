import java.util.ArrayList;

public class GerenciadorDeCursos {
	private static ArrayList<Curso> lista = new ArrayList<>();
	
	public void inserir(Curso curso){
		lista.add(curso);
	}
	
	public void alterar(Curso curso){
		for (Curso c : lista) {
			if (c.getId() == curso.getId()){
				c.setNome(curso.getNome());
				c.setDisciplinas(curso.getDisciplinas());
				break;
			}
		}
	}
	
	public void excluir(Curso curso){
		for (int i = 0; i < lista.size(); i++) {
			Curso c = lista.get(i);
			if (c.getId() == curso.getId()){
				lista.remove(c);
			}
		}
	}
	
	public Curso pesquisarPorId(Integer id){
		for (int i = 0; i < lista.size(); i++) {
			Curso c = lista.get(i);
			if (c.getId() == id){
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Curso> pesquisarPorNome(String nome){
		ArrayList<Curso> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Curso c = lista.get(i);
			if (c.getNome().contains(nome)){
				resultado.add(c);
			}
		}
		return resultado;
	}
	
	
}
