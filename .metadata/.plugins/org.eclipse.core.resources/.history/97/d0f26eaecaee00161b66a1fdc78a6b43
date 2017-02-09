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
			socket.getOutputStream().write(TOKEN.getBytes());//início da msg
			socket.getOutputStream().write(message.getText().getBytes());
			socket.getOutputStream().write(TOKEN.getBytes());//fim da msg
			//
			socket.getOutputStream().flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
