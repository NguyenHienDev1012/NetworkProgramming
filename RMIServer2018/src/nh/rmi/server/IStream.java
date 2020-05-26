package nh.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStream extends Remote{
	public void open(String fName) throws RemoteException;
	public void write(byte[] data,int offset,int len) throws RemoteException;
	public void close() throws RemoteException;
}
