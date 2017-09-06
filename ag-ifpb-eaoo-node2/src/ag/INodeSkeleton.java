package ag;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class INodeSkeleton extends Node2Impl {
	
	protected INodeSkeleton() throws RemoteException {
		super();
	}

	public static void start() throws RemoteException{
		//
		INodeSkeleton skeleton = new INodeSkeleton();
		//
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10999);
			while(true){
				Socket socket = serverSocket.accept();
				byte[] b = new byte[1024];
				socket.getInputStream().read(b);
				String result = skeleton.hello(new String(b).trim());
				socket.getOutputStream().write(result.getBytes());
				socket.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			if (serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
