package ag;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Representa o serviço de comunicação
 * oferecido pelo Middleware
 * 
 * @author arigarcia
 *
 */
public interface CommService {
	
	/**
	 * Envia uma mensagem
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	void send(Message message) throws FileNotFoundException, IOException;
	
	/**
	 * Recebe uma mensagem
	 * 
	 * @param message
	 */
	Message recv(String dest) throws FileNotFoundException, IOException;
}
