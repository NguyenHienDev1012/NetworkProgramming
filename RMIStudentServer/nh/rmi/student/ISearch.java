package nh.rmi.student;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISearch extends Remote{
	public String findByName(String name) throws RemoteException;
	public String findLessage(int age) throws RemoteException;
	public String findLessScore(double score) throws RemoteException;
	
	
	
	

}
