package ifpb;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServiceImpl extends UnicastRemoteObject implements MyService {

	protected MyServiceImpl() throws RemoteException {
		super();
	}

}
