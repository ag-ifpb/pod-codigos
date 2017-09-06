package ag;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//sem rmi - nós contruindo skeleton e o stub
		//INodeSkeleton.start();
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("ag.service1", new Node2Impl());
	}

}
