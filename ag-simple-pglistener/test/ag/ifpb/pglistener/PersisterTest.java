package ag.ifpb.pglistener;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.Test;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;

public class PersisterTest {

	@Test
	public void test() throws SQLException {
		Persister persister = new Persister();
		persister.persist(0, "Ari");
	}
	
	@Test
	public void testRemote() throws RemoteException {
		//
		EventBusClient client = new EventBusClient();
		for (int i = 0; i < 100; i++) {
			Object[] data = new Object[]{i+1, "Ana Maria #" + i};
			Event event = new Event("on-insert", data);
			client.fire(event);
			System.out.println("Inserindo elemento " + i);
		}
	}

}
