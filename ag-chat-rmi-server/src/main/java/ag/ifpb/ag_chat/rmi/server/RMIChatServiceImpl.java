package ag.ifpb.ag_chat.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import ag.ifpb.chat.rmi.share.ChatServer;
import ag.ifpb.chat.rmi.share.Message;
import ag.ifpb.chat.rmi.share.RMIChatService;
import ag.ifpb.chat.rmi.share.Session;

@SuppressWarnings(value = "serial")
public class RMIChatServiceImpl implements RMIChatService {
	private final ChatServer chatServer;
	private final List<Session> sessions = new ArrayList<Session>();

	private void saveSession(Session s){
		sessions.add(s);
	}
	
	protected RMIChatServiceImpl(ChatServer server) {
		this.chatServer = server;
	}

	public String login(String email) throws RemoteException{
		Session session = chatServer.login(email);
		//
		if (session != null){
			saveSession(session);//TODO: criar a sessão no banco de dados
			return session.getToken();
		} else {
			return null;
		}
	}

	public void sendMessage(String token, String text) throws RemoteException{
		//
		String user = null;
		for (Session session : sessions) {
			if (session.getToken().equalsIgnoreCase(token)){
				user = session.getUser();
			}
		}
		//
		if (user == null){
			throw new RemoteException("Sessão inválida!");
		}
		//
		Message m = new Message();
		m.setID(System.currentTimeMillis());
		m.setUser(user);
		m.setContent(text);
		//
		chatServer.persistAndforwardToAll(m);
	}

	public String[] receiveMessage(String token) throws RemoteException{
		// TODO recuperar na lista de notificações e verificar quais correspondem
		// ao meu token e devolver
		return null;
	}

}
