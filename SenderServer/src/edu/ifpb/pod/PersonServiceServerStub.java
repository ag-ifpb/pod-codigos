package edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonServiceServerStub {
  private final PersonService service = new PersonServiceImpl();

  public void receive(){
    try {
      //cria um servidor de socket
      ServerSocket serverSocket = new ServerSocket(10999);
      //aguarda a conexão do socket
      Socket socket = serverSocket.accept();
      //recupera o stream do objeto
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      //converte o objeto recebido em person
      Person p = (Person) input.readObject();
      input.close();
      //salva o person
      //qual é o método? save(), cancel(), delete()...?
      service.save(p);
      //fechar o socket e o servidor de socket
      socket.close();
      serverSocket.close();
    } 
    catch (IOException e) {
      e.printStackTrace();
    } 
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
  }
  
}
