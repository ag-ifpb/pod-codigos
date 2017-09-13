package ag.ifpb.eventbus.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//
		System.out.println("Iniciando o EventBus - server");
		//
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("EventBus", new EventBusServer());
	}
}
