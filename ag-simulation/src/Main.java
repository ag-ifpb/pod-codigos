
public class Main {

	public static void main(String[] args) {
		//
		Queue queue = new Queue();
		Attendante attendante = new Attendante(queue);
		IncomingManager manager = new IncomingManager(queue);
		//
		Engine engine = new Engine(manager, attendante, queue);
		engine.exec();
	}
}
