package ag.threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Operation {
	private final Connection conn;
	private static int id = 0;
	
	public Operation(Connection conn) {
		this.conn = conn;
	}
	
	public int getID(){
		return id;
	}
	
	public void increaseID(){
		id++;
	}

	public void insert(int id, String name) throws SQLException{
		String sql = "INSERT INTO tb(id, name, edited, deleted) VALUES (?, ?, FALSE, FALSE);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, name);
		int updated = ps.executeUpdate();
		if (updated != 1){
			throw new RuntimeException("Não conseguiu inserir ID " + id);
		}
	}
	
	public synchronized void update(int id) throws SQLException{
		String sql = "UPDATE tb SET edited = TRUE WHERE id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int updated = ps.executeUpdate();
		if (updated != 1){
			throw new RuntimeException("Não conseguiu atualizar ID " + id);
		}
	}
	
	public void delete(int id) throws SQLException{
		String sql = "UPDATE tb SET deleted = TRUE WHERE id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int updated = ps.executeUpdate();
		if (updated != 1){
			throw new RuntimeException("Não conseguiu excluir ID " + id);
		}
	}
	
	
	public static void clear(Connection conn) throws SQLException{
		String sql = "delete from tb";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	
}
