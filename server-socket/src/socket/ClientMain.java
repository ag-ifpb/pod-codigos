package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientMain {
	private static Logger logger = Logger.getLogger("AGDebug");

	public static void main(String[] args) throws IOException, InterruptedException {
		//log
		logger.info("open connection with host on 10998");
		//opening connection in port 10999, localhost
		Socket socket = new Socket("localhost", 10999);
		//Socket socket = new Socket("192.168.0.111", 10998);
		//log
		logger.info("setup input and output stream");
		//start communication
		DataInputStream inputStream = new DataInputStream(
				socket.getInputStream());//input/receive
		DataOutputStream outputStream = new DataOutputStream(
				socket.getOutputStream());//output/send
		//log
		logger.info("send request");
		//send HELO
		outputStream.writeUTF("HELO");
		//log
		logger.info("receving response");
		//receive response
		String resp = inputStream.readUTF();
		//print response
		//log
		logger.info("--------> Response: " + resp);
		//close connection
		socket.close();
		//log
		logger.info("closed connection");
	}
}
