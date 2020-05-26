package ltm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int PORT=1234;
	public static void main(String[] args) throws IOException {
	  ServerSocket ss=new ServerSocket(PORT);
	  System.out.println("WAITTING!!!");
	  while(true){
		  Socket sc=ss.accept();
		  ServerProcess serverProcess=new ServerProcess(sc);
		  serverProcess.start();
		  
	  }
		
	}

}
