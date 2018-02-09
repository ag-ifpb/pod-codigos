package websocket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.Base64;

public class WebSocketChannelApp {
  
  private static void sendAndRecv(int fin, SocketChannel socket) throws IOException, InterruptedException{
  //FIN=1, opcode=0x1, msg="hello" //b v*2^p
    //FIN=0x1 RSV1=0x0 RSV2=0x0 RSV2=0x0 opcode=0x01
    //byte = 0x80 | 0x00 | 0x00 | 0x00 | 0x01
    //b10000001 = 2^7+1 = 129 = 8*16^1 + 1*16^1 
    //mask = 0x1 payload_len = 10(d) = 0000 1010 = 0x0A
    //byte = 0x00 | 0x0A
    //b00001010 = 2^3 + 2^1 = 10 = 0xA
    //socket.getOutputStream().write(0x81);// 0x81  
    //socket.getOutputStream().write(0x0A);// 0x0A
    //socket.getOutputStream().write("_arigarcia".getBytes("UTF-8"));
    //0x81 0x85 0x37 0xfa 0x21 0x3d 0x7f 0x9f 0x4d 0x51 0x58
    byte[] bytes = new byte[]{
        (byte)fin, (byte)0x85,
        (byte)0x00, 0x00, 0x00, 0x00,
        (byte)0x48, 0x65, 0x6c, 0x6c, 0x6f
    };
    ByteBuffer out = ByteBuffer.allocate(11);//.wrap(bytes);
    out.put(bytes);
    out.flip();
    socket.write(out);
    
    //
    System.out.println("-------------------> 0");
//    Thread.sleep(500);
    //
    ByteBuffer in = ByteBuffer.allocate(7);
    socket.read(in);
    //
    System.out.println("-------------------> 1");
    //
    byte[] b = in.array();
    //
    int b1 = b[0];
    boolean isFIN =  (b1 & 0x80) == 0x80; //128 -> 0x80 -> b10000000
    boolean isRSV1 = (b1 & 0x40) != 0x00; //64  -> 0x40 -> b01000000
    boolean isRSV2 = (b1 & 0x20) != 0x00; //32  -> 0x20 -> b00100000
    boolean isRSV3 = (b1 & 0x10) != 0x00; //16  -> 0x10 -> b00010000
    boolean opcode_text = (b1 & 0x01) == 0x01;
    boolean opcode_binary = (b1 & 0x02) == 0x02;
    boolean opcode_closed = (b1 & 0x08) == 0x08;
    //
    System.out.println("FIN: " + isFIN);
    System.out.println("RSV1: " + isRSV1);
    System.out.println("RSV2: " + isRSV2);
    System.out.println("RSV3: " + isRSV3);
    System.out.println("opcode-text: " + opcode_text);
    System.out.println("opcode-binary: " + opcode_binary);
    System.out.println("opcode-closed: " + opcode_closed);
    //
    int b2 = b[1];
    boolean isMask =  (b2 & 0x80) == 0x80; //128 -> 0x80 -> b10000000
    int len;
    if (isMask){
      len = (b2 ^ 0x80);
    }
    else {
      len = b2;
    }
    //
    System.out.println("Mask: " + isMask);
    System.out.println("Length: " + len);
    //
    byte[] b3 = Arrays.copyOfRange(b, 2, len + 2);
    if (opcode_closed){
      System.out.println("text: " + new BigInteger(b3));
      System.out.println("text: " + new String(b3));
    }
    else {
      System.out.println("text: " + new String(b3));
    }
    
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    //
    //Socket socket = new Socket("uol.com.br", 80);
    //Socket socket = new Socket("ag-websocket-go.herokuapp.com", 80);
    //non-block io
    SocketChannel socket = SocketChannel.open(new InetSocketAddress("ag-websocket-go.herokuapp.com", 80));
    socket.configureBlocking(true);//false
    //
    //Selector selector = Selector.open();
    
    //
    StringBuffer buffer = new StringBuffer();
    //buffer.append("GET / HTTP/1.1");
    //buffer.append("GET /echo HTTP/1.1");
    //buffer.append("GET /echo?encoding=text HTTP/1.1");
    buffer.append("GET wss://ag-websocket-go.herokuapp.com/echo?encoding=text HTTP/1.1");
    buffer.append("\r\n");
    buffer.append("Host: ag-websocket-go.herokuapp.com");
    buffer.append("\r\n");
    //buffer.append("Pragma: no-cache");
    //buffer.append("\r\n");
    //buffer.append("Accept-Encoding: gzip, deflate, sdch, br");
    //buffer.append("\r\n");
    //buffer.append("Accept-Language: en-US,en;q=0.8");
    //buffer.append("\r\n");
    buffer.append("Upgrade: websocket");
    buffer.append("\r\n");
    buffer.append("Connection: Upgrade");
    buffer.append("\r\n");
    //buffer.append("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) ");
    //buffer.append("\r\n");
    buffer.append("Origin: http://aristofanio.ga");
    buffer.append("\r\n");
    buffer.append("Sec-WebSocket-Key: ").append(Base64.getEncoder().encodeToString(String.valueOf(Math.random()).getBytes()));
    buffer.append("\r\n");
    buffer.append("Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits");
    buffer.append("\r\n");
    //buffer.append("Sec-WebSocket-Protocol: chat, superchat");
    //buffer.append("\r\n");
    buffer.append("Sec-WebSocket-Version: 13");
    buffer.append("\r\n");
    buffer.append("\r\n");
    //
    System.out.println("===============");
    System.out.println("REQUEST RAW");
    System.out.println("===============");
    System.out.println(buffer.toString());
    System.out.println("===============");
    System.out.println("RESPONSE RAW");
    System.out.println("===============");
    //
    ByteBuffer byteBufferReq = ByteBuffer.wrap(buffer.toString().getBytes("UTF-8"));
    socket.write(byteBufferReq);
    //
    ByteBuffer byteBufferResp = ByteBuffer.allocate(1024);
    socket.read(byteBufferResp);
    System.out.println(new String(byteBufferResp.array()).trim());
    //
    System.out.println("===============");
    System.out.println("EXCHANGE");
    System.out.println("===============");
    //
    Thread.sleep(1000);
    //-----------------------------------------------------------------
    long t0 = System.currentTimeMillis();
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    sendAndRecv(0x81, socket);
    long t1 = System.currentTimeMillis();
    System.out.println(t1 - t0);
    //-----------------------------------------------------------------
    // 2.5s / 15 exchange ~ 0.17 (redução de ~60%)
    //
    socket.close();
  }
}
