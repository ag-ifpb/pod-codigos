package aaaa;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.spi.NamingManager;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("rmi://qwieruoiwuroiwe", new MyServiceImpl());
		
		
	}
}
