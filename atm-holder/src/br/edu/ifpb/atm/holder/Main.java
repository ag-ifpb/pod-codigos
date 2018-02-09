package br.edu.ifpb.atm.holder;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.edu.ifpb.atm.sharing.data.Billet;
import br.edu.ifpb.atm.sharing.service.ATMManager;

public class Main {
	
	private static void holder(String name, ATMManager manager) throws AccessException, RemoteException, NotBoundException{
		//
		System.out.println("------------------------------");
		System.out.println("balance>> " + manager.balance());
		System.out.println("------------------------------");
		String result = null;
		//
		result = manager.withdraw(Billet.$10dollars);
		System.out.println((name + ">> withdraw10: ") + ("success".equals(result) ? manager.balance() : result));
		//
		result = manager.withdraw(Billet.$20dollars);
		System.out.println((name + ">> withdraw20: ") + ("success".equals(result) ? manager.balance() : result));
		//
		result = manager.withdraw(Billet.$50dollars);
		System.out.println((name + ">> withdraw50: ") + ("success".equals(result) ? manager.balance() : result));
	}
	
	private static void holder1() throws AccessException, RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(9999);
		ATMManager manager = (ATMManager) registry.lookup("ATMService");
		//
		holder("holder1", manager);
	}
	
	private static void holder2() throws AccessException, RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(9999);
		ATMManager manager = (ATMManager) registry.lookup("ATMService");
		//
		holder("holder2", manager);
	}
	
	private static void holder3() throws AccessException, RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(9999);
		ATMManager manager = (ATMManager) registry.lookup("ATMService");
		//
		holder("holder3", manager);
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		//
		ThreadGroup hg = new ThreadGroup("Holders");
		//
		Thread h1 = new Thread(hg, "h1"){
			public void run() {
				try {
					for (int i = 0; i < 100; i++)
					holder1();
				} 
				catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			};
		};
		Thread h2 = new Thread(hg, "h2"){
			public void run() {
				try {
					for (int i = 0; i < 100; i++)
					holder2();
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			};
		};
		Thread h3 = new Thread(hg, "h3"){
			public void run() {
				try {
					for (int i = 0; i < 100; i++)
					holder3();
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			};
		};
		
		
		h1.start();
		h2.start();
		h3.start();
		System.out.println("---------" + hg.activeCount());
	}
	
}
