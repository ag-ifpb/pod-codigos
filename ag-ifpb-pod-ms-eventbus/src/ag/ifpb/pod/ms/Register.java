package ag.ifpb.pod.ms;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Registrador de Subscritores (interessados).
 * 
 * @author arigarcia
 *
 */
public class Register {
	private Map<String, Socket> repository = new HashMap<String, Socket>();
	
	/**
	 * Registro de um subscriber em memória
	 * 
	 * @param subscriberId - identificador do subscriber
	 * @param subscriberSocket - socket da conexão com subscriber
	 */
	public void register(String subscriberId, Socket subscriberSocket){
		repository.put(subscriberId, subscriberSocket);
	}
	
	/**
	 * Remove o subscriber da memória
	 * 
	 * @param subscriberId - identificador do subscriber
	 */
	public void unregister(String subscriberId){
		repository.remove(subscriberId);
	}

	/**
	 * Recupera a conexão com o subscriber
	 * 
	 * @param subscriberId - identificador do subscriber
	 * @return
	 */
	public Socket find(String subscriberId) {
		return repository.get(subscriberId);
	}

	/**
	 * Lista todos os identificadores dos subscribers
	 * 
	 * @return
	 */
	public List<String> listAll() {
		List<String> result = new ArrayList<String>();
		Set<String> keys = repository.keySet();
		for (String k : keys) {
			result.add(k);
		}
		return result;
	}
	
}
