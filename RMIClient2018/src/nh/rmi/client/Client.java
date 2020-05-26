package nh.rmi.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nh.rmi.server.Date;
import nh.rmi.server.IContest;
import nh.rmi.server.IStream;
import nh.rmi.server.Student;

public class Client {
	
	Registry r=LocateRegistry.getRegistry("LOCALHOST",1999);
	IContest contest=(IContest) r.lookup("contestImpl");
	IStream bos =(IStream)r.lookup("os");
	public Client() throws RemoteException, NotBoundException{
		String func = "REGISTER|Họ_và_tên|Ngày_sinh|Nơi_cư_trú\nSEND_FOTO|file_name\nVIEW_INFO|Mã_số_đã_đăng_ký\nQUIT\n";
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(true){
			System.out.println(func);
			try {
				line=in.readLine();
				if(line.equalsIgnoreCase("QUIT")){
					System.out.println("Tạm biệt!");
					break;
				}
				analysis(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	}
	
	
	public void analysis(String line) throws RemoteException {
		String command[]=line.split(" ");
		String key=command[0];
		String hoten,ngaysinh,noicutru,filename,masodadangky;
		switch (key) {
		case "REGISTER":
			hoten=command[1];
			ngaysinh=command[2];
			noicutru=command[3];
			String ns[]=ngaysinh.split("/");
			Student s=new Student(hoten, new Date(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2])), noicutru);
		    boolean check=	contest.register(s);
		    if(check){
		    	System.out.println("Mã số của bạn:" +contest.viewId());
		    }
		    else{
		    	System.out.println("Bạn lớn hơn 6 tuổi.");
		    }
		    
			break;
        case "SEND_FOTO":
			filename=command[1];
			upload(filename);
			System.out.println("Đã upload"+filename);
			
			
			break;
        case "VIEW_INFO":
        	masodadangky=command[1];
        	Student student = contest.viewInfo(masodadangky);
        	if(student == null){
        		System.out.println("Không tồn tại bé có mã số đăng ký:"+masodadangky);
        	}
        	else{
        		System.out.println("Thông tin:"+student);
        	}
			break;

		default:
			System.out.println("Nhập sai cú pháp! ");
			break;
		}
		
	}


	private void upload(String imageName) {
		String clientPath="C:\\Users\\DELL\\workspace2\\RMIClient2018\\Images\\";
		File in = new File(clientPath+imageName);
		if (in.exists() && in.isFile()) {
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(in));
				bos.open(in.getName());
				
				byte[] data = new byte[1024];
				int i = -1;
				while ((i = bis.read(data)) != -1) {
					bos.write(data, 0, i);
				}
				bis.close();
				bos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}


	public static void main(String[] args) throws RemoteException, NotBoundException {
		new Client();
		
	}

}
