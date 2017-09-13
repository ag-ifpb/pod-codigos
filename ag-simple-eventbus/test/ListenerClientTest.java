import java.rmi.RemoteException;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;
import ag.ifpb.eventbus.client.ListenerImpl;

public class ListenerClientTest {

	@SuppressWarnings("serial")
	public static void main(String[] args) throws RemoteException {
		EventBusClient client = new EventBusClient();
		client.on("onTest", new ListenerImpl() {
			@Override
			public void onEvent(Event event) throws RemoteException {
				System.out.println(String.format(
						"Recebendo um evento %s: %s", 
						event.getName(), event.getData()));
			}
		});
	}
}
