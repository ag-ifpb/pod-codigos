

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMain {
	
	public static String proxy(String text) throws IOException{
		//
		String HOST = "localhost";
		Integer PORT = 10999;
		//
		Socket socket = null;
		try {
			//criar uma conexão com o servidor
			socket = new Socket(HOST, PORT);
		}
		catch(ConnectException e){
			return "ERROR-Conexão recusada";
		}
		//encaminhar o texto para o servidor (request)
		OutputStream output = socket.getOutputStream();
		output.write(text.getBytes());
		//log
		System.out.println("Encaminhando e recebendo para/de pedro");
		//leitura da resposta do servidor
		InputStream input = socket.getInputStream();
		byte[] b = new byte[1024];
		input.read(b);
		//converte a resposta de bytes para texto
		String response = new String(b);
		//
		if (!response.startsWith("#"))
			return "ERROR-Falha na comunicação com servidor";
		//encerra a conexão com o servidor
		socket.close();
		//devolve a resposta do servidor
		return response;
	}

	public static void main(String[] args) throws IOException {
		//
		System.out.println("Servidor proxy ativo");
		//
		ServerSocket serverSocket = new ServerSocket(10998);
		while(true){
			//recebe uma conexão
			Socket socket = serverSocket.accept();
			//faz a leitura da requisição
			InputStream input = socket.getInputStream();
			byte[] b = new byte[1024];
			input.read(b);
			//converte em texto
			String text = new String(b);
			//
			System.out.println("Requesição recebido: " + text);
			String txt = proxy(text);//encaminhar para o servidor (node3)
			System.out.println("Resposta recebida: " + txt);
			//devolver a resposta do servidor para requisitor
			OutputStream output = socket.getOutputStream();
			output.write(txt.getBytes());
			//close connection
			socket.close();
		}
		//serverSocket.close();
	}
		
}
