package tcp2017_2018_server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
    private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;
	private String netUser;
	private StringTokenizer st;
	private IBanking ibanking;
	private Account acc;
	private PrintWriter pw;
	public ServerProcess(Socket socket) throws IOException{
		this.socket=socket;
		netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		ibanking=new BankingImpl();
	}
	
	@Override
	public void run() {
		try {
			while(true){
				netUser=netIn.readUTF();
				System.out.println(netUser);
				st=new StringTokenizer(netUser,"|");
				String key=st.nextToken();
				switch (key) {
				case "DN":
					 acc=new Account(st.nextToken(), st.nextToken(), 0, new ArrayList<>());
					boolean checkLogin=ibanking.checkAccount(acc);
					if(checkLogin){
						netOut.writeUTF("True");
						netOut.flush();
					}else{
						netOut.writeUTF("False");
						netOut.flush();
						
					}
					
					break;
				case "XEM":
					netOut.writeUTF("So tien cua ban hien co:"+ibanking.getBalance(acc));
					netOut.flush();
					break;
				case "GUI":
					netOut.writeUTF("So tien cua ban sau khi gui them"+ibanking.deposit(acc,Double.parseDouble(st.nextToken())));
					netOut.flush();
					break;
				case "CHUYEN":
					Account account=new Account(st.nextToken());
					netOut.writeUTF("So tien con lai sau khi chuyen"+ibanking.transfer(acc, account, Double.parseDouble(st.nextToken())));
					netOut.flush();
					break;

				case "RUT":
					netOut.writeUTF("So tien cua ban sau khi rut"+ibanking.withdraw(acc, Double.parseDouble(st.nextToken())));
					netOut.flush();
					break;
				case "XEMNHATKY":
					netOut.writeUTF("Nhat ky: "+ibanking.report(acc));
					netOut.flush();
					break;
				case "QUIT":
					netOut.writeUTF("See you later!");
					netOut.flush();
				
					break;
				
					
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
