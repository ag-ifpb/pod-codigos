package ag.proxyserver.streamer;

import ag.proxyserver.Logger;

public class Stream {
	byte[] b = new byte[1];
	
	public void send(int byteStream){
		Logger.info("Stream send()");
		b[0] = (byte) byteStream;
		synchronized (b) {
			b.notify();
		}
	}
	
	public int recv() throws InterruptedException{
		Logger.info("Stream recv()");
		synchronized (b) {
			b.wait();
		}
		return b[0];
	}
	
}
