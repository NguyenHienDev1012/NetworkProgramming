package ltm2008_01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int PORT = 12345;
	private ServerSocket serverSocket;
	private Socket socket;
	public void start() throws IOException{
		 serverSocket=new ServerSocket(PORT);
		
		while(true){
			socket=serverSocket.accept();
			ServerProcess serverProcess=new ServerProcess(socket);
			serverProcess.start();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Server server=new Server();
		server.start();
		
	}
	
}
