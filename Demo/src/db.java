import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
	public static void main(String[] args) throws SQLException {
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch (Exception e) {
		}
		String url = "jdbc:odbc:Xe";	
		Connection c = DriverManager.getConnection(url);
		System.out.println(c);
	}

}
