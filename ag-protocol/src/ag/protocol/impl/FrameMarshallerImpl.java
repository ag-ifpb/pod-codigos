package ag.protocol.impl;

import java.nio.ByteBuffer;
import java.util.Arrays;

import ag.protocol.Frame;
import ag.protocol.FrameMarshaller;

public class FrameMarshallerImpl implements FrameMarshaller {

	@Override
	public byte[] marshal(Frame frame) {
		//colocar o valor no primeiro bit do byte
		int first = 0x00;
		if (!frame.isReq()){//0 - req e 1 - resp
			first = 0x80; // 1000 0000
		}
		//colocar o valor no segundo bit do byte
		if (frame.isText()){//0 - b e 1 - t
			// 0100 0000 ->  0x40
			//operação OR com os primeiros bits
			first = 0x40 | first; //
		}
		//
		//colocar o valor no segundo byte
		int length = frame.getLength();
		length = (first << 8) | (length << 4);
		//armazenar no buffer
		int capacity = frame.getLength()+8;
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		buffer.putInt(length);
		buffer.put(frame.getPayload());
		buffer.putInt(frame.getIdentiy());
		//retirar os bytes adicionados pelo tipo inteiro
		return Arrays.copyOfRange(buffer.array(), 2, capacity);
	}

}
