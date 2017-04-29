package pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServidor extends Remote{
	int estabelecerConexao() throws RemoteException;
	String liberarConexao(int idCliente) throws RemoteException;
	void enviarParaTodos(String mmsg, int idClient, String name) throws RemoteException;
	
}
