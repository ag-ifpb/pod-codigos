package ag.ifpb.rmi.indocker;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//define type
		String type = args[0];
		if ("client".equals(type)){
			String host = args[1];
			HelloClientImpl.startClient(host);
		}
		else {
			HelloServerImpl.startServer();
		}		
	}
	
	
}
