package ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerApp extends Remote{
	void print(Message msg) throws RemoteException;
}
