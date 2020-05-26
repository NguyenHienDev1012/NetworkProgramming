package nh.rmi.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MyBufferedOutputSream extends UnicastRemoteObject implements IStream2{

	private static final long serialVersionUID = 1L;
    private ArrayList<BufferedOutputStream> list=new ArrayList<>();
    private String serverDir="C:\\dest";
	protected MyBufferedOutputSream() throws RemoteException {
		super();
	}

	@Override
	public int openSource(String filename) throws RemoteException {
		BufferedOutputStream mybos;
		try {
			mybos = new BufferedOutputStream(new FileOutputStream(serverDir +File.separator+ filename));
			list.add(mybos);
		} catch (FileNotFoundException e) {
		}
		return list.size()-1;
	}

	@Override
	public void write(byte[] data, int off, int lenght,int id) throws RemoteException {
		try {
			list.get(id).write(data, 0, lenght);
		} catch (IOException e) {
			e.printStackTrace();
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
