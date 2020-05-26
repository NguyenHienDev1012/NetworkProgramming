package nh.rmi.file;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nh.rmi.model.Data;
import nh.rmi.model.User;

public class FileImpl extends UnicastRemoteObject implements IFile {

	ArrayList<User> data;
	User user;
	protected FileImpl() throws RemoteException {
		super();
		data=Data.getData();
		user=null;
	}

	@Override
	public boolean findName(String name) throws RemoteException {
		for (User u : data) {
			if(u.getName().equals(name)){
			this.user=new User(name, null)	;
			}
		}
		this.user=null;
		return false;
	}

	@Override
	public boolean checkPass(String pass) throws RemoteException {
		if(this.user==null){
			throw new RemoteException("Bạn chưa nhập tên, vui lòng nhập tên theo cú pháp: TEN ten_nguoi_dung.");
		}
		for (User u : data) {
			if(user.getName().equals(u.getName())&&u.getPass().equals(pass)){
				user.setPass(pass);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void createDest(String df) throws RemoteException {
		
	}

	@Override
	public void writeData() throws RemoteException {
		
	}

	@Override
	public void closeDest() throws RemoteException {
		
	}

	@Override
	public void openSource(String sf) throws RemoteException {
		
	}

	@Override
	public byte[] readData() throws RemoteException {
		return null;
	}

	@Override
	public void closeSource() throws RemoteException {
		
	}

}
