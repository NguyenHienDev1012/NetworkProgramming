package nh.rmi.model;

import java.util.ArrayList;

public class Data {
	public static ArrayList<User> getData(){
		ArrayList<User> listUser=new ArrayList<>();
		listUser.add(new User("system", "system123"));
		listUser.add(new User("hien", "hien1999"));
		listUser.add(new User("admin", "admin123"));
		return listUser;
	}
	
	

}
