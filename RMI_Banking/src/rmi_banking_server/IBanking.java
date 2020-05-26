package rmi_banking_server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBanking extends Remote {
	public double getBalance(Account account) throws RemoteException;
	public double  withdraw(Account account, double amount) throws RemoteException;
	public double deposit(Account account, double amount) throws RemoteException;
	public double transfer(Account fromAccount, Account toAccount, double amount) throws RemoteException;
	public String report(Account account) throws RemoteException;
	public boolean checkAccount(Account account) throws RemoteException;

}
