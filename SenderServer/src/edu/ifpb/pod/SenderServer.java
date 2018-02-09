package edu.ifpb.pod;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderServer {


	public static void main(String[] args) throws IOException {
		System.out.println("Servidor inicializado.");
		ServerSocket server = new ServerSocket(10998);
		System.out.println("Aguardando conexão...");
//		Socket socket = server.accept();
//		System.out.println(socket);
		Socket socket;
		while ((socket = server.accept()) != null){
			InputStream in = socket.getInputStream();
			//leitura do stream
			byte[] b = new byte[1024];
			System.out.println("Tamanho do stream: " + b.length);
			System.out.println(in.read(b));
			//
			String command = new String(b);
			String c = command.trim();
			System.out.println(command);
			if ("fim".equals(c)){
				socket.close();
				break;
			}			
		}
		//
		System.out.println("Servidor encerrado.");
	}

}
