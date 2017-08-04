package ag.ifpb.pod.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsável pelo armazenamento das mensagens
 * 
 * @author arigarcia
 *
 */
public class MessageRepository {
	private List<Message> messages = new ArrayList<Message>();
	
	public void add(Message message){
		messages.add(message);
	}
	
	public void remove(Message message){
		messages.remove(message);
	}
	
	public List<Message> list(){
		return Collections.unmodifiableList(messages);
	}
	
}
