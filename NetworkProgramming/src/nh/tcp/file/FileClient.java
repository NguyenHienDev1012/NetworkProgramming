package nh.tcp.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.NoSuchElementException;

public class FileClient {
	public static final int PORT=1999;
	public static final String HOST="127.0.0.1";
	Socket socket;
	BufferedReader bufferedReader;
	DataInputStream netIn;
	DataOutputStream netOut;
	String clientDir="";
	String userLine="";
	String sf,df;
	public FileClient(){
		clientDir="C:\\source";
		
	}
	public void waitForConnect() throws IOException{
		try {
			socket=new Socket(HOST, PORT);
			bufferedReader=new BufferedReader(new InputStreamReader(System.in));
			netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			while(true){
				
			userLine=bufferedReader.readLine();
			analysis(userLine);
			
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		netIn.close();
		netOut.flush();
		netOut.close();
		socket.close();
		
	}
	
	private void analysis(String userLine) throws IOException,ArrayIndexOutOfBoundsException {
		String array[]=userLine.split(" ");
		String command=array[0];
		System.out.println(command);
		String value=array[1];
		/* example: SET_SERVER_DIR directory
		 * command = SET_SERVER_DIR
		 * value = directory
		 */
		switch (command) {
		case "SET_SERVER_DIR":
			netOut.writeUTF(userLine);
			break;
        case "SET_CLIENT_DIR":
			 clientDir=value;
			break;

        case "SEND":
        	try {
        		sf=array[1];
        		df=array[2];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Nhập thiếu rồi bạn ơi!!!");
			}
			netOut.writeUTF(command+" "+ df);
			netOut.flush();
			send(sf);
			break;
        case "GET":
        	sf=array[1];
        	df=array[2];
        	netOut.writeUTF("command"+" "+ sf);
        	netOut.flush();
        	receive(df);
			break;
		default:
			break;
		}
	
		netOut.flush();
		
		
	}

	private void receive(String df) {
	
	}
	
	private void send(String sf) throws IOException {
		File file = new File(clientDir+File.separator+sf);
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		netOut.writeLong(file.length());
	    byte [] buff=new byte[10*1024];
	    int data;
	    while((data=bis.read(buff))!= -1){
	    	netOut.writeInt(data);
	    	netOut.flush();
	    	netOut.write(buff, 0, data);
	    	netOut.flush();
	    }
	    netOut.writeInt(-1);
	    netOut.flush();
	    bis.close();
	}
	public static void main(String[] args) throws IOException {
		FileClient fileClient=new FileClient();
		fileClient.waitForConnect();
		
		
	}

}
