import java.rmi.RemoteException;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;

public class NotifierClient {

	public static void main(String[] args) throws RemoteException {
		//
		Event event = new Event("onTest", "Eita!!!");
		//
		EventBusClient client = new EventBusClient();
		client.fire(event);
	}
	
}
