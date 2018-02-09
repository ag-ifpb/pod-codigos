package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

//192.168.1.105
public class ServerMain {
	private static Logger logger = Logger.getLogger("AGDebug");
	
	public static void main(String[] args) throws IOException {
		//log 
		logger.info("open connection, bind (my ip = 0.0.0.0/0), list on 10998");
		//open connection, bind to 10998
		ServerSocket serverSocket = new ServerSocket(10998);
		//log
		logger.info("waiting connection");
		//listener, waiting connection
		Socket socket = serverSocket.accept();//block
		//log
		logger.info("get input, and output stream");
		//input --> receive / output --> send
		DataInputStream inputStream = new DataInputStream(
				socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(
				socket.getOutputStream());
		//log
		logger.info("read string in stream");
		//read/receive
		String reqData = inputStream.readUTF();
		//log
		logger.info("--------> Requested: " + reqData);
		//log
		logger.info("write string in stream");
		//write/send
		outputStream.writeUTF(reqData + ", Ari");
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
