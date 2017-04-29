package ifpb;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Receiver extends UnicastRemoteObject implements IReceiver {

	protected Receiver() throws RemoteException {
		super();
	}

	@Override
	public void delivery(Message msg) throws RemoteException {
		//
		System.out.println("Recebendo uma mensagem e tentando encaminhar para o server.");
		//
		Registry registry =  LocateRegistry.getRegistry(10992);
		try {
			IServerApp serverApp = (IServerApp) registry.lookup("ServerApp");
			serverApp.print(msg);
		}
		catch(NotBoundException | AccessException e){
			throw new RuntimeException("Foi mal!!");
		}
		
	}

	
}