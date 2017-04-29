package ag.webcamfake.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ag.webcamfake.WebCamException;
import ag.webcamfake.WebCam;

public class WebCamImpl implements WebCam{
	private final String code;
	
	public WebCamImpl(String code) {
		this.code = code;
	}

	@Override
	public FileInputStream play() throws WebCamException {
		File file = new File("preview-app-iphone.mp4");
		if (file.exists()){
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new WebCamException(3001, "Erro ao tentar carregar a webcam.");
			}
		}
		return null;
	}

	@Override
	public void stop(FileInputStream finput) throws WebCamException {
		try {
			finput.close();
		}
		catch(IOException e){
			throw new WebCamException(6002, "Erro ao tentar encerrar a transmissão.");
		}
	}

}
