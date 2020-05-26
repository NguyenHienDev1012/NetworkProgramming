package nh.rmi.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	 static ArrayList<User> listUsers;
	 static ArrayList<Product> listProducts;
	
	public static Connection createConnection() throws SQLException{
		Connection conn=null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url="jdbc:odbc:rmi";
		    conn=DriverManager.getConnection(url);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	public static ArrayList<User> createData(Connection conn){
		listUsers=new ArrayList<>();
		String sql="select * from user";
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				User user=new User(rs.getString(2), rs.getString(3));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUsers;
		
	}
	public static ArrayList<Product> loadProducts(Connection conn){
		listProducts=new ArrayList<>();
		String sql="select * from product";
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Product p =new Product(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getDouble(4));
				listProducts.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProducts;
		
	}
	public static void main(String[] args) throws SQLException {
		Database db=new Database();
		Connection conn=db.createConnection();
		System.out.println(db.loadProducts(conn));
	}

}
