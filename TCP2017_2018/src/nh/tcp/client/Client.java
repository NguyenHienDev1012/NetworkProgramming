package nh.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {
	private Socket socket;
	private BufferedReader netIn;
	private PrintWriter netOut;
	public static String HOST="127.0.0.1";
	public static int PORT=6666;
	private BufferedReader userIn;
	private String userLine;
	private String netLine="";
	
	public Client() throws UnknownHostException, IOException{
		socket=new Socket(HOST, PORT);
		netIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		userIn=new BufferedReader(new InputStreamReader(System.in));
		
	};
	public void connect() throws IOException{
		
		netLine=netIn.readLine();
		System.out.println(netLine);
		while(true){
			userLine=userIn.readLine();
			StringTokenizer st=new StringTokenizer(userLine,"|");
			String key=st.nextToken();
			if(key.equalsIgnoreCase("QUIT")){
				netOut.println("QUIT");
				System.out.println(netIn.readLine());
				netOut.close();
				userIn.close();
				netIn.close();
				socket.close();
				break;
			}
			else{
				switch (key) {
				case "SET_FOLDER":
					netOut.println(userLine);
					System.out.println(netIn.readLine());
					break;

				case "VIEW":
					netOut.println(userLine);
					System.out.println(netIn.readLine());
					break;
				case "COPY":
					netOut.println(userLine);
					System.out.println(netIn.readLine());
					break;
				case "MOVE":
					netOut.println(userLine);
					System.out.println(netIn.readLine());
					break;
				case "RENAME":
					netOut.println(userLine);
					System.out.println(netIn.readLine());
					break;

				default:
					System.out.println("Nhap sai cu phap");
					break;
				}
				
			}
		}
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client().connect();
		
	}

}
