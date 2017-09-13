package ag.ifpb.eventbus.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ag.ifpb.eventbus.Event;
import ag.ifpb.eventbus.Listener;

@SuppressWarnings("serial")
public abstract class ListenerImpl extends UnicastRemoteObject implements Listener {

	public ListenerImpl() throws RemoteException {}

	//É abastrato porque a implementação final é quem
	//vai dizer o que este métodos vai fazer
	@Override
	public abstract void onEvent(Event event) throws RemoteException;

}
