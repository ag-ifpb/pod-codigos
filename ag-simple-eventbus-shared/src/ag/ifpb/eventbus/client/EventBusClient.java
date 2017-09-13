package ag.ifpb.eventbus.client;

import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.EventBus;
import ag.ifpb.eventbus.Listener;

public class EventBusClient implements EventBus {

	/**
	 * Do lado do cliente, este método delega o seu comportamento
	 * para uma referência remota do eventBus 
	 * 
	 */
	@Override
	public void on(String eventName, Listener listener) throws RemoteException {
		//recuperando uma instância do registro
		Registry registry = LocateRegistry.getRegistry(10999);
		try {
			//recuperar uma referência para o EventBusServer
			EventBus eventBus = (EventBus) registry.lookup("EventBus");
			eventBus.on(eventName, listener);//faz o trabalho real
		} catch (NotBoundException | AccessException e){
			throw new RemoteException("Falha ao "
					+ "tentar vincular o listener ao evento", e);
		}
	}

	/**
	 * Do lado do cliente, este método delega o seu comportamento
	 * para uma referência remota do eventBus
	 */
	@Override
	public void fire(Event event) throws RemoteException {
		//recuperando uma instância do registro
		Registry registry = LocateRegistry.getRegistry(10999);
		try {
			//recuperar uma referência para o EventBusServer
			EventBus eventBus = (EventBus) registry.lookup("EventBus");
			eventBus.fire(event);
		} catch(NotBoundException | AccessException e){
			throw new RemoteException("Falha ao disparar um evento", e);
		}
	}

}
