package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class DatagramClient {
	private static Logger logger = Logger.getLogger("AGDebug");

	public static void main(String[] args) throws IOException {
		//log
		logger.info("estabelecer uma comunicação");
		// sender socket doesn’t need a special port number
		DatagramSocket clientSocket = new DatagramSocket();
		//log
		logger.info("setup message");
		//setup message
		InetAddress addr=InetAddress.getByName("localhost");
		String toSend = "HELO";
		byte[] buffer = toSend.getBytes() ;
		//log
		logger.info("building datagram");
		//datagram to receiver’s port 10998
		DatagramPacket question = new DatagramPacket (
				buffer,/*message in bytes*/ 
				buffer.length, /*message lenght*/
				addr, /*destinatary*/
				10998 /*destinatary port*/) ;
		//log
		logger.info("try send message");
		//try send message
		clientSocket.send(question) ;
		//setup response datagram
		DatagramPacket answer = new DatagramPacket(new byte[512], 512);
		//log
		logger.info("try receive message");
		clientSocket.receive(answer);
		//log
		logger.info("Response: " + new String(answer.getData()) + ", " + 
				"Host: " + answer.getAddress().getHostAddress() + ", " +
				"Port: " + String.valueOf(answer.getPort()));
		//close communication
		clientSocket.close();

	}
}
