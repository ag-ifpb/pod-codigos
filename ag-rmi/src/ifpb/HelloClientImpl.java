package ifpb;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;

public class HelloClientImpl {

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
		Registry registry = LocateRegistry.getRegistry(10999);
		Hello hello = (Hello) registry.lookup("helloService");
		Thread t0 = new Thread(){
			public void run() {
				try{
					hello.hello("Ari Garcia");
				} catch(RemoteException e){
					e.printStackTrace();
				}
			};
		};
		Thread t1 = new Thread(){
			public void run() {
				try {
					hello.hello("Ari Garcia");
				} catch(RemoteException e){
					e.printStackTrace();
				}
			};
		};
		//
		t0.start();
		t1.start();
	}
	
}
