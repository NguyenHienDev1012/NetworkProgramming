package rmi_banking_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry r=LocateRegistry.createRegistry(1999);
		IBanking bankingImpl=new BankingImpl();
		r.rebind("bankingImpl", bankingImpl);
		System.out.println("Waitting....");
		
		
	}

}
