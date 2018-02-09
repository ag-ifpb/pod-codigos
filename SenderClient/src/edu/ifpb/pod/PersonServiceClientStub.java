package edu.ifpb.pod;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PersonServiceClientStub {
  private final String SERVER_HOST = "localhost";
  private final Integer SERVER_PORT = 10999;

  public void sendBySocket(Person p){
    try {
      //
      Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
      //
      ObjectOutputStream objout = new ObjectOutputStream(socket.getOutputStream());
      objout.writeObject(p);
      objout.close();
      socket.close();
      //
    } 
    catch (UnknownHostException e) {
      e.printStackTrace();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
