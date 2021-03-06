package ag.proxyserver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import ag.proxyserver.util.Util;

public class ControladorTest {

	@Test
	public void test() throws UnknownHostException, IOException {
		//
		System.out.println("Iniciando o teste do controlador");
		Socket socket = new Socket("localhost", 10998);
		System.out.println("Conectado ao servidor");
		//enviando código antigo
		socket.getOutputStream().write(
				("TST 000000-00" + Constants.END_TOKEN).getBytes()
		);
		socket.getOutputStream().flush();
		//recebendo o novo
		byte[] b = new byte[22];
		socket.getInputStream().read(b);
		System.out.println(new String(b).trim());
		//
		FileOutputStream fout = new FileOutputStream("preview-app-iphone-out.mp4");
		Util.readInputStream(socket.getInputStream(), new Util.Callback() {
			@Override
			public void write(int byteStream) {
				try {
					if (byteStream == -1) {
						System.out.println("Encerrando stream do controlador.");
						fout.close();
					}else {
						System.out.println("Escrever no stream do controlador.");
						fout.write(byteStream);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//
		socket.close();
	}
	
}
