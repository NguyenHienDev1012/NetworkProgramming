package nh.rmi.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MyBufferedInputStream extends UnicastRemoteObject implements IDownStream{
	private static final long serialVersionUID = 1L;
	BufferedInputStream bis=null;
	public static String serverPath="C:\\Users\\DELL\\Desktop\\";
	private ArrayList<BufferedInputStream> list;
	protected MyBufferedInputStream() throws RemoteException {
		super();
		list=new ArrayList<>();
	}

	@Override
	public int openSource(String sf) throws RemoteException {
		try {
			bis=new BufferedInputStream(new FileInputStream(new File(serverPath+sf)));
			list.add(bis);
			return list.size()-1;
		} catch (FileNotFoundException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public byte[] readData(int id) throws RemoteException {
		byte[] buff=new byte[10*1024];
		int i=0;
		byte []res = null;
		try {
			i=list.get(id).read(buff);
			if(i==-1) return null;
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
			res=new byte[i];
			System.arraycopy(buff, 0, res, 0, i);
			try {
			} catch (Exception e) {
				throw new RemoteException(e.getMessage());
			}
		return res;
	}

	@Override
	public void closeSource(int id,String sf) throws RemoteException {
		try {
			list.get(id).close();
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
		
	}
	

}
