package ag.ifpb.eventbus;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Representa o Barramento de Eventos e seu
 * comportamento primário que é de
 * registrar um listener [.on()] e o de
 * notificar os listener para a ocorrência 
 * de um determinado evento [fire()]
 * 
 * @author arigarcia
 *
 */
public interface EventBus extends Remote {
	void on(String eventName, Listener listener) throws RemoteException;
	void fire(Event event) throws RemoteException;
}
