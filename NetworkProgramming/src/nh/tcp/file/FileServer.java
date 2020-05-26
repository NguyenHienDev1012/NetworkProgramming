package nh.tcp.file;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	
	public static final int PORT=1999;
	ServerSocket serverSocket;
	Socket socket;
	
	public void start(){
		try {
			serverSocket = new ServerSocket(PORT);
			while(true){
				socket=serverSocket.accept();
				FileServerProcess fileServerProcess=new FileServerProcess(socket);
				fileServerProcess.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileServer fileServer=new FileServer();
		fileServer.start();
		
	}

}
