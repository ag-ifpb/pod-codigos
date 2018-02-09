package edu.ifpb.pod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SenderClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Cliente inicializado e conectando.");
		//
		Socket client = new Socket("localhost", 10998);
		System.out.println("Cliente conectado...");
		System.out.println("Escrevendo no stream e enviando...");
		OutputStream out = client.getOutputStream();
		out.write("Texto que foi enviado...".getBytes());
		out.flush();
		client.close();
		System.out.println("Conexão encerrada.");
	}

}
