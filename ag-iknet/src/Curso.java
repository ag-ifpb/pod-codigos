

import java.util.ArrayList;

public class Curso {
    //Id
    private Integer id;
    //Nome
    private String nome;
    //Disciplinas
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}    
    
}