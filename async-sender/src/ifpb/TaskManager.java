package ifpb;

import java.util.Timer;

public class TaskManager {
	
	public static void runTask(MessageRepository repository){
		Timer timer = new Timer();
		timer.schedule(new SendTask(repository), 3000, 10000);
	}
	
}
