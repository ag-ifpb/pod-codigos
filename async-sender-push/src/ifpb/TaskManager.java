package ifpb;

import java.util.Timer;

import ifpb.repositories.MessageRepository;
import ifpb.repositories.SendedMessageRepository;

public class TaskManager {
	
	public static void runTask(MessageRepository repository, SendedMessageRepository sendedRepository){
		Timer timer = new Timer();
		timer.schedule(new SendTask(repository, sendedRepository), 3000, 10000);
	}
	
}
