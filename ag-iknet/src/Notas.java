
public class Notas {
	private Disciplina disciplina;
	private Aluno aluno;
	private double nota1;
	private double nota2;
	
	public Notas(Disciplina disciplina, Aluno aluno){
		this.disciplina = disciplina;
		this.aluno = aluno;
	}
	
	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}
	
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}
	
	public double getNota1() {
		return nota1;
	}
	
	public double getNota2() {
		return nota2;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public double media(){
		return (nota1 + nota2)/2;
	}
	
	public boolean aprovado(){
		if (media() > 7){
			return true;
		} else {
			return false;
		}
	}
	
}
