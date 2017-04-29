package ag.proxyserver;
import java.util.ArrayList;
import java.util.List;

public class CodeRepository {
	private static final List<String> list = new ArrayList<>();
	
	static {
		add("TST 000000-00");
	}
	
	public static void add(String code){
		list.add(code);
	}
	
	public static boolean exists(String code){
		for (String c : list) {
			if (c.equals(code)) return true;
		}
		return false;
	}
			
}
