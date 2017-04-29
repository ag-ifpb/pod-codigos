package ifpb;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		System.out.println("Inicializado o serviço de Sender");
		//inicializar o repositorio
		MessageRepository repository = new MessageRepository();
		//inicializar o gerenciador de tarefas
		TaskManager.runTask(repository);
		//inicializar o serviço para client app
		Registry registry = LocateRegistry.createRegistry(10990);
		registry.bind("Sender", new SenderImpl(repository));
	}
}
