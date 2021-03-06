package ag;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável ler e escrever mensagens
 * no repositório. Além disso deve excluir 
 * todas as mensagens que foram entregues.
 * 
 * Relacionar as mensagens de requisição e
 * de resposta.
 * 
 * Obs.: criar um arquivo .lock antes de 
 * ler/escrever o arquivo de mensagens.
 * 
 * @author arigarcia
 *
 */
public class MessageManager {
	private final RepositoryLocator locator;
	
	private List<String> read(File file) throws FileNotFoundException, IOException{
		FileInputStream input = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader bufferedReader = new BufferedReader(reader);
		//
		List<String> result = new ArrayList<>();
		//
		String line;
		while((line = bufferedReader.readLine()) != null){
			result.add(line);
		}
		//
		bufferedReader.close();
		reader.close();
		input.close();
		//
		return result;
	}
	
	private void write(File file, String msg) throws FileNotFoundException, IOException{
		FileOutputStream output = new FileOutputStream(file, true);
		String formatted_msg = msg + "\r\n"; 
		output.write(formatted_msg.getBytes());
		output.close();
	}
	
	private void remove(File file, String msg) throws FileNotFoundException, IOException{
		//criando um canal de leitura e escrita (em memória)
		FileInputStream input = new FileInputStream(file);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		//recuperar o buffer para leitura linha-a-linha
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader bufferedReader = new BufferedReader(reader);
		//fazer a leitura de cada linha
		String line;
		while ((line = bufferedReader.readLine()) != null){
			//verifica se a linha corresponde a mensage
			if (line.equals(msg)){
				continue;//desprezar, caso corresponda
			}
			//escreve na memória a linha
			String remsg = line + "\r\n";
			output.write(remsg.getBytes());
		}
		//encerrar o canal de leitura do arquivo
		reader.close();
		input.close();
		//abrir o canal de escrita do arquivo
		FileOutputStream foutput = new FileOutputStream(file);
		//escrever no arquivo o que estava em memória
		output.writeTo(foutput);
		//encerrar a memória
		output.close();
		//encerrar o canal de escrita do arquivo
		foutput.close();
	}
	
	private String convert(Message msg){
		StringBuilder sb = new StringBuilder();
		sb.append(msg.getIdentity()).append("|");
		sb.append(msg.getSequence()).append("|");
		sb.append(msg.getOriginalSequence()).append("|");
		sb.append(msg.getTo()).append("|");
		sb.append(msg.getFrom()).append("|");
		sb.append(msg.getMsg());
		return sb.toString();
	}
	
	private Message convert(String msg){
		String[] fields = msg.split("\\|");
		Message obj = new Message();
		obj.setIdentity(fields[0]);
		obj.setSequence(Integer.parseInt(fields[1]));
		obj.setOriginalSequence(Integer.parseInt(fields[2]));
		obj.setTo(fields[3]);
		obj.setFrom(fields[4]);
		obj.setMsg(fields[5]);
		return obj;
	}
	
	public MessageManager(RepositoryLocator locator){
		this.locator = locator;
	}
	
	public Message read(String functionName, String messageTypeName, String to) throws FileNotFoundException, IOException{
		//recuperar os parâmetros da localização
		String dir = locator.function(functionName);
		String fil = locator.messageType(messageTypeName);
		//carregar e ler conteúdo do arquivo
		File file = new File(dir, fil);
		List<String> msgs = read(file);
		for (String m : msgs){
			Message message = convert(m);
			if (message.getTo().equals(to)) {
				remove(file, m);
				return message;
			}
		}
		//result
		return null;
	}
	
	public void write(String functionName, String messageTypeName, Message message) throws FileNotFoundException, IOException{
		//recuperar os parâmetros da localização
		String dir = locator.function(functionName);
		String fil = locator.messageType(messageTypeName);
		//carregar e ler conteúdo do arquivo
		System.out.println("-----------> " + dir + "/" + fil);
		File file = new File(dir, fil);
		String msg = convert(message);
		write(file, msg);
	}
	
	
	
		
}
