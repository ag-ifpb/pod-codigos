import java.util.ArrayList;

public class GerenciadorDeAlunos {
	private static ArrayList<Aluno> lista = new ArrayList<>();
	
	public void inserir(Aluno aluno){
		lista.add(aluno);
	}
	
	public void alterar(Aluno aluno){
		for (Aluno a : lista) {
			if (a.getMatricula().equals(aluno.getMatricula())){
				a.setNome(aluno.getNome());
				a.setContato(aluno.getContato());
				a.setCurso(aluno.getCurso());
				a.setDataNascimento(aluno.getDataNascimento());
				a.setEndereco(aluno.getEndereco());
				a.setMatricula(aluno.getMatricula());
				a.setNomeDosPais(aluno.getNomeDosPais());
				a.setSexo(aluno.getSexo());
				break;
			}
		}
	}
	
	public void excluir(Aluno aluno){
		for (int i = 0; i < lista.size(); i++) {
			Aluno a = lista.get(i);
			if (a.getMatricula().equals(aluno.getMatricula())){
				lista.remove(a);
			}
		}
	}
	
	public Aluno pesquisarPorMatricula(String matricula){
		for (int i = 0; i < lista.size(); i++) {
			Aluno a = lista.get(i);
			if (a.getMatricula().equals(matricula)){
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Aluno> pesquisarPorNome(String nome){
		ArrayList<Aluno> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Aluno a = lista.get(i);
			if (a.getNome().contains(nome)){
				resultado.add(a);
			}
		}
		return resultado;
	}
	
	public ArrayList<Aluno> pesquisarPorSexo(String sexo){
		ArrayList<Aluno> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Aluno a = lista.get(i);
			if (a.getSexo().equals(sexo)){
				resultado.add(a);
			}
		}
		return resultado;
	}
	
	public ArrayList<Aluno> pesquisarPorCurso(Curso curso){
		ArrayList<Aluno> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Aluno a = lista.get(i);
			if (a.getCurso().getId() == curso.getId()){
				resultado.add(a);
			}
		}
		return resultado;
	}

	
}
