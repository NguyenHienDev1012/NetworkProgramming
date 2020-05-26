package nh.rmi.server;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyBufferedOutputStream extends UnicastRemoteObject implements IStream {

	private static final long serialVersionUID = 1L;
    String serverPath="C:\\Users\\DELL\\workspace2\\RMIServer2018\\Data\\";
	BufferedOutputStream bos;

	protected MyBufferedOutputStream() throws RemoteException {
		super();
	}

	public void open(String fName) throws RemoteException {
		try {
			bos = new BufferedOutputStream(new FileOutputStream(serverPath + fName));
		} catch (FileNotFoundException e) {
		}
	}

	public void write(byte[] data, int offset, int len) throws RemoteException {
		if (bos != null)
			try {
				bos.write(data, offset, len);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void close() throws RemoteException {
		if (bos != null)
			try {
				bos.close();
				bos = null;
			} catch (IOException e) {
				e.printStackTrace();
				bos = null;
			}

	}

}
