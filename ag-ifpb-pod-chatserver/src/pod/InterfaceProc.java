package pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceProc extends Remote{
	void atribuirMensagem(String msg) throws RemoteException;
}
