import java.util.ArrayList;

public class GerenciadorDeProfessores {
	private static ArrayList<Professor> lista = new ArrayList<>();
	
	public void inserir(Professor professor){
		lista.add(professor);
	}
	
	public void alterar(Professor professor){
		for (Professor p : lista) {
			if (p.getRg().equals(professor.getRg())){
				p.setContato(professor.getContato());
				p.setDataNascimento(professor.getDataNascimento());
				p.setDisciplinas(professor.getDisciplinas());
				p.setEndereco(professor.getEndereco());
				p.setGraduacao(professor.getGraduacao());
				p.setNome(professor.getNome());
				p.setSexo(professor.getSexo());
				break;
			}
		}
	}
	
	public void excluir(Professor professor){
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			if (p.getRg().equals(professor.getRg())){
				lista.remove(p);
			}
		}
	}
	
	public Professor pesquisarPorRg(String rg){
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			if (p.getRg().equals(rg)){
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Professor> pesquisarPorNome(String nome){
		ArrayList<Professor> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			if (p.getNome().contains(nome)){
				resultado.add(p);
			}
		}
		return resultado;
	}
	
	public ArrayList<Professor> pesquisarPorTitulo(String graduacao){
		ArrayList<Professor> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			if (p.getGraduacao().equals(graduacao)){
				resultado.add(p);
			}
		}
		return resultado;
	}
	
	public ArrayList<Professor> pesquisarPorCurso(Curso curso){
		ArrayList<Professor> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			for (Disciplina disciplinaDoCurso : curso.getDisciplinas()){
				for (Disciplina disciplinaDoProf : p.getDisciplinas()){
					if (disciplinaDoCurso.getId() == disciplinaDoProf.getId()){
						resultado.add(p);
					}
				}
			}
		}
		return resultado;
	}
	
	public ArrayList<Professor> pesquisarPorAluno(Aluno aluno){
		ArrayList<Professor> resultado = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Professor p = lista.get(i);
			for (Disciplina disciplinaDoCurso : aluno.getCurso().getDisciplinas()){
				for (Disciplina disciplinaDoProf : p.getDisciplinas()){
					if (disciplinaDoCurso.getId() == disciplinaDoProf.getId()){
						resultado.add(p);
					}
				}
			}
		}
		return resultado;
	}
	
	
}
