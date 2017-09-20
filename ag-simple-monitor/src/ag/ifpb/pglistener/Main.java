package ag.ifpb.pglistener;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.runner.notification.RunListener.ThreadSafe;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;
import ag.ifpb.eventbus.client.ListenerImpl;

public class Main {
	//
	static int inconsistenceCount = 0;
	static double inconsistenceTotal = 0;
	static ConcurrentLinkedQueue<Double> pglistenerTimer = new ConcurrentLinkedQueue<>();
	static ConcurrentLinkedQueue<Double> mylistenerTimer = new ConcurrentLinkedQueue<>();

	public static void main(String[] args) throws RemoteException {
		//
		System.out.println("Iniciando o Monitor");
		//
		EventBusClient client = new EventBusClient();
		//
		client.on("on-pg-timer", new ListenerImpl() {
			@Override
			public void onEvent(Event event) throws RemoteException {
				Double t = (Double) event.getData();
				pglistenerTimer.add(t);
			}
		});
		//
		client.on("on-my-timer", new ListenerImpl() {
			@Override
			public void onEvent(Event event) throws RemoteException {
				Double t = (Double) event.getData();
				mylistenerTimer.add(t);
			}
		});
		//
		Runnable task = new Runnable() {
			@Override
			public void run() {
				while(true){
					//recupera sem remover
					Double pgt = pglistenerTimer.peek();
					Double myt = mylistenerTimer.peek();
					//
					if (pgt != null && myt != null){
						inconsistenceCount++;
						inconsistenceTotal += (pgt - myt);
						pglistenerTimer.poll();
						mylistenerTimer.poll();
						System.out.println("Inconsistência média: " + (inconsistenceTotal/inconsistenceCount));
					} else {
						break;
					}
				}
			}
		};
		//
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(task, 100, 100, TimeUnit.MILLISECONDS);
	}
}
