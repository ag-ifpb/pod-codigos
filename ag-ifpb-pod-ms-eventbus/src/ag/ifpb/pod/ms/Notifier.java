package ag.ifpb.pod.ms;

import java.io.IOException;
import java.net.Socket;

/**
 * Notificador do subscriber
 * 
 * @author arigarcia
 *
 */
public class Notifier {
	private final static String TOKEN = "---123456---";
	private final Register register;
	
	public Notifier(Register register){
		this.register = register;
	}

	/**
	 * Notifica um determinado subscriber com uma mensagem
	 * específica
	 * 
	 * @param subscriberId - identificador do subscriber
	 * @param message - mensagem a ser notificada
	 */
	public void notify(String subscriberId, Message message){
		//
		Socket socket = register.find(subscriberId);
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
			//
			socket.getOutputStream().flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
