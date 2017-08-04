package ag.ifpb;

public interface Unmarshaller {
	int length();
	boolean hasNext();
	String message();
	Unmarshaller frame(byte[] value);
}
