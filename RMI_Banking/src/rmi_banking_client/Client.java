package rmi_banking_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;

import rmi_banking_server.Account;
import rmi_banking_server.Diary;
import rmi_banking_server.IBanking;

public class Client {
	private BufferedReader bReader;
	private String userIn="";
	private boolean isLogin=false;
	private Account account;
	
	public Client() throws NotBoundException, IOException {
		Registry r=LocateRegistry.getRegistry("127.0.0.1", 1999);
		IBanking bankingImpl=(IBanking) r.lookup("bankingImpl");
		bReader=new BufferedReader(new InputStreamReader(System.in));
		String func="Moi ban nhap chuc nang theo cu phap:\n1.Xem tien trong tai khoan(Cu phap: XEM).\n2."
				+ "Gui tien: (Cu phap: GUI|soluong).\n3.Chuyen khoan(Cu phap:CHUYEN|sotaikhoannhan|soluong).\n4.Rut tien(Cu phap: RUT|amount).\n5.Xem nhat ky(Cu phap: XEMNHATKY)";
		while(true){
			StringTokenizer st;
			while(isLogin==false){
				System.out.println("Ban hay dang nhap: (Cu phap: DN|accountNumber|pinCode)");
				userIn=bReader.readLine();
				st=new StringTokenizer(userIn,"|");
				String command=st.nextToken();
			    account=new Account(st.nextToken(), st.nextToken(),0, new ArrayList<Diary>());
				isLogin=bankingImpl.checkAccount(account);
			}
			System.out.println(func);
			try{
			userIn=bReader.readLine();
			st=new StringTokenizer(userIn,"|");
			String command=st.nextToken();
			switch (command) {
			case "XEM":
				System.out.println("So tien trong tai khoan cua ban: "+bankingImpl.getBalance(account));
				break;
			case "GUI":
				
				System.out.println("So tien cua ban co sau khi gui tien vao: "+bankingImpl.deposit(account, Double.parseDouble(st.nextToken())));
				
				break;
            case "RUT":
				
				System.out.println("So tien cua ban co sau khi rut tien: "+bankingImpl.withdraw(account, Double.parseDouble(st.nextToken())));
				
				break;
			case "CHUYEN":
	            System.out.println("So tien cua ban con lai sau khi chuyen:"+bankingImpl.transfer(account, new Account(st.nextToken()), Double.parseDouble(st.nextToken())));
	            break;
			case "XEMNHATKY":
				System.out.println(bankingImpl.report(account));
				break;
			case "DX":
				System.out.println("Hen gap lai ban lan sau!!!");
				isLogin=false;
				break;

			default:
				System.out.println("Ban nhap sai cu phap, moi nhap lai!!!");
				break;
			}
			
			}catch (RemoteException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	public static void main(String[] args) throws NotBoundException, IOException {
		new Client();

	}
}
