package ag.threads;

import java.sql.SQLException;

public class InsertOperation implements Runnable {
	private final int id;
	private final Operation op;
	private final BlockingQueue monitor;
	
	public InsertOperation(int id, Operation op, BlockingQueue se) {
		this.op = op;
		this.monitor = se;
		this.id = id;
	}
	
	public void run() {
		//como garantir um ID único???
		try {
			//operar
			op.insert(id, "Ari " + id);
			System.out.println("2:inserindo " + id);
			//notificar
			monitor.enqueue();
			System.out.println("3:notificando t1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
