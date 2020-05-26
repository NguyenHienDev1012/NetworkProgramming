package nh.rmi.product;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IproductDao extends Remote {
	public String addProduct(Product p) throws RemoteException;
	public String removeProduct(int id) throws RemoteException;
	public String editProduct(int id, Product p) throws RemoteException;
	public String viewProduct(String name) throws RemoteException;
	

}
