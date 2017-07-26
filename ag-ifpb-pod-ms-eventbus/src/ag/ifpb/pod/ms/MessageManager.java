package ag.ifpb.pod.ms;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerenciador de Mensagem
 * 
 * @author arigarcia
 *
 */
public class MessageManager {
	private final MessageRepository repository;
	
	public MessageManager() {
		repository = new MessageRepository();
	}
	
	/**
	 * Persiste a mensagem publicada
	 * 
	 * @param message - mensagem a ser persistida
	 */
	public void publish(Message message){
		repository.add(message);
	}
	
	/**
	 * Remove a mensagem publicada
	 * 
	 * @param message - mensagem a ser removida
	 */
	public void unplublish(Message message){
		repository.remove(message);
	}
	
	/**
	 * Localizar mensagens para um determinado subscriber
	 * 
	 * @param subscriberId - identificador do subscriber
	 * @return
	 */
	public List<Message> find(String subscriberId){
		List<Message> result = new ArrayList<Message>();
		for (Message message : repository.list()) {
			if (message.getSubscriberId().equals(subscriberId)){
				result.add(message);
			}
		}
		return result;
	}
	
}
