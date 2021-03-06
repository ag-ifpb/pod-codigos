package ag.ifpb.pod.ms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	final static String TOKEN = "---123456---";
	
	/**
	 * Extrai os dados da   mensagem
	 * - [0]: publisherId
	 * - [1]: subscriberId
	 * - [2]: text
	 * 
	 * @param text
	 * @return
	 */
	private static String[] extract(String text){
		if (text.startsWith(TOKEN) && text.endsWith(TOKEN)){
			return text.replaceAll(TOKEN, "").split("\\|");
		}
		throw new RuntimeException("Mensagem estruturada incorretamente.");
	}
	
	private static void createSubscriberServer(SubscriberManager register) throws IOException {
		ServerSocket subscriberServerSocket = new ServerSocket(10998);
		while(true) {
			//log
			System.out.println("Aguardando conexão do subscriber.");
			//connect
			Socket socket = subscriberServerSocket.accept();
			//
			System.out.println("Conexão estabelecida com o sbscriber.");
			System.out.println("Fazendo a leitura dos dados do subscriber.");
			//read id
			byte[] b = new byte[1024];
			socket.getInputStream().read(b);
			String msg = new String(b).trim();
			String subId = msg.replaceAll(TOKEN, "");
			//log
			System.out.println("Registrando um subscriber: " + subId);
			//register
			register.register(subId, socket);
		}
	}
	
	private static void createPublisherServer(MessageManager manager) throws IOException {
		ServerSocket publisherServerSocket = new ServerSocket(10999, 10);
		while(true){
			//log
			System.out.println("Aguardando conexão do publisher.");
			//aguarando a conexão com o publicador
			Socket clientSocket = publisherServerSocket.accept();
			//log
			System.out.println("Conexão estabelecida com o publisher.");
			System.out.println("Fazendo a leitura dos dados.");
			//fazer a leitura da requisição
			byte[] b = new byte[1024];
			clientSocket.getInputStream().read(b, 0, 1024);
			String textMessage = new String(b).trim();
			//log
			System.out.println("Mensagem recebida: " + textMessage);
			//recuperar os dados da mensagem
			String extractedTextMessage[] = extract(textMessage);
			String publisherId = extractedTextMessage[0];
			String subscriberId = extractedTextMessage[1];
			String text = extractedTextMessage[2];
			//log
			System.out.println("Persistindo mensagem: " + textMessage);
			//persistir a mensagem
			String msgId = UUID.randomUUID().toString();//<---------
			Message message = new Message(msgId, publisherId, subscriberId, text);
			manager.publish(message);
			//log
			System.out.println("Respondendo ao publisher que a mensagem foi publicada.");
			//informar ao publicador que a mensagem foi publicada
			clientSocket.getOutputStream().write("#OK#".getBytes());
			//log
			System.out.println("Encerrando conexão");
			//encerrando a conexão
			clientSocket.close();
		}
	}

	public static void main(String[] args) throws IOException {
		//instanciar os elementos principais
		SubscriberManager register = new SubscriberManager();
		PublishingManager notifier = new PublishingManager(register);
		MessageManager manager = new MessageManager();
		TaskManager taskManager = new TaskManager(register, manager, notifier);
		//programar o background
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		//excutar uma thread depois dos primeiros 2s e daí por diante a cada 5s.
		executor.scheduleAtFixedRate(taskManager, 2000, 5000, TimeUnit.MILLISECONDS);
		//publisher server
		Thread pubThread = new Thread(){
			public void run() {
				try {
					createPublisherServer(manager);
				} catch (IOException e) {
					e.printStackTrace();
				}//thread
			};
		};
		pubThread.start();
		//subscriber server
		Thread subThread = new Thread(){
			public void run() {
				try {
					createSubscriberServer(register);
				} catch (IOException e) {
					e.printStackTrace();
				}//thread
			};
		};
		subThread.start();
	}
	
}
