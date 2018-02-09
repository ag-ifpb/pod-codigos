package websocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.rmi.registry.LocateRegistry;
import java.util.Locale;

public class DatagramaMain {
  
  private static void recv() throws IOException{
    DatagramSocket d = new DatagramSocket(5000);
    byte[] buf = new byte[5];
    DatagramPacket p = new DatagramPacket(buf, buf.length);
    d.receive(p);
    System.out.println(new String(p.getData()));
    d.close();
  }
  
  private static void send() throws IOException{
    DatagramSocket d = new DatagramSocket();
    byte[] buf = "Hello".getBytes();
    
    //DatagramPacket p = new DatagramPacket(buf, buf.length, InetAddress.getByAddress(new byte[]{0x0A, 0x00, 0x03, (byte)0xff}), 5000);
    DatagramPacket p1 = new DatagramPacket(buf, buf.length, InetAddress.getByName("uol.com.br"), 80);
    d.send(p1);
    System.out.println("enviado...");
    DatagramPacket p2 = new DatagramPacket(buf, buf.length);
    d.receive(p2);
    System.out.println(new String(p2.getData()));
    d.close();
  }

  public static void main(String[] args) throws IOException {
    recv();
    //send();
  }
}
