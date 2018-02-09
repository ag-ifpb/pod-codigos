package ag.protocol;

import java.security.GeneralSecurityException;
import java.util.Base64;

import ag.protocol.util.Util;

/**
 * Representa o frame de dados que será enviado
 * ou recebido pelo cliente e servidor.
 * 
 * @author arigarcia
 *
 */
public class Frame{
	private boolean isReq;
	private boolean isText;
	private int length;
	private byte[] payload;
	private int identiy;
	
	private Frame(int id) {
		identiy = id;
		isReq = true;//0
		isText = true;//1
		length = 1;
		payload = new byte[length];
	}
	
	public int getIdentiy() {
		return identiy;
	}
	
	public boolean isReq(){
		return isReq;
	}
	
	public boolean isText() {
		return isText;
	}
	
	public int getLength() {
		return length;
	}
	
	public byte[] getPayload() {
		return payload;
	}
	
	public void dump(){
		System.out.println("-------- FRAME ----------");
		System.out.println("ID:             \t" + getIdentiy());
		System.out.println("IsReq:          \t" + isReq());
		System.out.println("IsText:         \t" + isText());
		System.out.println("Length:         \t" + getLength());
		if (isText()){
			System.out.println("Content-txt:    \t" + new String(getPayload()));
		} else {
			System.out.println("Content-bin:    \t" + Base64.getEncoder().encodeToString(getPayload()));
		}
		System.out.println("Content-hex:    \t0x" + Util.byteArrayToHexString(getPayload()));
	}
	
	private static Frame createReqOrRespFrame(int id, byte[] value, boolean isBinary, boolean isReq){
		//defining
		Frame tmp = new Frame(id);
		tmp.payload = value;
		tmp.length = value.length;
		tmp.isText = !isBinary;
		tmp.isReq = isReq;
		//result
		return tmp;
	}
	
	public static Frame createReqFrame(int id, byte[] value, boolean isBinary){
		return createReqOrRespFrame(id, value, isBinary, true);
	}
	
	public static Frame createRespFrame(int id, byte[] value, boolean isBinary){
		return createReqOrRespFrame(id, value, isBinary, false);
	}
	
}
