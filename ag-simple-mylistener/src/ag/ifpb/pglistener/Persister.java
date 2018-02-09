package ag.ifpb.pglistener;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Persister {
	private Connection connection;
	
	public Persister() {
		try {
			this.connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.99.100:5432/db_pod_replica?"
					+ "user=postgres&password=123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public long persist(int id, String who) throws SQLException{
		String sql = "insert into tb_dados ("
				+ "id, who, created_in) "
				+ "values (?, ?, ?)";
		//
		long time = System.currentTimeMillis();
		//
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, who);
		ps.setLong(3, time);
		ps.executeUpdate();
		//
		return time;
	}
	
}
