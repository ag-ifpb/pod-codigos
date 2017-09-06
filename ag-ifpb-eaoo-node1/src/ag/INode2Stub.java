package ag;

import java.io.IOException;
import java.net.Socket;

public class INode2Stub implements INode2 {
	private static final String HOST = "localhost";
	private static final int PORT =  10999;

	@Override
	public String hello(String name) {
		try{
			Socket socket = new Socket(HOST, PORT);
			//hello;1;name;name-value
			socket.getOutputStream().write(name.getBytes());
			//
			byte[] b = new byte[1024];
			socket.getInputStream().read(b);
			socket.close();
			//
			return new String(b).trim();
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
