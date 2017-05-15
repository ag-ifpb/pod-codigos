package ag.ifpb.rmi.indocker;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class HelloServerImpl extends UnicastRemoteObject implements Hello {

	protected HelloServerImpl() throws RemoteException {
		super();
	}

	public void hello(String value) throws RemoteException {
		System.out.println("Imprimindo do lado do server: " + value);
		System.out.println("------------------------------------------");
	}
	
	public static void startServer() throws RemoteException, AlreadyBoundException{
		System.out.println("Servidor iniciado.");
		System.out.println("------------------------------------------");
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("Server", new HelloServerImpl());
	}

}
