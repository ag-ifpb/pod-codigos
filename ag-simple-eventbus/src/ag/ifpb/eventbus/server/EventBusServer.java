package ag.ifpb.eventbus.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.EventBus;
import ag.ifpb.eventbus.Listener;

@SuppressWarnings("serial")
public class EventBusServer extends UnicastRemoteObject implements EventBus {
	private final Map<String, List<Listener>> listeners = new HashMap<>();

	public EventBusServer() throws RemoteException{}//obrigatório para RMI
	
	@Override
	public void on(String eventName, Listener listener) throws RemoteException {
		//
		List<Listener> ls = listeners.get(eventName);
		if (ls == null){//na primeira vez não haver listeners
			ls = new ArrayList<>();
			listeners.put(eventName, ls);
		}
		//
		ls.add(listener);
	}

	@Override
	public void fire(Event event) throws RemoteException {
		//
		List<Listener> list = listeners.get(event.getName());
		//
		if (list == null) {
			throw new RemoteException("Nenhum listener para este evento");
		}
		//
		for (Listener listener : list) {
			listener.onEvent(event);
		}
	}

}
