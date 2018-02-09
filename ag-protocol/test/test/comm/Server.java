package test.comm;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import ag.protocol.Frame;
import ag.protocol.Transport;
import ag.protocol.impl.DefaultTransport;

public class Server extends AbstractRunnable{

	protected void runWithException() throws IOException {
		//
		System.out.println("Servidor...");
		//
		ServerSocket serverSocket = new ServerSocket(10999);
		Socket socket = serverSocket.accept();
		//
		Transport transport = new DefaultTransport(socket);
		Frame frameReq = transport.receive();
		//
		Frame frameResp;
		if (frameReq.isText()){
			String ok = " (received: ok)";
			ByteBuffer buf = ByteBuffer.allocate(frameReq.getLength()+ok.length());
			buf.put(frameReq.getPayload());
			buf.put(ok.getBytes());
			frameResp = transport.send(frameReq.getIdentiy()+1, buf.array(), false, false);
		} else {
			String ok = "binary received: ok";
			ByteBuffer buf = ByteBuffer.allocate(ok.length());
			buf.put(ok.getBytes());
			frameResp = transport.send(frameReq.getIdentiy()+1, buf.array(), false, false);
		}
		//
		socket.close();
		serverSocket.close();
		//
		System.out.println("Request...");
		frameReq.dump();
		System.out.println("Response...");
		frameResp.dump();
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}
	
	
}
