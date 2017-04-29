package pod;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Servidor extends UnicastRemoteObject implements InterfaceServidor {
	private int idCliente = 0;
	private InterfaceProc[] clientesConectados = new InterfaceProc[10];

	protected Servidor() throws RemoteException {
		super();
	}

	@Override
	public int estabelecerConexao() throws RemoteException {
		idCliente++;
		try {
			clientesConectados[idCliente] = (InterfaceProc) Naming.lookup("cliente");
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
		return idCliente;
	}

	@Override
	public String liberarConexao(int idCliente) throws RemoteException {
		clientesConectados[idCliente] = null;
		return "Conexão liberada para id do cliente " + idCliente;
	}

	@Override
	public void enviarParaTodos(String mmsg, int idCliente, String name) throws RemoteException {
		for (int i = 0; i < 10; i++){
			InterfaceProc cliente = clientesConectados[i];
			if (cliente != null && i != idCliente){
				cliente.atribuirMensagem("> " + name + " disse: " + mmsg);
			}
		}
	}
	
	public static void main(String[] args) throws RemoteException{
		//
		//System.setSecurityManager(new RMISecurityManager());
		//
		Servidor servidor = new Servidor();
		//
		try {
			Naming.rebind("rmi://290.355.675.999/servidor", servidor);
		} 
		catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	

}
