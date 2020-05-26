package nh.rmi.product;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
	
	
  public static void main(String[] args) throws RemoteException, SQLException {
	  Registry r=LocateRegistry.createRegistry(1999);
	  IUserProcess iup=new UserProcessImpl();
	  IproductDao ipd=new ProductDaoImpl();
	  r.rebind("iup", iup);
	  r.rebind("ipd", ipd);
	  System.out.println("Server's waitting for client connect!!!");
	  
	  
	
}
}
