import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		try {
			ServerSocket serverSocket = new ServerSocket(10999);
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Initialized Connection");
				byte[] b = new byte[1024];
				socket.getInputStream().read(b);
				String name = new String(b).trim();
				String text = "Hello World, " + name;
				socket.getOutputStream().write(text.getBytes());
				socket.getOutputStream().flush();
				socket.close();
				System.out.println("Closed Connection");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
