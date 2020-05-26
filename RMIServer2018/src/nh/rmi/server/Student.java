package nh.rmi.server;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Date date;
	private String address;
	
	public Student(String id, String name, Date date, String address) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.address = address;
	}
	public Student(String name, Date date, String address) {
		this.name = name;
		this.date = date;
		this.address = address;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", date=" + date + ", address=" + address+ "]";
	}
	public static void main(String[] args){
		Date date =new Date(10, 12, 1999);
		Student st=new Student("SV01", "Nghia",date, "Tp. Ho Chi Minh");
		System.out.println(st.toString());
		
	}
	
}
