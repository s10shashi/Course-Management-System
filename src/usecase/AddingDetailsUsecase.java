package usecase;

import java.util.Scanner;

import bean.Batch;
import bean.Course;
import bean.CoursePlan;
import bean.Faculty;
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
		
		AdminDao ad = new AdminDaoImpl();
		String str = ad.createNewBatch(batch);
		System.out.println(str);

		
		
	
		
	}
	
	public static void creatFaculty() {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Faculty Name");
		String name= sc.nextLine();
		
		System.out.println("Enter Faculty address");
		String address= sc.nextLine();
		
		System.out.println("Enter Faculty Mobile Number");
		String mobile= sc.nextLine();
		
		System.out.println("Enter Faculty Email");
		String email= sc.nextLine();
		
		System.out.println("Enter username of Faculty");
		String username= sc.nextLine();
		
		System.out.println("Enter Password");
		String password=sc.nextLine();
		
		Faculty faculty= new Faculty(0,name, address, mobile, email, username, password);
		
		AdminDao ad = new AdminDaoImpl();
		String str = ad.createNewFaculty(faculty);
		System.out.println(str);
		
		

	}
	
	public static void createCoursePlan() {
		
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Batch ID");
		int bid= sc.nextInt();
		
		System.out.println("Enter Day Number");
		int dayNo= sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Topic");
		String topic= sc.nextLine();
		
		
		System.out.println("Enter status(completed/pending)");
		String status= sc.nextLine();
		
		CoursePlan cp = new CoursePlan(0, bid, dayNo, topic, status);
		
		AdminDao ad= new AdminDaoImpl();
		String str= ad.createCoursePlan(cp);
		System.out.println(str);
		
		
		
	}
	
	
	
}
