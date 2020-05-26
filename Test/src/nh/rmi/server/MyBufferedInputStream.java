package nh.rmi.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MyBufferedInputStream extends UnicastRemoteObject implements IStream{
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedInputStream>list;
	private String serverDir="C:\\dest";
	protected MyBufferedInputStream() throws RemoteException {
		super();
		list=new ArrayList<>();
	}
	@Override
	public int openSource(String filename) throws RemoteException {
		File file = new File(serverDir+File.separator+filename);
		try {
			if(file.exists()&&file.isFile()){
			BufferedInputStream bis= new BufferedInputStream(new FileInputStream(file));
			list.add(bis);
			}
		} catch (FileNotFoundException e) {
			throw new RemoteException("Khong mo duoc file nguon!");
		}
		return list.size()-1;
	}
	@Override
	public byte[] readData(byte[] data,int id) throws RemoteException {
		try {
		   BufferedInputStream bis=list.get(id);
			int i=bis.read(data);
			if(i==-1) return null;
			byte[] result=new byte[data.length];
			System.arraycopy(data, 0, result, 0, i);
			return result;
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	@Override
	public void close(int id) throws RemoteException {
		try {
			list.get(id).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
