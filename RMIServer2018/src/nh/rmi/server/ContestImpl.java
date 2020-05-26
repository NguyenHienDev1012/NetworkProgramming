package nh.rmi.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class ContestImpl extends UnicastRemoteObject implements IContest{
	
	private static final long serialVersionUID = 1L;
	private Student student=null;
    private DataOutputStream dos;
    private DataInputStream dis;
	protected ContestImpl() throws RemoteException {
		super();
		
	}

	@Override
	public  boolean register(Student student) throws RemoteException {
	  try {
	    String path="C:\\Users\\DELL\\workspace2\\RMIServer2018\\Data\\info.txt";
	    dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path,true)));
		Random rd=new Random();
		int id=rd.nextInt(1000);
		System.out.println("id"+id);
		String idStudent="MS"+id;
		this.student=student;
		if(validate(student.getDate())){
            student.setId(idStudent);
            try {
				dos.writeUTF(student.getId());
				dos.writeUTF("\t");
				dos.writeUTF(student.getName());
				dos.writeUTF("\t");
				dos.writeInt(student.getDate().getDay());
				dos.writeUTF("/");
				dos.writeInt(student.getDate().getMonth());
				dos.writeUTF("/");
				dos.writeInt(student.getDate().getYear());
				dos.writeUTF("\t");
				dos.writeUTF(student.getAddress());
				dos.flush();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return true;
		}
		
	  } catch (FileNotFoundException e) {
			e.printStackTrace();
}
		return false;
	}

	@Override
	public boolean validate(Date date) throws RemoteException {
		if(2019-date.getYear()<=6){
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws RemoteException {
		ContestImpl contest=new ContestImpl();
		//contest.register(new Student("Nghia", new Date(10, 12, 1999), "Thu Duc"));
		System.out.println(contest.viewInfo("MS735"));
	}

	@Override
	public String viewId() throws RemoteException {
		return student.getId();
	}

	@Override
	public Student viewInfo(String id) throws RemoteException {
		 String path="C:\\Users\\DELL\\workspace2\\RMIServer2018\\Data\\info.txt";
		 //String path="C:\\Users\\DELL\\Desktop\\a.txt";
		 ArrayList<Student> listStudents=new ArrayList<>();
		 try {
			dis=new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
            	try {
					while(dis.available()>0){
						String id_read=dis.readUTF();
						dis.readUTF();
						String name=dis.readUTF();
						dis.readUTF();
						int day=dis.readInt();
						dis.readUTF();
					    int month=dis.readInt();
					    dis.readUTF();
					    int year=dis.readInt();
					    dis.readUTF();
					    String address=dis.readUTF();
					Student s = new Student(id_read, name, new Date(day, month, year), address)	;
					listStudents.add(s);
					System.out.println(s);
					}
					for (Student st : listStudents) {
						if(st.getId().equals(id)){
							return st;
						}
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		    } catch ( FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		
		
		return null;
	}
}
