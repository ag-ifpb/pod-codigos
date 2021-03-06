

public class Attendante {
	private int count = 0;
	private final Queue queue;
	private People current = null;
	
	public Attendante(Queue queue) {
		this.queue = queue;
	}
	
	public void startService(){
		current = this.queue.pop();
		System.out.println("Iniciando o atendimento ao cliente " + current.getName());
	}
	
	public void stopService(){
		System.out.println("Encerrando o atendimento ao cliente " + current.getName());
		current = null;
		count++;
	}
	
	public int count(){
		return count;
	}
}
