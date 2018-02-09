import java.util.ArrayList;

public class GerenciadorDeDisciplinas {
	private static ArrayList<Disciplina> lista = new ArrayList<>();
	
	public void inserir(Disciplina disciplina){
		lista.add(disciplina);
	}
	
	public void alterar(Disciplina disciplina){
		for (Disciplina d : lista) {
			if (d.getId() ==  disciplina.getId()){
				d.setNome(disciplina.getNome());
				d.setCargaHoraria(disciplina.getCargaHoraria());
				break;
			}
		}
	}
	
	public void excluir(Disciplina disciplina){
		for (int i = 0; i < lista.size(); i++) {
			Disciplina d = lista.get(i);
			if (d.getId() == disciplina.getId()){
				lista.remove(d);
			}
		}
	}
	
	public Disciplina pesquisarPorId(Integer id){
		for (int i = 0; i < lista.size(); i++) {
			Disciplina d = lista.get(i);
			if (d.getId() == id){
				return d;
			}
		}
		return null;
	}
	
	public ArrayList<Disciplina> pesquisarPorNome(String nome){
		ArrayList<Disciplina> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Disciplina d = lista.get(i);
			if (d.getNome().contains(nome)){
				resultado.add(d);
			}
		}
		return resultado;
	}
	
}
