package ag.sharedrepository.node;

import ag.CommService;
import ag.Message;
import ag.RepositoryLocator;
import ag.impl.CommServiceImpl;

public class Main {
	private static int sequence = 0;
	
	private static Message process(Message msgReq){
		Message msgResp = new Message();
		msgResp.setIdentity(msgReq.getIdentity());
		msgResp.setSequence(sequence++);
		msgResp.setOriginalSequence(msgReq.getSequence());
		msgResp.setTo(msgReq.getFrom());
		msgResp.setFrom(Node2.NAME);
		msgResp.setMsg("Fala cara!!!!");
		return msgResp;
	}
	
	public static void main(String[] args) {
		System.out.println("Rodando node2");
		//configuração do repositório
		RepositoryLocator locator = new RepositoryLocator();
		locator.registryFunction(CommServiceImpl.FUNC_NAME, "/tmp");//raiz do projeto de node 1
		locator.registryMessageType(CommServiceImpl.MSG_REQ_TYPE, "req.txt");
		locator.registryMessageType(CommServiceImpl.MSG_RESP_TYPE, "resp.txt");
		//instanciando o serviço de middleware do repositório
		CommService service = new CommServiceImpl(locator);
		//instanciar o node2
		Node2 node = new Node2(service);
		//
		while(true){
			Message msgReq = node.recvRequest();
			//
			if (msgReq != null){
				System.out.println("Ident: " + msgReq.getIdentity());
				System.out.println("Seq: " + msgReq.getSequence());//controlado pelo node2
				System.out.println("OrigSeq: " + msgReq.getOriginalSequence());//<-- igual a minha seq
				System.out.println("To: " + msgReq.getTo());
				System.out.println("From: " + msgReq.getFrom());
				System.out.println("Msg: " + msgReq.getMsg());
				//preparar uma mensagem de resposta
				Message msgResp = process(msgReq);
				//enviar uma resposta
				node.sendResponse(msgResp);
			}else {
				System.out.println("Requisição veio nula");
			}
		}
		
	}
}
