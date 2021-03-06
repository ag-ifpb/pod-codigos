package test.comm;
import java.io.IOException;
import java.net.Socket;

import ag.protocol.Frame;
import ag.protocol.Transport;
import ag.protocol.impl.DefaultTransport;

public class Client extends AbstractRunnable{

	protected void runWithException() throws IOException {
		//client
		Socket socket = new Socket("localhost", 10999);
		Transport transport = new DefaultTransport(socket);
		Frame frameReq = transport.send(1, "HELO".getBytes(), false, true);
		Frame frameResp = transport.receive();
		//
		System.out.println("Request...");
		frameReq.dump();
		System.out.println("Response...");
		frameResp.dump();
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.run();
	}

}
