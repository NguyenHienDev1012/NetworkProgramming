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
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;
	private String serverDir="";
 	
	public ServerProcess(Socket socket) throws IOException{
		serverDir="C:\\dest";
		this.socket=socket;
		this.netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		this.netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		netOut.writeUTF("WELCOME YOU COME HERE!!!");
		netOut.flush();
	}
	@SuppressWarnings("unused")
	@Override
	public void run() {
		StringTokenizer st;
		String userInNet;
		while(true){
			try {
				userInNet=netIn.readUTF();
				System.out.println(userInNet+"net");
				if(userInNet.equalsIgnoreCase("QUIT")){
					netOut.writeUTF("Tam biet");
					netOut.flush();
					netOut.close();
					netIn.close();
					socket.close();
					break;
				}
				else{
				st=new StringTokenizer(userInNet,"|");
				String key=st.nextToken();
				switch (key) {
				case "SEND":
					String dFile=st.nextToken();
					receive(dFile);
					break;
				case "GET":
					String sFile=st.nextToken();
					send(sFile);
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
	private void send(String sFile) throws IOException {
		File file = new File(serverDir+File.separator+sFile);
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
	private void receive(String dFile) throws IOException {
		File file = new File(serverDir+File.separator+dFile);
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
}
