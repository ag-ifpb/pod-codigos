package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		//opening connection in port 10999, localhost
		Socket socket = new Socket("localhost", 10998);
		//Socket socket = new Socket("192.168.0.111", 10998);
		//start communication
		InputStream inputStream = socket.getInputStream();//receive
		OutputStream outputStream = socket.getOutputStream();//send
		//
		Thread.sleep(30000);
		//send HELO
		outputStream.write("HELO".getBytes());
		//receive response
		byte[] b = new byte[1024];
		inputStream.read(b);
		//print response
		System.out.print(new String(b));
		System.out.println("<---");
		//close connection
		socket.close();
	}
}
