package ag.ifpb.eventbus;

import java.io.Serializable;

/**
 * Data Transfer Object - DTO
 * Representa o evento e os dados
 * que o evento carrega
 * 
 * @author arigarcia
 *
 */
public class Event implements Serializable{
	private String name;
	private Serializable data;
	
	public Event() {
		// usado para seria lizar na JVM
	}
	
	//eventos são imutáveis
	public Event(String name, Serializable data){
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public Serializable getData() {
		return data;
	}
	
}
