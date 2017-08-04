package ag.sharedrepository.node;

import java.io.FileNotFoundException;
import java.io.IOException;

import ag.CommService;
import ag.Message;

public class Node2 {
	public final static String NAME = "node2";
	private final CommService commService;
	
	public Node2(CommService service) {
		this.commService = service;
	}
	
	public Message recvRequest(){
		try {
			Message msgReq = null;
			while(msgReq == null){
				msgReq = commService.recv(NAME);
			}
			return msgReq;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendResponse(Message message){
		try {
			commService.send(message);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
