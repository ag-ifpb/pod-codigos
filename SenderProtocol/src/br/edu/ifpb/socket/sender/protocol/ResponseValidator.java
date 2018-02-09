package br.edu.ifpb.socket.sender.protocol;

import java.io.IOException;
import java.io.InputStream;

public class ResponseValidator {
	
	public static void valid(InputStream in) throws ResponseValidatorException{
		try {
			byte[] b = new byte[1];
			in.read(b);
			String r = new String(b);
			if (!("0".equals(r) || "1".equals(r))){
				throw new ResponseValidatorException();
			}
		}
		catch (IOException e) {
			throw new ResponseValidatorException();
		}
	}
	
	public static void valid(String value) throws ResponseValidatorException {
		if (!("0".equals(value) || "1".equals(value))){
			throw new ResponseValidatorException();
		}
	}
	
	
	@SuppressWarnings("serial")
	public static class ResponseValidatorException extends Exception{
	}
	
}
