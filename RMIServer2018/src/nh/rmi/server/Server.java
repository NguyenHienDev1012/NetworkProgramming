package nh.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry r = LocateRegistry.createRegistry(1999);
		IContest contestImpl=new ContestImpl();
		IStream os=new MyBufferedOutputStream();
		r.rebind("contestImpl", contestImpl);
		r.rebind("os", os);
        System.out.println("Waiting");		
	}

}
