package br.edu.ifpb.socket.sender.protocol;

import java.io.InputStream;

public class RequestValidator {
	
	public static void valid(InputStream in) throws Exception{
		byte[] b = new byte[5];
		in.read(b);
		String r = new String(b);
		//perguntar se tem virgula
		if (r.contains(",")){
			if (r.indexOf(",") == 2){
				String[] a = r.split(",");
				try {
					int row = Integer.valueOf(a[0]);
					int col = Integer.valueOf(a[1]);
					boolean v = row > 0 && col > 0 && row < 11 && col < 11;
					if (!v){
						throw new Exception();
					}
				}
				catch (NumberFormatException e) {
					throw new Exception();
				}
			}
			else {
				throw new Exception();
			}
		}
		else {
			throw new Exception();
		}
	}
	
	public static void valid(String value) throws Exception{
		String r = value;
		//perguntar se tem virgula
		if (r.contains(",")){
			if (r.indexOf(",") == 2){
				String[] a = r.split(",");
				try {
					int row = Integer.valueOf(a[0]);
					int col = Integer.valueOf(a[1]);
					boolean v = row > 0 && col > 0 && row < 11 && col < 11;
					if (!v){
						throw new Exception();
					}
				}
				catch (NumberFormatException e) {
					throw new Exception();
				}
			}
			else {
				throw new Exception();
			}
		}
		else {
			throw new Exception();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//
		//RequestValidator.valid("1,10");
		RequestValidator.valid("01,10");
		//RequestValidator.valid("11,10");
		//RequestValidator.valid("a,10");
		//RequestValidator.valid("0,304");
		RequestValidator.valid("03,04");
	}

}
