package nh.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IContest extends Remote {
	public boolean register(Student student) throws RemoteException;
	public boolean validate(Date date) throws RemoteException;
	public String viewId() throws RemoteException;
	public Student viewInfo(String id) throws RemoteException;

}
