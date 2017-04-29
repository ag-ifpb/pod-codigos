package ag.proxyserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import ag.proxyserver.util.Util;

public class WebCamTest {

	@Test
	public void test() throws UnknownHostException, IOException {
		//
		System.out.println("Iniciando o teste do webcam.");
		Socket socket = new Socket("localhost", 10999);
		System.out.println("Conectado ao servidor");
		//enviando código antigo
		socket.getOutputStream().write(
				("TST 000000-00" + Constants.END_TOKEN).getBytes()
		);
		socket.getOutputStream().flush();
		//recebendo o novo
		byte[] b = new byte[22];
		socket.getInputStream().read(b);
		System.out.println(new String(b));
		//aguardando um comando
		String comm = Util.readInputStream(socket.getInputStream());
		System.out.println("Comando recebido pela webcam");
		//transferir video
		FileInputStream finput = new FileInputStream("preview-app-iphone.mp4");
		//
		System.out.println("Iniciando a transmissão");
		int r = 0;
		while((r = finput.read()) != -1){
			socket.getOutputStream().write(r);
		}
		finput.close();
		System.out.println("Finalização a transmissão");
		//
		socket.close();
	}

}
