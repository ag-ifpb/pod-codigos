import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ag.ifpb.eventbus.server.EventBusServer;

/**
 * Representa o trabalho do EventBus
 * 
 * @author arigarcia
 *
 */
public class EventBusServerTest {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//
		System.out.println("Iniciando o EventBus - server");
		//
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("EventBus", new EventBusServer());
	}
}
