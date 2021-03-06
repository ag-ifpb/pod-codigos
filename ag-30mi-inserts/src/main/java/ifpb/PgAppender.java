package ifpb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.postgresql.Driver;

public class PgAppender {
	Object lock = new Object();
	int ident = 0;

	public Connection connect() throws SQLException{
		System.out.println(Driver.getVersion());
		return DriverManager.getConnection("jdbc:postgresql://192.168.99.100/db_pod", "postgres", "123456");
	}
	
	public int next(){
		synchronized (lock) {
			return ident++;//
		}
	}
	
	public void insert(Connection conn, int initial, int total, String who) throws SQLException{
		//
		String sql = "INSERT INTO tb_dados(id, who, created_in) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//
		long t0 = System.currentTimeMillis();
		for (int i = initial; i < total; i++){
			ps.setInt(1, next());//<----não repetir
			ps.setString(2, who);
			ps.setLong(3, System.currentTimeMillis());
			ps.addBatch();
		}
		long t1 = System.currentTimeMillis();
		System.out.println("#0" + (t1 - t0));
		ps.executeBatch();
		long t2 = System.currentTimeMillis();
		System.out.println("#1" + (t2 - t1));
	}
}
