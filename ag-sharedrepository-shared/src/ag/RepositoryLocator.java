package ag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsável por encontrar todos os
 * diretórios e arquivos no repositório 
 * compartilhado
 * 
 * @author arigarcia
 *
 */
public class RepositoryLocator {
	public static final String HELO_FUNCTION = "hello";
	/**
	 * Lista dos diretórios disponíveis
	 */
	private Map<String, String> dirPaths = new HashMap<String, String>();
	/**
	 * Lista dos arquivos disponíveis
	 */
	private Map<String, String> filesPaths = new HashMap<String, String>();
	
	/**
	 * Retorna o caminho de um diretório 
	 * para uma determinada função
	 * 
	 * @param name
	 * @return
	 */
	public String function(String name){
		return dirPaths.get(name);
	}
	
	/**
	 * Retorna o nome do arquivo a partir
	 * de um tipo de mensagem
	 * 
	 * @param name
	 * @return
	 */
	public String messageType(String name){
		return filesPaths.get(name);
	}
	
	/**
	 * Registra um relacionamento entre uma 
	 * função e um diretório
	 * 
	 * @param name
	 * @param dirPath
	 */
	public void registryFunction(String name, String dirPath){
		dirPaths.put(name, dirPath);
	}
	
	/**
	 * Registra um relacionamento entre um tipo de mensagem e
	 * um arquivo
	 *  
	 * @param name
	 * @param filePath
	 */
	public void registryMessageType(String name, String filePath){
		filesPaths.put(name, filePath);
	}
	
}
