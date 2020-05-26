package nh.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStream extends Remote{
	public int openSource(String filename) throws RemoteException;
	public byte[] readData(byte[] data,int id) throws RemoteException;
	public void close(int id) throws RemoteException;

}
