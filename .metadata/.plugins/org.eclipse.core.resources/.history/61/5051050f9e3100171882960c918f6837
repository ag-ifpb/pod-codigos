package ifpb;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		//log
		System.out.println("Acionado o clientapp");
		//recuperação do Sender
		Registry registry = LocateRegistry.getRegistry(10990);
		ISender sender = (ISender) registry.lookup("Sender");
		//
		String id = "askjdlkasjd";
		String text = "Hello World!";
		//
		sender.sendMessage(new Message(id, text));
		//recuperar uma resposta
		//??????
	}
	
}
