package edu.ifpb.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectSenderServer {


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Servidor inicializado.");
		ServerSocket server = new ServerSocket(10997);
		System.out.println("Aguardando conexão...");
		//
		while (true){
      		Socket socket = server.accept();
      		InputStream in = socket.getInputStream();
      		//leitura do stream
      		ObjectInputStream oin = new  ObjectInputStream(in);
      		ObjectStream objInput = (ObjectStream) oin.readObject();
      		//
      		System.out.println("Recebendo o objeto.");
      		System.out.println(objInput);
      		System.out.println("Conteúdo do objeto: " + objInput.getName());
      		//
      		if (objInput.isExit()){
      		  break;
      		}
		}
		//
		System.out.println("Servidor encerrado.");
	}

}
