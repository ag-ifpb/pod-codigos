package ifpb;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientMain {

	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		MyService service = (MyService) LocateRegistry.getRegistry(10999).lookup("rmi://qwieruoiwuroiwe");
		System.out.println(service.toString());
	}
}
