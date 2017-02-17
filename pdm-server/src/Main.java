import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(10999);
			while(true){
				Socket socket = serverSocket.accept();
				byte[] b = new byte[1024];
				socket.getInputStream().read(b);
				String name = new String(b).trim();
				String text = "Hello World, " + name;
				socket.getOutputStream().write(text.getBytes());
				socket.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
