package nh.tcp.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	private Socket socket;
	private BufferedReader netIn;
	private PrintWriter netOut;
	private String netUserIn;
	private String sourceFolder,sourceFile,desFile;
	private boolean isSetFolder;
	
	public ServerProcess(Socket socket) throws IOException{
		this.socket=socket;
		netIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		netOut.println("Welcome to fie management");
	}
	
	
	@Override
	public void run() {
		
		
		while(true){
			try {
				netUserIn=netIn.readLine();
				if(netUserIn.equalsIgnoreCase("QUIT")){
					netOut.println("Tam biet");
					netIn.close();
					netOut.close();
					socket.close();
					break;
				}
				else{
				StringTokenizer st=new StringTokenizer(netUserIn, "|");
				String key=st.nextToken();
				switch (key) {
				case "SET_FOLDER":
					String value=st.nextToken();
					File file=new File(value);
					if(!file.exists()){
						netOut.println("Folder khong ton tai!");
					}
					else{
						sourceFolder=value;
						netOut.println("Thiet lap folder thanh cong");
						isSetFolder=true;
					}
					
					break;

				case "VIEW":
					if(isSetFolder){
					netOut.println(	view(sourceFolder+st.nextToken()));
					}
					else{
						netOut.println("Thiet lap folder truoc");
					}
					
					break;
				case "COPY":
					sourceFile=st.nextToken();
					desFile=st.nextToken();
					File sCopy=new File(sourceFile);
					File dCopy=new File(desFile);
					
					if(!sCopy.exists()){
						netOut.println("File nguon khong ton tai!");
					}else{
						if(copy(sCopy,dCopy,false)){
							netOut.println("Copy thanh cong.");
						}
						else{
							netOut.println("Copy that bai.");
						}
					}
					break;
				case "MOVE":
					
					break;
				case "RENAME":
					
					break;
				default:
					netOut.println("Nhap sai cu phap");
					break;
				}
		      }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}


	private boolean copy(File sCopy, File dCopy,boolean move) {
		if(sCopy.isDirectory()){
			if(!dCopy.exists()) dCopy.mkdir();
			File []listFiles=sCopy.listFiles();
			for (File f : listFiles) {
				if(!f.getName().equals(dCopy.getName())){
					File f1 =new File(sCopy,f.getName());
					File f2 = new File(dCopy, f.getName());
					copy(f1, f2, move);
				}
			}
			
		}
		else{
			
			
		}
		
		return move;
		
		
		
	}


	private String view(String path) {
		String result="";
		File f =new File(path);
		if(!f.exists()){
			netOut.println("Khong ton tai file");
		}
		else{
			if(f.isDirectory()){
			   result=fullPath(f);
				
			}else{
				if(f.isFile()){
					String action = "";
					if(f.canRead()) action="R";
					if(f.canWrite()) action+="W";
					result+=f.getAbsolutePath()+"|"+"dunng luong"+f.length()+"|"+action;
				}
			}
		}
		return result;
	}
	String fullpath="";
	private String fullPath(File f) {
	 if(f.isDirectory()){
		fullpath+=f.getAbsolutePath()+"|";
		File [] listFiles=f.listFiles();
		for (File file : listFiles) {
				fullPath(file);
		}
	}else{
		fullpath+=f.getAbsolutePath()+"|";	
		}
		return fullpath;
	}

}
