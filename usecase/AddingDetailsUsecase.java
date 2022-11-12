package usecase;

import java.util.Scanner;

import bean.Batch;
import bean.Course;
import dao.AdminDao;
import dao.AdminDaoImpl;

public class AddingDetailsUsecase {

	public static void addCourse() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Course Name");
		String name= sc.next();
		
		System.out.println("Enter Course Description");
		String desc= sc.next();
		
		System.out.println("Enter Course Fees");
		int fee= sc.nextInt();
		
		Course course= new Course();
		
		course.setCourseName(name);
		course.setCourseDescription(desc);
		course.setFees(fee);
		
		AdminDao ad = new AdminDaoImpl();
		String str = ad.addNewCourse(course);
		System.out.println(str);

		
		sc.close();
		
	}
	
	public static void createBatch() {
		
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Course ID");
		int cid= sc.nextInt();
		
		System.out.println("Enter Number Of Students");
		int noOfStu = sc.nextInt();
		
		System.out.println("Enter Batch Duration");
		String duration = sc.next();
		
		sc.nextLine();
		
		System.out.println("Enter Batch Starting Date");
		String date = sc.next();
		
		
		Batch batch= new Batch();
		batch.setCourseId(cid);
		batch.setNumberOfStudents(noOfStu);
		batch.setDuration(duration);
		batch.setBatchStartDate(date);
		
		sc.close();
		
		
		
	}
	
	
}
