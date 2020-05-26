package nh.rmi.student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SearchImpl extends UnicastRemoteObject implements ISearch {
    StudentDao studentDao=new StudentDao();
    ArrayList<Student> listStudent=studentDao.loadStudents();
	protected SearchImpl() throws RemoteException {
		super();
	}

	public String findByName(String name)throws RemoteException {
		String result="";
		for (int i = 0; i < listStudent.size(); i++) {
			if(listStudent.get(i).getName().equals(name)){
				result+=listStudent.get(i).toString();
			}
		}
		return result;
	}

	public String findLessage(int age) throws RemoteException{
		String result="";
		for (int i = 0; i < listStudent.size(); i++) {
			if(listStudent.get(i).getAge()<age){
				result+=listStudent.get(i).toString();
			}
		}
		return result;
	}

	public String findLessScore(double score) throws RemoteException{
		String result="";
		for (int i = 0; i < listStudent.size(); i++) {
			if(listStudent.get(i).getScore()<score){
				result+=listStudent.get(i).toString();
			}
		}
		return result;
	}

}
