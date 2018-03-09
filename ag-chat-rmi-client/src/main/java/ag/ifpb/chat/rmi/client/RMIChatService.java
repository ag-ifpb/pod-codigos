package ag.ifpb.chat.rmi.client;

public interface RMIChatService {
	
	/**
	 * Faz o login no server e retorna o token da sessão
	 * 
	 * @param email
	 * @return
	 */
	String login(String email);
	
	
	/**
	 * Enviar um token para identificar a sessão do usuário
	 * e a própria mensagem
	 * 
	 * @param token
	 * @param text
	 */
	void sendMessage(String token, String text);
	
	/**
	 * Receber uma mensagem a partir a identificação da sessão do usuário
	 * 
	 * @param token
	 * @return um vetor contendo o email do usuário e o texto da mensagem, 
	 * nesta sequência
	 */
	String[] receiveMessage(String token);
	
}
