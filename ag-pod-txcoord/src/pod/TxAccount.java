package pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TxAccount extends Remote {
  void prepare() throws RemoteException;//identificar a transacao
  void commit() throws RemoteException;
  void rollback() throws RemoteException;
}
