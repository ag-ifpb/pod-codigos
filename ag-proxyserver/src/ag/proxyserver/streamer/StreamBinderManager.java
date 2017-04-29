package ag.proxyserver.streamer;

import java.util.HashMap;
import java.util.Map;

import ag.proxyserver.Logger;

public class StreamBinderManager {
	private final Map<String, StreamBinder> list = new HashMap<>();
	
	public void registry(String code){
		Logger.info("Registrando um StreamBinder para " + code);
		list.put(code, new StreamBinder());
	}
	
	public StreamBinder getBinder(String code){
		Logger.info("Recuperando um StreamBinder para " + code);
		return list.get(code);
	}
	
}
