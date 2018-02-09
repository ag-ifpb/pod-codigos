package br.edu.ifpb.atm.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ifpb.atm.sharing.data.Billet;
import br.edu.ifpb.atm.sharing.service.ATMManager;


@SuppressWarnings("serial")
public class ATMManagerImpl extends UnicastRemoteObject implements ATMManager {
	private final ATM atm;

	public ATMManagerImpl(ATM a) throws RemoteException {
		atm = a;
	}

	@Override
	public String withdraw(Billet billet) throws RemoteException {
		boolean r = false;
		switch (billet) {
			case $10dollars:
				r = atm.billet10dollarsWithdraw(); break;
			case $20dollars:
				r = atm.billet20dollarsWithdraw(); break;
			case $50dollars:
				r = atm.billet50dollarsWithdraw(); break;
		}
		return r ? "success" : "funds insufficient";
	}

	@Override
	public String balance() throws RemoteException {
		StringBuffer sb = new StringBuffer();
		sb.append("10dollars (").append(atm.getBillet10dollars()).append(")");
		sb.append(" + ");
		sb.append("20dollars (").append(atm.getBillet20dollars()).append(")");
		sb.append(" + ");
		sb.append("50dollars (").append(atm.getBillet50dollars()).append(")");
		sb.append(" = ");
		sb.append(atm.balance());
		return sb.toString();
	}

}
