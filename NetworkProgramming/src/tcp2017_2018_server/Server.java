package tcp2017_2018_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
    public static int PORT = 1999;
    private ServerSocket serversocket;
    private Socket socket;
    
    public void start() throws IOException{
    	serversocket=new ServerSocket(PORT);
    	while(true){
    		socket=serversocket.accept();
    		ServerProcess serverProcess=new ServerProcess(socket);
    		serverProcess.start();
    	}
    	
    }
    
	public static void main(String[] args) throws IOException {
           new Server().start();		
	}
}
