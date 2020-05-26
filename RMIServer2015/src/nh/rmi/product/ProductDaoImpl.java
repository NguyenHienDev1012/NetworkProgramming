package nh.rmi.product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDaoImpl  extends UnicastRemoteObject implements IproductDao{
    private ArrayList<Product>listProducts;
    private Connection conn;
	private static final long serialVersionUID = 1L;

	protected ProductDaoImpl() throws RemoteException, SQLException {
		super();
		conn=Database.createConnection();
		listProducts=Database.loadProducts(conn);
		
		
	}

	@Override
	public String addProduct(Product p) throws RemoteException {
		String result="";
		String sql="insert into  product (name,quantity,price) values(?,?,?)";
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, p.getName());
			ptmt.setInt(2, p.getQuantity());
			ptmt.setDouble(3, p.getPrice());
			int kt=ptmt.executeUpdate();
			
			if(kt!=-1){
				result="OK";
			}else
			{
				result="ERRO";
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public String removeProduct(int id) throws RemoteException {
		listProducts=Database.loadProducts(conn);
		String result="";
		int count=0;
		for (int i = 0; i < listProducts.size(); i++) {
			if(listProducts.get(i).getId()==id){
				listProducts.remove(i);
				count++;
			}
			
		}
		result=count+"";
		return result;
	}

	@Override
	public String editProduct(int id, Product p) throws RemoteException {
		listProducts=Database.loadProducts(conn);
		String result="";
		for (int i = 0; i < listProducts.size(); i++) {
			Product product=listProducts.get(i);
			if(product.getId()==id){
				product.setName(p.getName());
				product.setPrice(p.getPrice());
				product.setQuantity(p.getQuantity());
				result="OK";
				return result;
			}
			else{
				result="CAN NOT UPDATE!";
			}
			
		}
		return result;
	}

	@Override
	public String viewProduct(String name) throws RemoteException {
		listProducts=Database.loadProducts(conn);
		String result="";
		ArrayList<Product> list=new ArrayList<>();
		for (int i = 0; i < listProducts.size(); i++) {
			Product product=listProducts.get(i);
			if(product.getName().equals(name)){
				list.add(product);
			}
			result=list.toString();
		}
		return result;
	}
public static void main(String[] args) throws RemoteException, SQLException {
	ProductDaoImpl p =new ProductDaoImpl();
	System.out.println(p.addProduct(new Product(0, "bbb", 1, 5000)));
}
}
