package ag;

/**
 * Representa a mensagem identificada, 
 * endereçada e sequencializada
 * 
 * @author arigarcia
 *
 */
public class Message {
	/**
	 * Sequência da mensagem para cada componente que requisita.
	 */
	private int sequence;
	/**
	 * Sequência da mensagem que originou esta, caso exista
	 */
	private int originalSequence = -1;
	/**
	 * Identificação da mensagem no requisitor
	 */
	private String identity;
	/**
	 * Identificação do componente que irá receber a mensagem
	 * (destinatário/consumidor/publicador/servidor)
	 */
	private String to;
	/**
	 * Identificação do componente que irá enviar a mensagem
	 * (remetente/produtor/inscritor/cliente)
	 */
	private String from;
	
	/**
	 * Valor da mensagem enviada na requisição ou resposta
	 */
	private String msg;
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public void setOriginalSequence(int originalSequence) {
		this.originalSequence = originalSequence;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}

	public int getSequence() {
		return sequence;
	}

	public int getOriginalSequence() {
		return originalSequence;
	}

	public String getIdentity() {
		return identity;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
