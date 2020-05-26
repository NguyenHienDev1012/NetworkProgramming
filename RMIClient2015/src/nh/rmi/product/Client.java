package nh.rmi.product;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Client {
	public static int PORT=1999;
    private BufferedReader bRead;
    private PrintWriter pr;
    private String userLine="";
    private StringTokenizer st;
	private boolean isLogin=false;
	private User u;
	
	
	
	public Client() throws RemoteException, NotBoundException{
		Registry r=LocateRegistry.getRegistry(PORT);
		IUserProcess iup=(IUserProcess)r.lookup("iup");
		IproductDao ipd=(IproductDao)r.lookup("ipd");
		bRead=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME TO MANAGER PRODUCT SYSTEM");
		String funct="1.USER\ttendangnhap\n2.PASS\t matkhau\n3.EXIT";
		boolean kt= true;
	     a:	while(kt){
			String command="";
			try {
				while(isLogin==false){
					System.out.println(funct);
					userLine=bRead.readLine();
					u=new User();
					st=new StringTokenizer(userLine,"\t");
					 command=st.nextToken().toUpperCase();
					switch (command) {
					case "USER":
						String name=st.nextToken();
						boolean checkName=iup.checkUser(name);
						if(checkName){
							u.setName(name);
						}else{
							System.out.println("Username 's not exists. Please type again!");
						}
						break;
                    case "PASS":
						String pass=st.nextToken();
						boolean checkLogin=iup.checkLogin(pass);
						if(checkLogin){
							u.setPass(pass);
							System.out.println("Login success");
							isLogin=true;
						}
						else{
							System.out.println("Password's incorrect. Login fail!");
						}
						break;
                    case "EXIT":
                    	System.out.println("See you later!");
                    	kt=false;
						continue a;

					default:
						System.out.println("Error.Please type again");
						break;
					}
					
				}
					String option="1.ADD\ttensanpham\tsoluong\tgiaban\n2.REMOVE\tidsp\n3.EDIT\tidsp\ttensp\tsoluong\tgiaban\n4.VIEW\ttensanpham\n5.\tQUIT";
					System.out.println(option);
					userLine=bRead.readLine();
					st=new StringTokenizer(userLine,"\t");
					 command=st.nextToken().toUpperCase();
					switch (command) {
					case "ADD":
						System.out.println(ipd.addProduct(new Product(st.nextToken(), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()))));
						break;
					case "REMOVE":
						ArrayList<Integer> listId=new ArrayList<>();
						while(st.hasMoreTokens()){
							listId.add(Integer.parseInt(st.nextToken()));
						}
						for (int i = 0; i < listId.size(); i++) {
							System.out.println(ipd.removeProduct(listId.get(i)));
						}
						break;
					case "EDIT":
						System.out.println(ipd.editProduct(Integer.parseInt(st.nextToken()), new Product(st.nextToken(), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()))));
						break;
					case "VIEW":
						System.out.println(ipd.viewProduct(st.nextToken()));
						break;
					case "QUIT":
						isLogin=false;
						break;

					default:
						System.out.println("Error.Please type again");
						break;
					}
					
					
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		
		 
		
		
	}
	public static void main(String[] args) throws RemoteException, NotBoundException {
		new Client();
	}

}
