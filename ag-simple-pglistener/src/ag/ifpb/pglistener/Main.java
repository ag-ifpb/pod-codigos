package ag.ifpb.pglistener;

import java.rmi.RemoteException;
import java.sql.SQLException;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.client.EventBusClient;
import ag.ifpb.eventbus.client.ListenerImpl;

public class Main {

	public static void main(String[] args) throws RemoteException {
		//
		final Persister persister = new Persister();
		//
		EventBusClient client = new EventBusClient();
		client.on("on-insert", new ListenerImpl() {
			@Override
			public void onEvent(Event event) throws RemoteException {
				try {
					Object[] data = (Object[]) event.getData();
					persister.persist((int) data[0], (String) data[1]);
				}
				catch (SQLException e){
					throw new RemoteException("Erro ao receber um evento", e);
				}
			}
		});
	}
}
