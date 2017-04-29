package pod;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

import javax.management.remote.rmi.RMIConnectionImpl;
import javax.management.remote.rmi.RMIConnector;
import javax.management.remote.rmi.RMIServerImpl;

public class ServidorDeNome {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("servidor", new Servidor());
	}
}
