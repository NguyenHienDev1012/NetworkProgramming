package nh.rmi.download;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public static int PORT=1999;
	public static void main(String[] args) throws RemoteException {
		Registry r=LocateRegistry.createRegistry(PORT);
		IDownStream mybis=new MyBufferedInputStream();
		r.rebind("mybis", mybis);
		System.out.println("Server's waitting"); 
	}
}
