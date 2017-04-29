import java.io.IOException;
import java.net.Socket;
import java.util.Base64;

public class ClientTest {

	public static void main(String[] args) throws IOException {
		//
		System.out.println(System.currentTimeMillis());
		//
		Socket socket = new Socket("127.0.0.1", 1025);
		socket.getOutputStream().write("GET /preview-app-iphone.mp4 HTTP/1.1\r\n".getBytes());
		socket.getOutputStream().write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n".getBytes());
		socket.getOutputStream().write("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36\r\n".getBytes());
		socket.getOutputStream().write("\r\n".getBytes());
		byte[] b = new byte[263];
		socket.getInputStream().read(b);
		System.out.print(new String(b));
		//
		b = new byte[2];
		socket.getInputStream().read(b);
		System.out.print(new String(Base64.getEncoder().encode(b)));
		//
		int t = 0;
		while(true){
			b = new byte[1913579];
			int s = socket.getInputStream().read(b);
			t += s;
			System.out.println(t);
			if (t == 1913579) break;
		}
		//
		b = new byte[2];
		socket.getInputStream().read(b);
		System.out.print(new String(Base64.getEncoder().encode(b)));
		b = new byte[2];
		socket.getInputStream().read(b);
		System.out.print(new String(Base64.getEncoder().encode(b)));
		b = new byte[2];
		socket.getInputStream().read(b);
		System.out.print(new String(Base64.getEncoder().encode(b)));
		//b = new byte[1024];
		//socket.getInputStream().read(b);
		//System.out.print(new String(Base64.getEncoder().encode(b)));
		socket.close();
	}
}
