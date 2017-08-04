package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerMain {
	private static Logger logger = Logger.getLogger("AGDebug");
	
	public static void main(String[] args) throws IOException {
		//log
		logger.info("open connection, bind to 10998");
		//open connection, bind to 10998
		//192.168.1.105
		ServerSocket serverSocket = new ServerSocket(10998);
		//log
		logger.info("listener, waiting connection");
		//listener, waiting connection
		Socket socket = serverSocket.accept();//block
		//log
		logger.info("get input, and output stream");
		//input --> receive / output --> send
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		//log
		logger.info("read 4 bytes in stream");
		//byte array (stream)
		byte[] b = new byte[1024];
		//read/receive
		inputStream.read(b);
		//log
		logger.info("write 2 bytes in stream");
		//write/send
		String resp = new String(b).trim();
		if ("HELO".equals(resp)){
			outputStream.write("OK".getBytes());
		} else {
			outputStream.write("ER".getBytes());
		}
		//log
		logger.info("close client connection");
		//closing
		socket.close();
		//log
		logger.info("close server-listener");
		//close server
		serverSocket.close();
	}

}
