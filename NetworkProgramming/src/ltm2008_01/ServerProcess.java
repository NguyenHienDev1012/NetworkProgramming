package ltm2008_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ServerProcess extends Thread {
	private Socket socket;
	private BufferedReader bReader;
	private PrintWriter pWriter;
	private String userIn="";
	private Dao dao=new Dao();
	private boolean isLogin=false;
	public ServerProcess(Socket socket) throws UnsupportedEncodingException, IOException{
		dao.napDuLieuBanDau();
		this.socket=socket;
		this.bReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pWriter=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
		pWriter.println("Welcome to NLU e-Bank.");
	}
	@Override
	public void run() {
		pWriter.println("Please type your username: TEN	tennguoidung ");
		while(true){
			try {
				userIn=bReader.readLine();
				if(userIn.equalsIgnoreCase("QUIT")){
					pWriter.println("See you again!");
					break;
				}
				analysis(userIn);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void analysis(String userIn) throws IOException  {
		if(!isLogin){
		String array[]=userIn.split("\t");
		String command=array[0];
		switch (command) {
		case "TEN":
			String name=null;
			try {
				 name=array[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				pWriter.println("Please type adequate columns!");
			}
			boolean checkName=dao.checkName(name);
			if(checkName){
				pWriter.println("Your name's correct... Please type your password: MATKHAU	matkhau");
			}
			else{
				pWriter.println("Your's name incorrect..");
			}
			break;
		case "MATKHAU":
			try {
				boolean checkPass=dao.checkPass(array[1]);
				if(checkPass){
					isLogin=true;
					pWriter.println("Your's password correct..");
					pWriter.println("Please choose option you want:");
					pWriter.println("----------------------------------");
					pWriter.println("GUI	sotien");
					pWriter.println("LAY	sotien");
					pWriter.println("CHUYEN	sotaikhoanchuyenden	sotien");
					pWriter.println("----------------------------------");
				}
				else{
					pWriter.println("Your's password incorrect.Please type your password: MATKHAU	matkhau");
				}
			 } catch (RuntimeException e) {
				pWriter.println(e.getMessage());
			
		}
			
			break;
			
		default:
			pWriter.println("Incorrect format!");
			break;
		}
		
	}	
		else{
			String array[]=userIn.split("\t");
			String command=array[0];
			switch (command) {
			case "GUI":
				double amountSend=Double.parseDouble(array[1]);
				boolean checkSend=dao.sendMoney(amountSend);;
				if(checkSend){
				   pWriter.println("Send money success...");
				   pWriter.println("View results:"+dao.getUser());
			    }
				else{
				   pWriter.println("Send money failed.Please try again!");
				}
				break;
            case "LAY":
            	double amountReceive=Double.parseDouble(array[1]);
				boolean checkReceive=dao.receiveMoney(amountReceive);
				if(checkReceive){
					 pWriter.println("Receive money success...");
					 pWriter.println("View results:"+dao.getUser());
				}
				else{
					  pWriter.println("Receive money failed.Please try again!");
				}
				break;
            case "CHUYEN":
				String accountNumber=array[1];
				double amountTransfer=Double.parseDouble(array[2]);
				boolean checkTransfer=dao.transferMoney(accountNumber, amountTransfer);
				if(checkTransfer){
					 pWriter.println("Transfer money success...");
					 pWriter.println("View results:"+dao.getUser());
				}
				else{
					  pWriter.println("Transfer money failed.Please try again!");
				}
				break;

			default:
				pWriter.println("Incorrect format!");
				break;
			}
			
		}
	}

}
