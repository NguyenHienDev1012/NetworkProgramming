package nh.rmi.download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static int PORT= 1999;
	public static String clientPath="C:\\Users\\DELL\\Desktop\\";
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry r=LocateRegistry.getRegistry(PORT);
		IDownStream mybis=(IDownStream)r.lookup("mybis");
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File(clientPath+"ebanking-down.rar")));
		int id=mybis.openSource("a.zip");
		byte [] data=new byte[10*1024];
		while((data=mybis.readData(id))!=null){
				bos.write(data, 0, data.length);
				bos.flush();
		}
		bos.flush();
		mybis.closeSource(id,"a.zip");
		bos.close();
		
		
	}
}
