package ag.proxyserver.reverse;

@SuppressWarnings("serial")
public class ReverseServerException extends Exception{

	public ReverseServerException(int errorCode, String msg) {
		super(msg + " (code: " + errorCode + ")");
	}
	
}
