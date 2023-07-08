package com.spring.ORM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.ORM.Dao.impl.StudentDaoImpl;
import com.spring.ORM.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDaoImpl bean = context.getBean("daoImpl", StudentDaoImpl.class);
		Student bean2 = context.getBean("student", Student.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("**********************WELLCOME TO OUR APPLICATION**************************");

		boolean flag = true;
		while (flag) {
			System.out.println("Press 1 for add new Student");
			System.out.println("Press 2 for display all student");
			System.out.println("Press 3 for get detail of single Student");
			System.out.println("press 4 for delete Student");
			System.out.println("press 5 for update Student");
			System.out.println("press 6 for exit out application");
			try {

				byte input = Byte.parseByte(br.readLine());
				switch (input) {
				case 1:
					System.out.println("Enter Student ID");
					bean2.setStudentId(Integer.parseInt(br.readLine()));
					System.out.println("Enter Student Name");
					bean2.setStudentName(br.readLine());
					System.out.println("Enter Student City");
					bean2.setStudentCity(br.readLine());
					int i = bean.register(bean2);
					System.out.println("Your ID is :" + i);
					break;
				case 2:
					List<Student> list = bean.getAllStudent();
					for (Student student : list) {
						System.out.println("STUDENT DETAIL\n");
						System.out.println("Student Id :" + student.getStudentId());
						System.out.println("Student Name :" + student.getStudentName());
						System.out.println("Student City :" + student.getStudentCity());
						System.out.println("Student Country :" + student.getCountry());
						System.out.println("*****************************************************\n");
					}
					break;
				case 3:
					System.out.println("Enter Your Id :");
					bean2.setStudentId(Integer.parseInt(br.readLine()));
					Student ld = bean.getStudent(bean2.getStudentId());
					System.out.println("STUDENT DETAIL\n");
					System.out.println("Student Id :" + ld.getStudentId());
					System.out.println("Student Name :" + ld.getStudentName());
					System.out.println("Student City :" + ld.getStudentCity());
					System.out.println("Student Country :" + ld.getCountry());
					System.out.println("*****************************************************\n");
					break;
				case 4:
					System.out.println("Enter your Id to delete :");
					bean2.setStudentId(Integer.parseInt(br.readLine()));
					bean.deleteStudent(bean2.getStudentId());
					System.out.println("Student deleted");
					break;
				case 5:
					System.out.println("Enter your id to update :");
					bean2.setStudentId(Integer.parseInt(br.readLine()));
					List<Student> list2 = bean.getAllStudent();
					boolean flag1 = false;
					for (Student student : list2) {
						if(student.getStudentId()==bean2.getStudentId()) {
							flag1=true;
							System.out.println("Enter Your Name :");
							bean2.setStudentName(br.readLine());
							System.out.println("Enter Your City :");
							bean2.setStudentCity(br.readLine());
							bean.updateStudent(bean2);
							System.out.println("Updated..");
							break;
						}
						else {
							flag1=false;
						}
						
					}
					if(!flag1) {
						System.out.println("Id dosn't exist Enter valid Id");
					}
					break;
				case 6:
					flag = false;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Input Try With Another One");
				System.out.println(e.getMessage());
				e.printStackTrace();			
			}
		}
		System.out.println("Thankyou For Using Our Application !!");
		System.out.println("See You Soon...");
	}
}