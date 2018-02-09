package br.edu.ifpb.atm.sharing.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.edu.ifpb.atm.sharing.data.Billet;


public interface ATMManager extends Remote {	
	String withdraw(Billet billet) throws RemoteException;
	String balance() throws RemoteException;
}
