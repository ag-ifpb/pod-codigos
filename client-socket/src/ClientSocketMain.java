import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketMain {

	public static void main(String[] args) throws Exception {
		//
		String HOST = "localhost";
		Integer PORT = 10998;
		//
		Socket socket = new Socket(HOST, PORT);
		OutputStream output = socket.getOutputStream();
		output.write("Requisição enviada pelo cliente.".getBytes());
		//
		InputStream input = socket.getInputStream();
		byte[] b = new byte[1024];
		input.read(b);
		//
		String text = new String(b);
		//
		if (text.contains("ERROR-Conexão recusada"))
			throw new Exception("Conexão com o servidor principal foi recusada");
		System.out.println(text.trim());
		//
		socket.close();
	}

}
