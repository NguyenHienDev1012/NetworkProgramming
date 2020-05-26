package nh.rmi.download;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDownStream extends Remote{
	
	public int openSource(String sf) throws RemoteException;
	public byte[] readData(int id) throws RemoteException;
	public void closeSource(int id,String sf) throws RemoteException;

}
