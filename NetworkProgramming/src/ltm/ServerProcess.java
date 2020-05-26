package ltm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerProcess  extends Thread{
	private Socket socket;
	private BufferedReader  netIn;
	private PrintWriter pr;
	private String serverDir;
	private String userIn;
	private File file;
	public ServerProcess(Socket socket) throws IOException {
		this.socket=socket;
		netIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pr=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		serverDir="C:\\Users\\DELL\\workspace2\\NetworkProgramming\\";
		System.out.println("WELCOME!!!");
	}
	
	
	@Override
	public void run() {
		StringTokenizer st;
		String command="";
		while(true){
			try {
				pr.println("SET_DIR directory");
				pr.println("SET_FILE file_name");
				pr.println("GET_NUM");
				pr.println("GET_WORDS");
				pr.println("QUIT");
				userIn=netIn.readLine();
				st=new StringTokenizer(userIn," ");
				command=st.nextToken();
				if(command.equalsIgnoreCase("QUIT")){
					pr.println("Goodbye");
					break;
				}
				switch (command) {
				case "SET_DIR":
					String serverChanged=st.nextToken();
					pr.println("You changed serverDir from:\t"+serverDir +"to"+serverChanged);
					serverDir=serverChanged;
					break;
				case "SET_FILE":
				    file=new File(serverDir+st.nextToken());
				    if(!file.exists()){
				    	pr.println("File is not exists");
				    }else{
				    	pr.println("File is exists");
				    	
				    }
					break;
				case "GET_NUM":
					pr.println("Tong cac so:"+getNum(file));				     
				
					break;
				case "GET_WORDS":
				pr.println("Tong cac chu:"+getWords(file));			
				break;

				default:
					pr.println("Sai cu phap!!!");		
					break;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}


	private int getWords(File file) throws IOException {
		int count=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line=br.readLine();
		while(line!=null){
			StringTokenizer st=new StringTokenizer(line," ");
			while(st.hasMoreElements()){
			try {
				int x=Integer.parseInt(st.nextToken());
			} catch (Exception e) {
				count++;
			}
		}
			 line=br.readLine();
		}
		
	return count;
		
	}


	private int getNum(File file) throws IOException {
		int num=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line=br.readLine();
		while(line!=null){
			StringTokenizer st=new StringTokenizer(line," ");
			while(st.hasMoreElements()){
			try {
				int x=Integer.parseInt(st.nextToken());
				num+=x;
			} catch (Exception e) {
				continue;
			}
		  }
			 line=br.readLine();
		}
		
	return num;
	}

}
