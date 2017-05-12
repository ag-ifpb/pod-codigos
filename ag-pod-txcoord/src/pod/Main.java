package pod;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
  private static final int PORT = 10001;
  private static final String HOST_A = "192.168.1.101";
  private static final String HOST_B = "192.168.1.106";
  private static final String HOST_C = "192.168.1.102";
  private static final String HOST_D = "192.168.1.104";
  private static final String HOST_E = "192.168.1.105";
  
  public static TxAccount getTxAccountA() throws AccessException, RemoteException, 
      NotBoundException{
    Registry registry = LocateRegistry.getRegistry(HOST_A, PORT);
    return (TxAccount) registry.lookup("TxServ");
  }
  
  public static TxAccount getTxAccountB() throws AccessException, RemoteException, 
      NotBoundException{
    Registry registry = LocateRegistry.getRegistry(HOST_B, PORT);
    return (TxAccount) registry.lookup("TxServ");
  }
  
  public static TxAccount getTxAccountC() throws AccessException, RemoteException, 
      NotBoundException{
    Registry registry = LocateRegistry.getRegistry(HOST_C, PORT);
    return (TxAccount) registry.lookup("TxServ");
  }
  
  public static TxAccount getTxAccountD() throws AccessException, RemoteException, 
      NotBoundException{
    Registry registry = LocateRegistry.getRegistry(HOST_D, PORT);
    return (TxAccount) registry.lookup("TxServ");
  }
  
  public static TxAccount getTxAccountE() throws AccessException, RemoteException, 
      NotBoundException{
    Registry registry = LocateRegistry.getRegistry(HOST_E, PORT);
    return (TxAccount) registry.lookup("TxServ");
  }
  
  public static void startTxService(TxCoord txCoord) throws RemoteException, 
      AlreadyBoundException{
    Registry registry = LocateRegistry.createRegistry(10003);
    registry.bind("TxCoord", txCoord);
  }

  public static void main(String[] args) throws AccessException, 
      RemoteException, NotBoundException, AlreadyBoundException {
    //localizar a transação de A
    TxAccount txa = getTxAccountA();
    //localizar a transação de B
    TxAccount txb = getTxAccountB();
    //localizar a transação de C
    TxAccount txc = null;//getTxAccountC();
    //localizar a transação de D
    TxAccount txd = getTxAccountD();
    //localizar a transação de E
    TxAccount txe = getTxAccountE();
    //
    TxCoord txCoord = new  TxCoordImpl(txa, txb, txc, txd, txe);
    //
    System.setProperty("java.rmi.server.hostname", "192.168.1.103");
    startTxService(txCoord);
    //
    System.out.println("Transação Liberada.");
  }
  
}
