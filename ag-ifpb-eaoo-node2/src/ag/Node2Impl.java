package ag;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Node2Impl extends UnicastRemoteObject implements INode2 {

	protected Node2Impl() throws RemoteException {
		super();
	}

	@Override
	public String hello(String name) throws RemoteException{
		return "Hello World, " + name + ".";
	}

}
