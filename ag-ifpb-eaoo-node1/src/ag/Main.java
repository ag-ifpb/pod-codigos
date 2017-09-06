package ag;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		//INode2 node2 = new INode2Stub();//nós construíndo o skeleton e o stub
		Registry registry =  LocateRegistry.getRegistry("localhost", 10999);
		INode2 node2 = (INode2) registry.lookup("ag.service1");
		System.out.println(node2.hello("Ari"));
	}
	
}
