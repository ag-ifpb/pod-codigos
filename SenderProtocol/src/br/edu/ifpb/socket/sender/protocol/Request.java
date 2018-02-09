package br.edu.ifpb.socket.sender.protocol;

public class Request {
	private String row;
	private String col;
	
	private String format(String v){
		if (v.length() != 2){
			return "0" + v;
		}
		return v;
	}
	
	public Request(String row, String col) {
		this.row = row;
		this.col = col;		
	}
	
	public String getValue(){
		return format(row) + "," + format(col);
	}
	
	public static void main(String[] args) {
		Request r = new Request("1", "10");
		System.out.println(r.getValue());
	}
	
}
