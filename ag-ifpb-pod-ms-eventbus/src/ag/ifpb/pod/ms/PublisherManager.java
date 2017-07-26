package ag.ifpb.pod.ms;

import java.io.IOException;
import java.net.Socket;

/**
 * Notificador do subscriber
 * 
 * @author arigarcia
 *
 */
public class PublisherManager {
	private final static String TOKEN = "---123456---";
	private final SubscriberManager register;
	
	public PublisherManager(SubscriberManager register){
		this.register = register;
	}

	/**
	 * Notifica um determinado subscriber com uma mensagem
	 * específica
	 * 
	 * @param subscriberId - identificador do subscriber
	 * @param message - mensagem a ser notificada
	 */
	public boolean notify(String subscriberId, Message message){
		//
		Socket socket = register.find(subscriberId);
		//
		if (subscriberId == null){
			return false;
		}
		//
		try {
			//
			StringBuilder sb = new StringBuilder();
			sb.append(TOKEN);//inicio da mensagem
			sb.append(message.getPublisherId());
			sb.append("|");
			sb.append(message.getText());
			sb.append(TOKEN);//fim da mensagem
			//
			socket.getOutputStream().write(sb.toString().getBytes());
			socket.getOutputStream().flush();
			//
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//
		return false;
	}
	
}