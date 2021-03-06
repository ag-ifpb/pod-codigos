import ag.protocol.Frame;
import ag.protocol.FrameMarshaller;
import ag.protocol.FrameUnmarshaller;
import ag.protocol.impl.FrameMarshallerImpl;
import ag.protocol.impl.FrameUnmarshallerImpl;
import ag.protocol.util.Util;

public class FrameTest {

	public static void main(String[] args) {
		//
		Frame frame = Frame.createReqFrame(1, "HELO GUYS".getBytes(), false);
		frame.dump();
		//
		FrameMarshaller marshaller = new FrameMarshallerImpl();
		byte[] serialized = marshaller.marshal(frame);
		//imprimir
		System.out.println("-------- STREAM ----------");
		System.out.println("Frame:       \t0x" + Util.byteArrayToHexString(serialized));
		//
		FrameUnmarshaller unmarshaller = new FrameUnmarshallerImpl();
		Frame result = unmarshaller.unmarshal(serialized);
		result.dump();
	}
}
