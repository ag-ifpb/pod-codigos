package br.edu.ifpb.atm;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.edu.ifpb.atm.manager.ATM;
import br.edu.ifpb.atm.manager.ATMManagerImpl;
import br.edu.ifpb.atm.sharing.service.ATMManager;

public class Main {
	
	public static void main(String[] args) {
		//instancia o caixa eletronico
		ATM atm = new ATM();
		try {
			//instanciar o gerencimento do caixa eletronico
			ATMManager manager = new ATMManagerImpl(atm);
			//registrar o serviço
			Registry registry = LocateRegistry.createRegistry(9999);
			registry.bind("ATMService", manager);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		} 
		catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
