package nh.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static ServerSocket ss;
	public static Socket sc;
	public static int PORT = 6666;
	
	public static void main(String[] args) throws IOException {
		ss=new ServerSocket(PORT);
		while(true){
			sc=ss.accept();
			ServerProcess sProcess=new ServerProcess(sc);
			sProcess.start();
			
		}
		
	}

}
