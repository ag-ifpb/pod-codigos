package ag.webcamfake;

import java.io.IOException;
import java.net.Socket;

public class Connector {
	private Socket socket;
	private boolean connected = false;

	/**
	 * Inicia uma conexão com o servidor proxy.
	 * 
	 * @throws WebCamException - disparado caso 
	 * não consiga a conexão seja recusada (erro 1001) 
	 * ou caso a conexão expire (erro 1002).
	 */
	public void connect() throws WebCamException {
		//Socket - conexão persistente
		try {
			socket = new Socket("localhost", 10999);
			connected = true;
		} catch (IOException e) {
			throw new WebCamException(1002, "Tentantiva de conexão com o servidor.");
		}
	}
	
	/**
	 * Verifica se a conexão foi estabelecida.
	 * 
	 * @return
	 */
	public boolean isConnected(){
		return connected;
	}
	
	/**
	 * Desconecta o servidor
	 * 
	 * @throws WebCamException
	 */
	public void disconnect() throws WebCamException{
		try {
			connected = false;
			socket.close();
		} catch (IOException e) {
			throw new WebCamException(1002, "Tentantiva de conexão com o servidor.");
		}
	}
	
	public void send(byte[] data) throws IOException{
		socket.getOutputStream().write(data);
		socket.getOutputStream().flush();
	}
	
	public byte[] receive(int size) throws IOException {
		byte[] b = new byte[size];
		int r = socket.getInputStream().read(b);
		return (r > 0 ? b : null);
	}
	
}
