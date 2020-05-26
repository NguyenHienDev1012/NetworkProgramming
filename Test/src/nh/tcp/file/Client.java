package nh.tcp.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {
	private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;
	private BufferedReader readUserIn;
	public static int PORT=1999;
	public static String HOST="127.0.0.1";
	public String clientDir="";
	private String userIn;
	public Client() throws UnknownHostException, IOException{
		clientDir="C:\\source";
	};
	
	public void connect() throws IOException{
		socket=new Socket(HOST,PORT);
		this.netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		this.netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		this.readUserIn=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(netIn.readUTF());
		
		StringTokenizer st;
		while(true){
			System.out.println("Moi ban nhap");
			userIn=readUserIn.readLine();
			System.out.println(userIn);
			if(userIn.equalsIgnoreCase("QUIT")){
				netOut.writeUTF("QUIT");
				netOut.flush();
				System.out.println(netIn.readUTF());
				netIn.close();
				netOut.close();
				readUserIn.close();
				break;
			}
			else{
				st=new StringTokenizer(userIn,"|");
				String key=st.nextToken();
				switch (key) {
			
			case "SEND":
				String sf=st.nextToken();
				String df=st.nextToken();
				netOut.writeUTF("SEND"+"|"+df);
				netOut.flush();
				send(sf);
				System.out.println(key);
				break;
			case "GET":
				String sfile=st.nextToken();
				String dFile=st.nextToken();
				netOut.writeUTF("GET"+"|"+sfile);
				netOut.flush();
				receive(dFile);
				break;
			default:
				System.out.println("Sai cu phap.");
				break;
			}
		}
		}
	}
	
	private void receive(String dFile) throws IOException {
		File file = new File(clientDir+File.separator+dFile);
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		byte[] buff=new byte[10*1024];
		long size=netIn.readLong();
		int byteMustRead,byteRead = 0;
		long remain=size;
		
		while(remain>0){
			byteMustRead=remain>buff.length?buff.length:(int)remain;
			byteRead=netIn.read(buff, 0, byteMustRead);
			bos.write(buff, 0, byteRead);
			remain-=byteRead;
		}
		bos.close();
	}

	private void send(String sf) throws IOException {
	File file = new File(clientDir+File.separator+sf);
    BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    byte [] buff=new byte[10*1024];
    long size=file.length();
    netOut.writeLong(size);
    netOut.flush();
    int data;
    while((data=bis.read(buff))!=-1){
    	netOut.write(buff, 0, data);
    }
    netOut.flush();
    bis.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client().connect();;
		
		
	}

}
