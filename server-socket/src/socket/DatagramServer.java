package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Logger;

public class DatagramServer {
	private static Logger logger = Logger.getLogger("AGDebug");

	public static void main(String[] args) throws IOException {
		//log 
		logger.info("open connection, bind (my ip = 0.0.0.0/0), list on 10998");
		//open connection, bind to 10998
		DatagramSocket serverSocket = new DatagramSocket(10998);
		//log
		logger.info("building datagram for request");
		//datagram from receiver
		DatagramPacket question = new DatagramPacket(new byte[512], 512);
		serverSocket.receive(question);
		//log
		logger.info("Request: " + new String(question.getData()) + ", " + 
				"Host: " + question.getAddress().getHostAddress() + ", " +
				"Port: " + String.valueOf(question.getPort()));
		//request data
		String reqData = new String(question.getData());
		//log
		logger.info("setup response");
		//building response
		String respData = reqData + ", Ari";
		byte[] buffer = respData.getBytes();
		//log
		logger.info("build response datagram");
		//build response datagram
		DatagramPacket answer = new DatagramPacket(buffer,
				buffer.length, question.getAddress(), question.getPort());
		serverSocket.send(answer);
		//close connection
		serverSocket.close();
	}
	
}
