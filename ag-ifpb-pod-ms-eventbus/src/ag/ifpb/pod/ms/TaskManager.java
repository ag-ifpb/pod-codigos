package ag.ifpb.pod.ms;

import java.util.Collections;
import java.util.List;

public class TaskManager implements Runnable{
	private final Register register;
	private final MessageManager messageManager;
	private final Notifier notifier;
	
	public TaskManager(Register register, MessageManager messageManager,
			Notifier notifier){
		this.register = register;
		this.messageManager = messageManager;
		this.notifier = notifier;
	}

	private List<String> listSubscribers(){
		List<String> subscribersIds = register.listAll();
		return Collections.unmodifiableList(subscribersIds);
	}
	
	private List<Message> listMessages(String subscriber){
		List<Message> messages = messageManager.find(subscriber);
		return Collections.unmodifiableList(messages);
	}
	
	private void notifyAndRemoveMessage(String subscriber, Message message){
		notifier.notify(subscriber, message);
		messageManager.unplublisher(message);
	}
	
	@Override
	public void run() {
		//log
		System.out.println("Iniciando a tarefa");
		System.out.println("Listar subscritores");
		// listar subscritores
		List<String> subscribers = listSubscribers();
		System.out.println("Quantidade de subscritores: " + subscribers.size());
		//-- para cada subscritor, 
		for (String subscriber : subscribers) {
			//log
			System.out.println("Verificar se existem mensagems para " + subscriber);
			//-- verificar se existem mensagens
			List<Message> messages = listMessages(subscriber);
			//log
			System.out.println("Existem " + messages.size() + " mensagens para " + subscriber);
			//-- caso exista, notifa o subscritor
			for (Message message : messages) {
				//log
				System.out.println("Enviando e descartando a mensagem " + message.getIdentify());
				//
				notifyAndRemoveMessage(subscriber, message);
			}
		}
		//
		System.out.println("Fim da tarefa");
		System.out.println("--------");
	}
	
}
