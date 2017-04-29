package ag.webcamfake.impl;

import java.io.IOException;
import java.io.InputStream;

import ag.webcamfake.Connector;
import ag.webcamfake.Constants;
import ag.webcamfake.ProxyServer;
import ag.webcamfake.WebCamException;

public class ProxyServerImpl implements ProxyServer{
	private final Connector connector;
	
	private StringBuffer readStream() throws WebCamException{
		//
		StringBuffer buffer = new StringBuffer();
		//
		while(true){
			//
			byte[] b = null;
			//
			try {
				b = connector.receive(1);
			} catch (IOException e) {
				throw new WebCamException(4000, "Erro ao fazer leitura do comando.");
			}
			//
			if ( b != null){
				buffer.append(new String(b));
				if (buffer.toString().contains(Constants.END_TOKEN)){
					break;
				}
			}
		}
		//
		return buffer;
	}
	
	public ProxyServerImpl(Connector connector) {
		this.connector = connector;
	}

	@Override
	public String register(String oldCode) throws WebCamException {
		//
		String c = "COD-000000";//mask
		if (oldCode != null){
			c = oldCode;
		}
		//
		String msg = c + Constants.END_TOKEN;
		try {
			//write
			connector.send(msg.getBytes());
			//read
			byte[] b = connector.receive(19);
			//convert byte[] in msg
			String rslt = new String(b);
			if (rslt.contains(Constants.END_TOKEN)){
				c = rslt.replace(Constants.END_TOKEN, "");
			}
		}
		catch(IOException e){
			throw new WebCamException(2001, "Erro ao tentar escrever no stream.");
		}
		//
		return c;
	}

	@Override
	public String awaitCommand() throws WebCamException {
		//
		StringBuffer buffer = readStream();
		//
		return buffer.toString().replace(Constants.END_TOKEN, "");
	}

	@Override
	public void transferStreamToProxy(InputStream finput) throws WebCamException {
		//-------------------------------------------------
		//solicitar os parametros do stream
		//-------------------------------------------------
		StringBuffer buffer = readStream();
		//formato: <OFFSET>:<ENDSET>:<LENGTH>
		String[] params = buffer
				.toString()
				.replace(Constants.END_TOKEN, "")
				.split(":");
		//-------------------------------------------------
		//TODO resolver o problema de range
		//capturar os bytes nas posições do stream
		//-------------------------------------------------
		int offset = Integer.parseInt(params[0]);
		int endset = Integer.parseInt(params[1]);
		int totalReceived = 0;
		int partialReceived = 0;
		long remaining = endset - offset + 1;
		//
		int bsize = (remaining < 1024? (int) remaining : 1024);
		byte[] b = new byte[bsize];
		//
		try {
			//
			finput.skip(offset);
			while(true){
				partialReceived = finput.read(b, 0, bsize);
				if (partialReceived > 0){
					connector.send(b);
					totalReceived += partialReceived;
				}
				if (remaining - totalReceived <= 0){
					break;
				}
			}
			//
			connector.send(Constants.END_TOKEN.getBytes());
		}
		catch(IOException e){
			throw new WebCamException(5000, "Erro ao transferir dados para o proxy.");
		}
		
	}

}
