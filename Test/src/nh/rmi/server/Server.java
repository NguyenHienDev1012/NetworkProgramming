package nh.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry r=LocateRegistry.createRegistry(1999);
		MyBufferedInputStream mybis=new MyBufferedInputStream();
		MyBufferedOutputSream mybos=new MyBufferedOutputSream();
		r.rebind("mybos", mybos);
		r.rebind("mybis", mybis);
	}

}
