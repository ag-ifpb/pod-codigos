package pod;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Cliente extends UnicastRemoteObject implements InterfaceProc {

	public Cliente() throws RemoteException{
		super();
	}
	
	@Override
	public void atribuirMensagem(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	public static void main(String[] args) throws RemoteException {
		Cliente cliente = new Cliente();
		try {
			Naming.rebind("cliente", cliente);
			
			InterfaceServidor servidor = (InterfaceServidor) Naming.lookup("rmi://127.0.0.1:10999/servidor");
			System.out.println(servidor);
			//
			int idCliente = servidor.estabelecerConexao();
			System.out.println("Id do cliente: " + idCliente);
			//
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Entre com um nome e depois a sua mensagem: ");
			String _nome = reader.readLine();
			String _mess = reader.readLine();
			while(_mess.compareTo("end") != 0){
				servidor.enviarParaTodos(_mess, idCliente, _nome);
				_mess = reader.readLine();
			}
			//
			System.out.println(servidor.liberarConexao(idCliente));
			System.out.println("saiu");
			System.exit(0);
			
		} 
		catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}

}
