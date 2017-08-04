package ag.ifpb.pod.ms;

import java.io.IOException;
import java.net.Socket;

/**
 * Gerenciado de publicações.
 * Responsável por encaminhar as mensagens para quem
 * tiver inscrito para recebe-las.
 * 
 * @author arigarcia
 *
 */
public class PublishingManager {
	private final static String TOKEN = "---123456---";
	private final SubscriberManager register;
	
	private boolean isValid(Socket socket){
		return true;
	}
	
	public PublishingManager(SubscriberManager register){
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
		//recupera a conexão do inscrito
		Socket socket = register.find(subscriberId);
		//verificar se a conexão é valida
		if (!isValid(socket)){
			return false;
		}
		//verificar se a identificação de inscrição é valida
		if (subscriberId == null || subscriberId.isEmpty()){
			return false;
		}
		//
		try {
			//constroe a mensagem a ser entregue
			StringBuilder sb = new StringBuilder();
			sb.append(TOKEN);//inicio da mensagem
			sb.append(message.getPublisherId());
			sb.append("|");
			sb.append(message.getText());
			sb.append(TOKEN);//fim da mensagem
			//faz entrega escrevendo no stream
			socket.getOutputStream().write(sb.toString().getBytes());
			socket.getOutputStream().flush();
			//entrega realizada com sucesso
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//não foi entregue
		return false;
	}
	
}
