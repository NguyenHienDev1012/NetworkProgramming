package nh.rmi.student;

import java.util.ArrayList;

public class StudentDao {
	
	public ArrayList<Student> loadStudents(){
		ArrayList<Student> listStudents=new ArrayList<Student>();
		Student s1=new Student("Hien", 18, 8);
		Student s2=new Student("Nghia", 19, 9);
		Student s3=new Student("Hoang", 20, 7);
		listStudents.add(s1);
		listStudents.add(s2);
		listStudents.add(s3);
		return listStudents;
		
	}
	

}
