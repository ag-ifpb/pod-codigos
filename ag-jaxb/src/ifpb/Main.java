package ifpb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXB;

public class Main {
	//
	private static void marshall() throws IOException{
		RemoteRef ref = new RemoteRef();
		ref.setHost("123.092.222.121");
		ref.setPort(10999);
		//
		Message m = new Message();
		m.setId(12231);
		m.setRef(ref);
		//
		//FileOutputStream out = new FileOutputStream("test.xml");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JAXB.marshal(m, out);
		//
		String xml = new String(out.toByteArray());
		System.out.println(xml);
		//
		out.close();
	}
	
	private static void unmarshall(){
		//
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><message><id>12231</id><ref><host>123.092.222.121</host><port>10999</port></ref></message>";
		ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
		//
		Message m = JAXB.unmarshal(in, Message.class);
		System.out.println("Id: " + m.getId());
		System.out.println("Host: " + m.getRef().getHost());
		System.out.println("Port: " + m.getRef().getPort());
	}

	public static void main(String[] args) throws IOException {
		marshall();
		unmarshall();
	}
}
