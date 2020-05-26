package tcp2017_2018_server;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Account> listAccounts;
	public Data(){
	}
	public ArrayList<Account> createData(){
		listAccounts=new ArrayList<>();
		Account account1=new Account("1234", "1234",2000000, new ArrayList<Diary>());
		Account account2=new Account("5678", "5678",1000000, new ArrayList<Diary>());
		Account account3=new Account("6666", "6666",230000, new ArrayList<Diary>());
		Account account4=new Account("7777", "7777",140000, new ArrayList<Diary>());
		Account account5=new Account("8888", "8888",90000, new ArrayList<Diary>());
		listAccounts.add(account1);
		listAccounts.add(account2);
		listAccounts.add(account3);
		listAccounts.add(account4);
		listAccounts.add(account5);
		return listAccounts;
	}
	

}
