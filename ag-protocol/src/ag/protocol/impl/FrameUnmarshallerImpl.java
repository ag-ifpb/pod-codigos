package ag.protocol.impl;

import java.util.Arrays;

import ag.protocol.Frame;
import ag.protocol.FrameUnmarshaller;

public class FrameUnmarshallerImpl implements FrameUnmarshaller {

	@Override
	public Frame unmarshal(byte[] f) {
		//extrair apenas os dois primeiros bits no byte
		int first = f[0];
		int two = f[1];
		//verificar se é requisição
		boolean isReq = (first & 0xC0 & 0x80) == 0x00; 
		//verificar se é tipo texto
		boolean isBinary = (first & 0xC0 & 0x40) == 0x00;
		//capturando o tamanho
		int newfirst = (first & 0x3F) << 10;
		int newtwo   = two  << 2;
		int lenght = (newfirst |  newtwo);
		lenght = lenght >> 6;
		if ((lenght & 0x8000) == 0x8000){//se negativo
			lenght = 2 + Math.abs(lenght);
		}
		//capturando o conteúdo
		byte[] content = Arrays.copyOfRange(f, 2, lenght+2);
		int id = f[f.length-1];
		System.out.println(id);
		//
		return isReq ? Frame.createReqFrame(id, content, isBinary): 
			Frame.createRespFrame(id, content, isBinary);
	}

}
