package ag.ifpb;

public interface Marshaller {
	Marshaller length(int value);
	Marshaller hasNext(boolean value);
	Marshaller message(String value);
	byte[] build();
}
