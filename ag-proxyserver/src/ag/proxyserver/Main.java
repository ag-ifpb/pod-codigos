package ag.proxyserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ag.proxyserver.proxy.ProxyServerSocket;
import ag.proxyserver.reverse.ReverseServerSocket;
import ag.proxyserver.streamer.StreamBinderManager;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//log
		Logger.info("Inicializando o servidor proxy.");
		//instanciar os servidores
		ServerSocket proxyServerSocket = new ServerSocket(10998);
		ServerSocket reverseServerSocket = new ServerSocket(10999);
		//
		StreamBinderManager manager = new StreamBinderManager();
		//proxy
		Thread tProxyServerSocket = new Thread(){
			public void run() {
				Logger.info("Inicializando a escuta na porta 10998.");
				while(true){
					Socket socket = null;
					try {
						socket = proxyServerSocket.accept();
						Logger.info("Recebendo uma conexão do controle.");
						ProxyServerSocket proxyServerSocket = new ProxyServerSocket(socket, manager);
						proxyServerSocket.start();
					}
					catch(IOException e){
						e.printStackTrace();
						try {
							if (socket != null) socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Logger.info("Finalizando uma conexão do controle.");
					}
				}
			};
		};
		//for server
		//reverse
		Thread tReverseServerSocket = new Thread(){
			public void run() {
				Logger.info("Inicializando a escuta na porta 10999.");
				while(true){
					Socket socket = null;
					try {
						socket = reverseServerSocket.accept();
						Logger.info("Recebendo uma conexão da webcam.");
						ReverseServerSocket reverseServerSocket = new ReverseServerSocket(socket, manager);
						reverseServerSocket.start();
					}
					catch(IOException e){
						e.printStackTrace();
						try {
							if (socket != null) socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Logger.info("Finalizando uma conexão da webcam.");
					}
				}
			};
		};
		//
		tProxyServerSocket.start();
		tReverseServerSocket.start();
		//
		tProxyServerSocket.join();
		tReverseServerSocket.join();
		//
		proxyServerSocket.close();
		reverseServerSocket.close();
	}

}
