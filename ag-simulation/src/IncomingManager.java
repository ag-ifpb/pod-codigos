import java.util.Random;

public class IncomingManager {
	private int count = 0;
	private int fail = 0;
	private final Queue queue;

	private People createPeople(){
		return new People(count++);
	}
	
	public IncomingManager(Queue queue) {
		this.queue = queue;
	}
	
	public int exec(){
		//randomizando o número de pessoas a serem criadas (entrada)
		Random r = new Random();
		int qt = r.nextInt(5) + 1;
		//criando e encaminhando para a fila
		for (int i = 0; i < qt; i++) {
			People p = createPeople();
			fail += queue.push(p) ? 0 : 1;
		}
		//
		return qt;
	}
	
	public int count(){
		return count;
	}
	
	public int fail(){
		return fail;
	}
	
}
