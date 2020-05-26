package ltm2008_01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dao {
	
	public static ArrayList<User> users =new ArrayList<>();
	private User user;
	public void napDuLieuBanDau() throws IOException{
		File file=new File("");
		BufferedReader bReader=new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()+"\\user.txt")));
		
		String lineData="";
		while((lineData=bReader.readLine())!=null){
			String array[] =lineData.split("\t");
			User user = new User(array[0], array[1], array[2], Double.parseDouble(array[3]));
			users.add(user);
		}
		bReader.close();
	}
	
	public boolean checkName(String name) throws IOException{
		System.out.println(users.toString());
		for (User u : users) {
			if(u.getName().equals(name)){
				user=new User(name, null, null, 0);
				return true;
			}
		}
		this.user=null;
		return false;
	}
	
	public boolean checkPass(String pass) throws RuntimeException{
		if(user==null){
			throw new RuntimeException("Please type your username: TEN	tennguoidung ");
		}
		for (User u : users) {
			if(u.getName().equals(user.getName())&& u.getPass().equals(pass)){
				user.setPass(pass);
				return true;
			}
			
		}
		
		return false;
		
	}
    public boolean sendMoney(double amount){
    	for (User u : users) {
			if(u.getName().equals(user.getName())&&u.getPass().equals(user.getPass())){
				u.setMoney(u.getMoney()+amount);
				return true;
			}
		}
		return false;
	
    }
	public boolean transferMoney(String accountNumber, double amount){
		for (User u : users) {
			
			if(u.getName().equals(user.getName())&&u.getPass().equals(user.getPass())){
				u.setMoney(u.getMoney()-amount);
				if(u.getAccountNumber().equals(accountNumber)){
					u.setMoney(u.getMoney()+amount);
				}
				return true;
			}
		}
		return false;
		
	}
	public boolean receiveMoney(double amount){
		for (User u : users) {
			if(u.getName().equals(user.getName())&&u.getPass().equals(user.getPass())){
				u.setMoney(u.getMoney()-amount);
				return true;
			}
		}
		return false;	
		
	}
	public User getUser(){
		for (User u : users) {
		if(u.getName().equals(user.getName())&&u.getPass().equals(user.getPass())){
			return u;
		 }
		}
		return null;
		
	}
	public static void main(String[] args) throws IOException {
		Dao d =new Dao();
		//System.out.println(users.toString());
		System.out.println(d.checkName("Nvan"));
	}

}
