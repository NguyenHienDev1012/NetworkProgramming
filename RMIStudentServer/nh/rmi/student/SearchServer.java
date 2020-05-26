package nh.rmi.student;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SearchServer {
	public static void main(String[] args) throws RemoteException {
		Registry reg=LocateRegistry.createRegistry(1999);
		SearchImpl searchImpl=new SearchImpl();
		reg.rebind("searchImpl", searchImpl);
		
	}

}
