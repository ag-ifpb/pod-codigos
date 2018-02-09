package br.edu.ifpb.socket.sender.protocol;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private OutputStream out;

	public Response(OutputStream stream) {
		this.out = stream;
	}
	
	public void setValue(String value) throws Exception{
		try {
			if ("0".equals(value)||"1".equals(value)){
				out.write(value.getBytes());
				out.flush();
			}
			else {
				throw new Exception();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

	