package ag.ifpb.pod.ms;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		//instanciar os elementos principais
		Register register = new Register();
		Notifier notifier = new Notifier(register);
		MessageManager manager = new MessageManager();
		TaskManager taskManager = new TaskManager(register, manager, notifier);
		//programar o background
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		//excutar uma thread depois dos primeiros 2s e daí por diante a cada 5s.
		executor.scheduleAtFixedRate(taskManager, 2000, 5000, TimeUnit.MILLISECONDS);
	}
	
}
