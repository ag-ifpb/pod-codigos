package websocket;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {

  public static void main(String[] args) throws UnknownHostException, IOException {
//    int x  = 0x01;
//    System.out.println(x);
//    //
//    Socket s = new Socket("10.0.3.255", 0);
//    byte[] b = new byte[32];
//    s.getInputStream().read(b);
//    s.close();
    Enumeration<NetworkInterface> enums = NetworkInterface.getNetworkInterfaces();
    System.out.println(enums.nextElement().getIndex());
    System.out.println(enums.nextElement().getIndex());
    System.out.println(enums.nextElement().getIndex());
    System.out.println(enums.nextElement().getIndex());
    System.out.println(enums.nextElement().getIndex());
    //
    System.out.println(NetworkInterface.getByIndex(4).getInetAddresses().nextElement());
    //
    //ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
  }
}
