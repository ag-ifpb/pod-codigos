package ag.messenger.model;
import java.util.List;

public interface SharedRepository {
	void store(Message m);
	List<Message> select(int id);
}
