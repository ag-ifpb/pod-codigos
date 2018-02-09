import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		InetAddress group = InetAddress.getByName("10.3.181.63");
		 MulticastSocket s = new MulticastSocket(6789);
		 s.joinGroup(group);
		 // Envia e recebe mensagens.
		 s.leaveGroup(group);

	}
}
