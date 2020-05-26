package nh.tcp.file;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileServerProcess  extends Thread{
	 Socket socket;
	 DataInputStream netIn;
	 DataOutputStream netOut;
	 String serverDir="";
	 String sf,df;
	 
	 public FileServerProcess(Socket socket) {
		     
		 try {
		    serverDir="C:\\dest";
		    this.socket=socket;
			netIn=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			netOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	@Override
	public void run() {
		String line="";
		while(true){
			try {
				line=netIn.readUTF();
				System.out.println("read"+line);
				analysis(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	private void analysis(String line) throws IOException {
		String array[]=line.split(" ");
		switch (array[0]) {
		case "SET_SERVER_DIR":
			serverDir=array[1];
			System.out.println(serverDir);
			break;
			
		case "SEND":
			try {
				df=array[1];	
			} catch (ArrayIndexOutOfBoundsException e) {
			}
			
			receive(df);
			break;

		default:
			break;
		}
		
	}
	private void receive(String df) throws IOException {
		File file=new File(serverDir+File.separator+df);
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		long size=netIn.readLong();
		int byteRead, byteMustRead;
		long remain = size;
		byte[] buff = new byte[10*1024];
//		while (remain > 0) {
//			byteMustRead = buff.length > remain ? (int) remain : buff.length;
//			byteRead = netIn.read(buff, 0, byteMustRead);
//			bos.write(buff, 0, byteRead);
//			remain-=byteRead;
//		}
		int data;
		while((netIn.readInt())!=-1){
			data = netIn.read(buff);
			bos.write(buff, 0,data);
		}
		
		bos.close();
	}

}
