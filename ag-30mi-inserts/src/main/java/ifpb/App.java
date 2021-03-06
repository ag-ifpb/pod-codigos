package ifpb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	static Object lock = new Object();
	
	public static void main(String[] args) throws SQLException {
		//
		final PgAppender appender = new PgAppender();
		//
		int PARSECOUNT = 100000;
		int THREAD_POOL_SIZE = 10;
		ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		for (int i = 0; i < THREAD_POOL_SIZE; i++){
			final String who = "Thread#" + i;
			final int initial = PARSECOUNT*i; 
			final int total = initial + PARSECOUNT; 
			Runnable runnable = new Runnable(){
				public void run() {
					try {
						final Connection conn = appender.connect();
						//execução SINCRONA (em 29431ms)
						long t0 = System.currentTimeMillis();
						appender.insert(conn, initial, total, who);
						long t1 = System.currentTimeMillis();
						// connection
						//conn.commit();
						//.................
						System.out.println(t1 - t0);
					} catch(SQLException e){
						e.printStackTrace();
					}
				};
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
