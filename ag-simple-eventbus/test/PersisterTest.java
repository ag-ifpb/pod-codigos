

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.Test;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;

/**
 * Representação do cliente no diagrama
 * 
 * @author arigarcia
 *
 */
public class PersisterTest {
	
	@Test
	public void testRemote() throws RemoteException, InterruptedException {
		//
		Thread.sleep(10000);
		//
		EventBusClient client = new EventBusClient();
		for (int i = 0; i < 2000; i++) {
			Object[] data = new Object[]{i+1, "Ana Maria #" + i};
			Event event = new Event("on-insert", data);
			client.fire(event);
			System.out.println("Inserindo elemento " + i);
		}
	}

}
