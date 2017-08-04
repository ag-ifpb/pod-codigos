package ag.sharedrepository.node;

import java.io.FileNotFoundException;
import java.io.IOException;

import ag.CommService;
import ag.Message;
import ag.RepositoryLocator;
import ag.impl.CommServiceImpl;

public class Main {
	private static int sequence = 0;
	
	private static Message process(){
		Message msgReq = new Message();
		msgReq.setIdentity(Node1.NAME + sequence);
		msgReq.setSequence(sequence++);
		msgReq.setFrom(Node1.NAME);
		msgReq.setTo("node2");
		msgReq.setMsg("Tudo bem com vc cara???");
		return msgReq;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Rodando node1");
		//configuração do repositório
		RepositoryLocator locator = new RepositoryLocator();
		locator.registryFunction(CommServiceImpl.FUNC_NAME, "/tmp");//raiz do projeto de node 1
		locator.registryMessageType(CommServiceImpl.MSG_REQ_TYPE, "req.txt");
		locator.registryMessageType(CommServiceImpl.MSG_RESP_TYPE, "resp.txt");
		//instanciando o serviço de middleware do repositório
		CommService service = new CommServiceImpl(locator);
		//criando uma mensage para node 2
		Message msgReq = process();
		//instanciando o node1
		Node1 node = new Node1(service);
		//enviando e aguardando o recebimento da resposta
		Message msgResp = node.sendRequest(msgReq);
		//checagem da resposta
		if (msgResp != null){
			System.out.println("Ident: " + msgResp.getIdentity());
			System.out.println("Seq: " + msgResp.getSequence());//controlado pelo node2
			System.out.println("OrigSeq: " + msgResp.getOriginalSequence());//<-- igual a minha seq
			System.out.println("To: " + msgResp.getTo());
			System.out.println("From: " + msgResp.getFrom());
			System.out.println("Msg: " + msgResp.getMsg());
		}else {
			System.out.println("Resposta veio nula");
		}
	}
}
