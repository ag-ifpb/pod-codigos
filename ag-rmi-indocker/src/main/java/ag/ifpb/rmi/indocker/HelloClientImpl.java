package ag.ifpb.rmi.indocker;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class HelloClientImpl implements Hello {
	private final String host;
	
	public HelloClientImpl(String host) {
		this.host = host;
	}

	public void hello(String value) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(host, 10999);
			Hello hello = (Hello) registry.lookup("Server");
			hello.hello(value);
		}
		catch(NotBoundException e){
			e.printStackTrace();
		}
	}
	
	public static void startClient(String host){
		//
		System.out.println("Cliente iniciado. Digite 'exit' para sair.");
		System.out.println("------------------------------------------");
		//String host = "rmi-server.docker";
		Hello hello = new HelloClientImpl(host);
		Scanner scanner = new Scanner(System.in);
		while(true){
			String line = scanner.nextLine();
			if ("exit".equals(line)){
				scanner.close();
				System.exit(0);
			}
			else {
				try {
					hello.hello(line);
					System.out.println("------------------------------------------");
				}
				catch(RemoteException e ){
					System.out.println("Erro de comunicação. Tente novamente.");
				}
			}
		}
	}

}
