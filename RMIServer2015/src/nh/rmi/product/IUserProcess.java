package nh.rmi.product;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserProcess extends Remote {
	public boolean checkUser(String name) throws RemoteException;
	public boolean checkLogin(String pass) throws RemoteException;
	
	

}
