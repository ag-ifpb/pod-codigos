package ag.ifpb.rmi.indocker;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	void hello(String value) throws RemoteException;
}
