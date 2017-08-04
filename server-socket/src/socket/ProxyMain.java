package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyMain {
	
	private static String client(byte[] brcv) throws IOException{
		//opening connection in port 10998, localhost
		Socket socket = new Socket("localhost", 10998);
		//start communication
		InputStream inputStream = socket.getInputStream();//receive
		OutputStream outputStream = socket.getOutputStream();//send
		//send 
		outputStream.write(brcv);
		//receive response
		byte[] b = new byte[1024];
		inputStream.read(b);
		//close connection
		socket.close();
		//
		return new String(b);
	}
	
	private static void server() throws IOException {
		//open connection, bind to 10999
		ServerSocket serverSocket = new ServerSocket(10999);
		//listener, waiting connection
		Socket socket = serverSocket.accept();//block
		//input --> receive / output --> send
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		//byte array (stream)
		byte[] b = new byte[1024];
		//read/receive
		inputStream.read(b);
		//
		String resp = client(b);
		//
		outputStream.write(resp.getBytes());
		//closing
		socket.close();
		//close server
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		server();
	}
}
