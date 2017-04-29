package ag.proxyserver.proxy;

public class ProxyServerException extends Exception {

	public ProxyServerException(int errorCode, String msg) {
		super(msg + " (code: " + errorCode + ")");
	}
	
}
