package ag.ifpb;

import java.util.Arrays;

public class UnmarshallerImpl implements Unmarshaller {
	private byte[] frame = null;

	@Override
	public int length() {
		//multiplicador
		int f = frame[0];
		int s = frame[1];
		//
		// 0 0 0 0 0 1 0 1 --> f
		// 0 0 0 0 0 0 1 1 --> comparador
		int mlt = f & 0x3;
		int tam = s;
		//
		return (mlt*256) + tam + 1;
	}

	@Override
	public boolean hasNext() {
		//
		if (frame == null) throw new RuntimeException(
				"Defina o frame antes de solicitar a continuidade.");
		//
		byte f = frame[0];
		// 0 0 0 0 0 0 0
		// 0 0 0 0 1 0 0
		// 0 0 0 0 1 0 0 <-- combinador
		return ((f & 0x4) == 4);
	}

	@Override
	public String message() {
		if (frame == null) throw new RuntimeException(
				"Defina o frame antes de solicitar a mensagem.");
		//
		int l = length();
		byte[] m = Arrays.copyOfRange(frame, 2, l+2);
		//
		return new String(m);
	}

	@Override
	public Unmarshaller frame(byte[] value) {
		frame = value;
		return this;
	}

}
