package ag.proxyserver.proxy.impl;

import ag.proxyserver.Constants;
import ag.proxyserver.Logger;
import ag.proxyserver.proxy.ProxyServer;
import ag.proxyserver.proxy.ProxyServerException;
import ag.proxyserver.streamer.Stream;
import ag.proxyserver.streamer.StreamBinder;
import ag.proxyserver.streamer.StreamBinderManager;

public class ProxyServerImpl implements ProxyServer{
	private final StreamBinderManager manager;
	//
	private String code = null;
	
	public ProxyServerImpl(StreamBinderManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean play(String code) throws ProxyServerException {
		if (code == null) throw new ProxyServerException(
				5000, "Erro ao transferir dados do stream. Código inválido."
		);
		//
		this.code = code;
		//
		StreamBinder binder = manager.getBinder(code);
		Stream stream = binder.getCommandStream();
		byte[] b = ("PLAY" + Constants.END_TOKEN).getBytes();
		for (int i = 0; i < b.length; i++) {
			stream.send(b[i]);
		}
		return true;
	}

	@Override
	public int readStream() {
		Logger.info("Transferindo stream do ProxyServerImpl");
		StreamBinder binder = manager.getBinder(code);
		Stream stream = binder.getTransferStream();
		try {
			return stream.recv();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
