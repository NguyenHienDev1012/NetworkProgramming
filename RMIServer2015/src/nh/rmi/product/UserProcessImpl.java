package nh.rmi.product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProcessImpl extends UnicastRemoteObject implements IUserProcess{

	private static final long serialVersionUID = 1L;
    private Connection conn;
    private User u=null;
    private ArrayList<User> listUsers;
	protected UserProcessImpl() throws RemoteException, SQLException {
		super();
		Database db=new Database();
	    conn=Database.createConnection();
	    listUsers=db.createData(conn);
	    u=new User();
		         
		
	}

	@Override
	public boolean checkUser(String name) throws RemoteException {
		
		for (User user : listUsers) {
			if(user.getName().equals(name)){
				u.setName(name);
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public boolean checkLogin( String pass) throws RemoteException {
		if(u.getName()!=null){
			for (User user : listUsers) {
				if(user.getName().equals(u.getName())&&user.getPass().equals(pass)){
					return true;
				}
			}
			u.setName(null);;
		}else{
			throw new RemoteException("Bạn phải nhập username trước!!!");
		}
		return false;
	}
  public static void main(String[] args) throws RemoteException, SQLException {
	UserProcessImpl userProcess=new UserProcessImpl();
	System.out.println(userProcess.checkUser("hien"));
}
}
