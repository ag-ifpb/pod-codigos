package ag.sharedrepository.node;

import java.io.FileNotFoundException;
import java.io.IOException;

import ag.CommService;
import ag.Message;

public class Node1 {
	public final static String NAME = "node1";
	private final CommService commService;
	
	public Node1(CommService service) {
		this.commService = service;
	}

	public Message sendRequest(Message message){
		try {
			commService.send(message);
			Message resp = null;
			while(resp == null){
				resp = commService.recv(NAME);
			}
			return resp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//caso ocorra erro
		return null;
	}
	
}
