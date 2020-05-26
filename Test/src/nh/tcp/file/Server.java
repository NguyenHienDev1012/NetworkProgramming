package nh.tcp.file;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static ServerSocket ss;
	public static Socket socket;
	public static int PORT=1999;
	
	public Server() throws IOException{
		ss=new ServerSocket(PORT);
		System.out.println("Waiting for connect!");
		while(true){
			socket=ss.accept();
			@SuppressWarnings("unused")
			ServerProcess serverProcess=new ServerProcess(socket);
			serverProcess.start();
			
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		new Server();
		
	}

}
