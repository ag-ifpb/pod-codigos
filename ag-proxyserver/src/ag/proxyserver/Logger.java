package ag.proxyserver;

import java.util.logging.Level;

public class Logger {
	private static volatile java.util.logging.Logger logger = java.util.logging.Logger.getLogger("ProxyServer-Log");
	
	static {
		logger.setLevel(Level.ALL);
	}
	
	public static void info(String msg){
		logger.info(msg);
	}
	
	public static void error(String msg){
		logger.severe(msg);
	}
}
