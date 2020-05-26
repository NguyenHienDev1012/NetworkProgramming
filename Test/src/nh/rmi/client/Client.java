package nh.rmi.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nh.rmi.server.IStream;
import nh.rmi.server.IStream2;
public class Client {
	private String clientDir="C:\\source";
	IStream mybis;
	IStream2 mybos;
	public Client() throws RemoteException, NotBoundException{
		Registry r;
		r = LocateRegistry.getRegistry("localhost", 1999);
		mybis=(IStream)r.lookup("mybis");
		mybos=(IStream2)r.lookup("mybos");
		
	}
	
	public void download(String filename) throws NotBoundException, IOException{
		try {
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(clientDir+File.separator+filename));
			byte [] buff=new byte[10*1024];
			byte[] data;
			int id=mybis.openSource(filename);
			while((data=mybis.readData(buff,id))!=null){
				bos.write(data, 0, data.length);
				bos.flush();
			}
			bos.close();
			mybis.close(id);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
	public void upload(String filename) throws IOException{
	BufferedInputStream bis=new BufferedInputStream(new FileInputStream(clientDir+File.separator+filename));
	byte [] buff=new byte[10*1024];
	int i;
	int id=mybos.openSource(filename);
	while((i=bis.read(buff))!=-1){
		mybos.write(buff, 0, i, id);
	}
		mybos.close(id);
	}
	
	public static void main(String[] args) throws NotBoundException, IOException {
		new Client().upload("booking.png");
		
	}
	

}
