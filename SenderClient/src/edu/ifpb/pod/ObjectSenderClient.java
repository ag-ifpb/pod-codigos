package edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectSenderClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Cliente inicializado e conectando.");
		Socket client = new Socket("localhost", 10997);
		System.out.println("Cliente conectado...");
		System.out.println("Escrevendo no stream e enviando...");
		OutputStream out = client.getOutputStream();
		ObjectOutputStream oout = new  ObjectOutputStream(out);
		//
		ObjectStream os = new ObjectStream();
		os.setName("Objeto de Stream");
		//
		oout.writeObject(os);
		out.flush();
		client.close();
		System.out.println("Conexão encerrada.");
	}
	
}
