package ifpb;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Receiver extends UnicastRemoteObject implements IReceiver, Subject {
	private final ResponseMessageRepository repository;
	
	private Observer observer = null; 

	protected Receiver(ResponseMessageRepository repository) throws RemoteException {
		this.repository = repository;
	}

	@Override
	public void delivery(Message msg) throws RemoteException {
		//
		System.out.println("Recebendo uma mensagem e tentando encaminhar para o server.");
		//
		Registry registry =  LocateRegistry.getRegistry(10992);
		try {
			IServerApp serverApp = (IServerApp) registry.lookup("ServerApp");
			MessageResult result = serverApp.print(msg);
			repository.add(result);
			//
			observer.notify(result);
		}
		catch(NotBoundException | AccessException e){
			throw new RuntimeException("Foi mal!!");
		}
		
	}

	@Override
	public MessageResult result(String id) throws RemoteException {
		return repository.get(id);
	}
	
	@Override
	public void registry(Observer obj) throws RemoteException {
		this.observer = obj;
	}

	
}
