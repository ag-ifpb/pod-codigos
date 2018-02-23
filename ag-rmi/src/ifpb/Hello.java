package ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

//JRMP - Java Remote Method Protocol
//IIOP - Internet Inter Object Protocol (CORBA)
//RMI over ORB (Object Request Broker) (CORBA)
//IDL está para CORBA, assim como a impl de interfaces Remote estão para Java (RMI)
public interface Hello extends Remote{
	void hello(String name) throws RemoteException;
}
