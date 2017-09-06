package ag;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INode2 extends Remote{
	String hello(String name) throws RemoteException;
}
