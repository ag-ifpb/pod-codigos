package ag.ifpb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main {
	static byte first = 0x2;
	
	public static void hasValue(boolean value){
		if (value){
			first = (byte) (first | 0x4);
		} else {
			first = (byte) (first & 0x3);
		}
	}
	
	public static void testHasNext(){
		//0 0 0 0 0 0 0 0 - false (2)
		//0 0 0 0 0 1 0 0 - true  (6)
		System.out.println("-> " + first);
		hasValue(true);
		System.out.println("-> " + first);
		hasValue(false);
		System.out.println("-> " + first);
		hasValue(true);
		System.out.println("-> " + first);
		hasValue(false);
		System.out.println("-> " + first);
	}
	
//	public static void main(String[] args) {
//		//
//		String msg = "Primeira mensagem..";//19
//		//
//		Marshaller marshaller = new MarshalerImpl();
//		Unmarshaller unmarshaller = new UnmarshallerImpl();
//		//
//		byte[] frame = marshaller.length(msg.length())
//			.hasNext(true)
//			.message(msg)
//			.build();
//		//
//		unmarshaller.frame(frame);
//		System.out.println("Continuidade: " + unmarshaller.hasNext());
//		System.out.println("Tamanho da Mensagem: " + unmarshaller.length());
//		System.out.println("Mensagem: " + unmarshaller.message());
//	}
	
	public static class Model implements Serializable{
		private int x = 0;
		private String y = null;
	}
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("lixo.obj");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(new Model());
		out.flush();
		out.close();
		fout.close();
	}
	
}
