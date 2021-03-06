package ag;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;



public class MessageManagerTest {
	
	@Test
	public void regex(){
		String msg = "id|name|pop";
		String[] fields = msg.split("\\|");
		Assert.assertTrue(fields.length == 3);
	}

	@Test
	public void writeAndReadMessage() throws FileNotFoundException, IOException {
		//registrar as configurações
		RepositoryLocator locator = new RepositoryLocator();
		locator.registryFunction("helloReq", "./");
		locator.registryMessageType("msgReq", "req.txt");
		locator.registryFunction("helloResp", "./");
		locator.registryMessageType("msgResp", "resp.txt");
		//instanciando um gerenciador de mensagem
		MessageManager manager = new MessageManager(locator);
		//criando uma mensagem de req
		Message msgReq = new Message();
		msgReq.setIdentity("698987-79098");
		msgReq.setSequence(0);
		msgReq.setTo("node2");
		msgReq.setFrom("node1");
		msgReq.setMsg("Hello World");
		//encaminhar a mensagem
		manager.write("helloReq", "msgReq", msgReq);
		//
		Message msgResp = manager.read("helloReq", "msgReq", "node2");
		//checagem
		Assert.assertEquals(msgReq.getIdentity(), msgResp.getIdentity());
		Assert.assertEquals(msgReq.getSequence(), msgResp.getSequence());
		Assert.assertEquals(msgReq.getOriginalSequence(), msgResp.getOriginalSequence());
		Assert.assertEquals(msgReq.getTo(), msgResp.getTo());
		Assert.assertEquals(msgReq.getFrom(), msgResp.getFrom());
		Assert.assertEquals(msgReq.getMsg(), msgResp.getMsg());
	}

}
