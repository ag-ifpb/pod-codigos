package ag.webcamfake;

import java.io.FileInputStream;

import ag.webcamfake.impl.ProxyServerImpl;
import ag.webcamfake.impl.WebCamImpl;

public class Main {
	
	private static void connect(Connector connector) {
		while(!connector.isConnected()){
			//
			System.out.println("Inicializando uma conexão.");
			//
			try {
				//conectar ao proxy
				connector.connect();
				Thread.sleep(1000);
				break;
			}
			catch(WebCamException | InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		//antigo código da webcam
		String oldCode = "COD-010101";//or null
		//inicializar webcam e registrar
		Connector connector = new Connector();
		//conecta no proxy
		connect(connector);
		//
		try {
			//
			ProxyServer server = new ProxyServerImpl(connector);
			//registrar a webcam e receber um código
			String code = server.register(oldCode);
			//webcam
			WebCam webCam = new WebCamImpl(code);
			//aguardar commandos
			FileInputStream finput = null;
			while(true){
				//receber um comando ['PLAY', 'STREAM + <OFFSET>:<ENDSET>:<LENGTH>']
				String command = server.awaitCommand();
				//commands
				if ("PLAY".equals(command)){
					finput = webCam.play();
					server.transferStreamToProxy(finput);
				}
				else if ("STOP".equals(command)){
					if (finput != null) webCam.stop(finput);
				}
				else {
					break;
				}
			}
		}
		catch(WebCamException e){
			e.printStackTrace();
		}
	}

}
