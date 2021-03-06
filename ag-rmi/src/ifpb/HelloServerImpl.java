package ifpb;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServerImpl extends UnicastRemoteObject implements Hello{
	private static final long serialVersionUID = 7766216779761919793L;

	protected HelloServerImpl() throws RemoteException {
		super();
	}

	@Override
	public void hello(String name) throws RemoteException {
		System.out.println("Hello, " + name);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		System.out.println("Servidor Iniciando");
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("helloService", new HelloServerImpl());
		System.out.println("Servidor Iniciado");
	}
	
}
