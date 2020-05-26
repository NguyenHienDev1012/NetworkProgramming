package nh.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStream2 extends Remote{
	public int openSource(String filename) throws RemoteException;
	public void write(byte [] data, int off, int lenght, int id) throws RemoteException;
	public void close(int id) throws RemoteException;
}
