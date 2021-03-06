package ag.messenger.app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ag.messenger.infra.SharedRepositoryImpl;
import ag.messenger.model.Message;
import ag.messenger.model.Messenger;
import ag.messenger.model.SharedRepository;

public class Main {

	public static void main(String[] args){
		//
		int rid = 0;
		int wid = 0;
		//
		SharedRepository repository = new SharedRepositoryImpl();
		Messenger messenger = new MessengerImpl(repository);
		//
		Scanner scanner = new Scanner(System.in);
		//fazer login
		System.out.println("Por favor, informe seu nome:");
		String name = scanner.nextLine();
		//
		System.out.println();
		//
		System.out.println("Chat");
		System.out.println("-----------------------------");
		//
		while(true){
			//
			String msg = scanner.nextLine();
			//
			List<Message> list = repository.select(rid);
			for (Message message : list) {
				//diferenciar por nome
				if (!message.getFrom().equals(name))
					System.out.println(String.format("%s: %s", 
						message.getFrom(), message.getText()));
				//
				rid = message.getId();
			}
			//
			wid = rid+1;
			//
			Message m = new Message();
			m.setId(wid);
			m.setFrom(name);
			m.setText(msg);
			m.setDate(new Date());
			//
			repository.store(m);
		}
		
	}
}
