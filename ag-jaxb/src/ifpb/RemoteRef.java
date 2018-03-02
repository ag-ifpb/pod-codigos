package ifpb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class RemoteRef implements Serializable{
	private String host;
	private int port;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
}
