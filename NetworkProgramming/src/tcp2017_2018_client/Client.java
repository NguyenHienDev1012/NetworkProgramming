package tcp2017_2018_client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import tcp2017_2018_server.Account;

public class Client {
	public static String HOST="127.0.0.1";
	public static int PORT=1999;
	private Socket socket;
	private BufferedReader bReader;
	private PrintWriter pr;
	private String userIn;
	private StringTokenizer st;
	private Account acc;
	private boolean isLogin=false;
	private DataOutputStream netOut;
	private DataInputStream netIn;
	
	public void connect(){
		
		try {
			socket=new Socket(HOST, PORT);
			bReader=new BufferedReader(new InputStreamReader(System.in));
			netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
		
		 while(isLogin==false){
			System.out.println("Ban hay dang nhap: (Cu phap: DN|accountNumber|pinCode)");
			userIn=bReader.readLine();
			st=new StringTokenizer(userIn,"|");
			
			String command=st.nextToken().toUpperCase();
			switch (command) {
			case "DN":
				acc=new Account(st.nextToken(), st.nextToken(), 0, new ArrayList<>());
				netOut.writeUTF(userIn);
				netOut.flush();
				if(netIn.readUTF().equalsIgnoreCase("true")) isLogin=true;
				break;
			default:
				System.out.println("Nhap sai cu phap!");
				break;
			}
			
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(isLogin==true){
			String func="Moi ban nhap chuc nang theo cu phap:\n1.Xem tien trong tai khoan(Cu phap: XEM).\n2."
					+ "Gui tien: (Cu phap: GUI|soluong).\n3.Chuyen khoan(Cu phap:CHUYEN|sotaikhoannhan|soluong).\n4.Rut tien(Cu phap: RUT|amount).\n5.Xem nhat ky(Cu phap: XEMNHATKY)";
			System.out.println(func);
			try {
				userIn=bReader.readLine();
				st=new StringTokenizer(userIn,"|");
				String command=st.nextToken();
				switch (command) {
				case "XEM":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					break;
				case "GUI":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					break;
				case "CHUYEN":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					break;

				case "RUT":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					break;
				case "XEMNHATKY":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					break;
				case "QUIT":
					netOut.writeUTF(userIn);
					netOut.flush();
					System.out.println(netIn.readUTF());
					isLogin=false;
					break;
				default:
					break;
				}
				netOut.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		Client client=new Client();
		client.connect();
		
		
	}
}
