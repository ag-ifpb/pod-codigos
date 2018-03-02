package ifpb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message implements Serializable {
	private int id;
	private RemoteRef ref;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RemoteRef getRef() {
		return ref;
	}
	public void setRef(RemoteRef ref) {
		this.ref = ref;
	}
}
