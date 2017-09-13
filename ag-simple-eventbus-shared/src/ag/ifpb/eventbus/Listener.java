package ag.ifpb.eventbus;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Representa um listener no lado
 * do cliente que irá aguadar a notificação
 * do eventbus do lado do servidor
 * 
 * @author arigarcia
 *
 */
public interface Listener extends Remote {
	void onEvent(Event event) throws RemoteException;
}
