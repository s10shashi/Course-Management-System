package usecase;

import java.util.List;
import java.util.Scanner;

import bean.Batch;
import bean.BatchReport;
import bean.Course;
import bean.CoursePlan;
import bean.Faculty;
import dao.AdminDao;
import dao.AdminDaoImpl;
import dao.FacultyDao;
import dao.FacultyDaoImpl;
import exceptions.AdminException;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Welcome Student Management System");

		while (true) {
			System.out.println("\n1. Login as Admin \n2. Login as Faculty \n3. Exit");
			System.out.println("Enter Your Choice");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			if (choice == 1) {
				if (AdminLoginUsecase.login() == true) {
					System.out.println("Login Sucessfull");
					

					while (true) {
						System.out.println("===========================================");
						System.out.println("Select One Option:-");
						System.out.println("\n1. Add new course \n2. Update course fee by 10 percent\n3. " + 
											"See all Courses \n4. Create new Batch \n5. "
											+ "Update No of Students in a batch \n6. See all ongoing batches \n7. "
											+ "Add New Faculty \n8. Show all faculty Members \n9. "
											+ "Alloate faculty to a batch \n10. Create Course Plan \n11. "
											+ "Update Status in course Plan \n12. Show Day wise update of every batch \n13. "
											+ "Generate report for every batch");
						
						int x = sc.nextInt();

						switch (x) {
						
						
							case 1: {
								AddingDetailsUsecase.addCourse();
								break;
							}
							
							
							case 2: {
								System.out.println("Enter The course id to update the fees of that course");
								int f = sc.nextInt();
								AdminDao ad = new AdminDaoImpl();
								String str = ad.updateFeeBY10Percent(f);
								System.out.println(str);
								break;
							}
							
							
							case 3: {
								AdminDao ad = new AdminDaoImpl();
								try {
									List<Course> list = ad.getAllCources();
									list.forEach(res -> System.out.println(res));
								} catch (AdminException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							
							
							case 4: {
								AddingDetailsUsercase.createBatch();
								break;
							}
							
							
							case 5: {
								System.out.println("Enter updated no. of students");
								int a = sc.nextInt();
								System.out.println("Enter The Batch id to update the no. of students of that batch");
								int b = sc.nextInt();
								AdminDao ad = new AdminDaoImpl();
								String str = ad.updatenoOfStudentInBatch(a, b);
								System.out.println(str);
								break;
							}
							
							
							case 6: {
								AdminDao ad = new AdminDaoImpl();
								try {
									List<Batch> list = ad.seeAllOnGoingBatches();
									list.forEach(res -> System.out.println(res));
								} catch (AdminException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							
							
							case 7: {
								AddingDetailsUsercase.createFaculty();
								break;
							}
							
							
							case 8: {
								AdminDao ad = new AdminDaoImpl();
								try {
									List<Faculty> list = ad.showAllFaculty();
									list.forEach(res -> System.out.println(res));
								} catch (AdminException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							
							
							case 9: {
								System.out.println("Enter Faculty id");
								int fid = sc.nextInt();
								System.out.println("Enter batch id");
								int bid = sc.nextInt();
								AdminDao ad = new AdminDaoImpl();
								String str = ad.allocateFacultyToBatch(fid, bid);
								System.out.println(str);
								break;
							}
							
							
							case 10: {
								AddingDetailsUsercase.createCoursePlan();
								break;
							}
							case 11: {
								System.out.println("Enter Plan id");
								int a = sc.nextInt();
								System.out.println("Enter Batch id");
								int b = sc.nextInt();
								AdminDao ad = new AdminDaoImpl();
								String str = ad.updateStatusInCoursePlan(a, b);
								System.out.println(str);
								break;
							}
							
							
							
							case 12: {
								AdminDao ad = new AdminDaoImpl();
								try {
									List<CoursePlan> list = ad.showAllCoursePlan();
									list.forEach(res->{

										System.out.println("B id: "+res.getBatchId()+" || Day No. "+res.getDayNumber()+" || Topic: "+
												res.getTopic()+" || Status: "+res.getStatus());
									});
								} catch (AdminException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
							}
							
							
							
							case 13: {
								AdminDao ad = new AdminDaoImpl();
								try {
									List<BatchReport> list = ad.generatebatchReport();
									list.forEach(res->System.out.println(res));
								} catch (AdminException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
							}
						}
						System.out.println();
						System.out.println("You Want to Exit (y/n)");
						String s = sc.next();
						if(s.compareToIgnoreCase("Y")==0) {
							break;
						}else {
							continue;
						}
						
					}

				}

			} else if (choice == 2) {
				
				boolean boll = false;
				System.out.println("Enter username");
				String username = sc.next();
				System.out.println("Enter password");
				String password = sc.next();
				String str = null;
				try {
					FacultyDao fd = new FacultyDaoImpl();
					str = fd.loginFaculty(username, password);
					boll = true;
				} catch (AdminException e) {
					System.out.println(e.getMessage());
				}
				
				if(boll) {
					System.out.println("Login Sucessfull");
					
					while(true) {
						System.out.println("===========================================");
						System.out.println("Select One Option:-");
						System.out.println("\n1. View The Coruse Plan \n2. Update Password");
						
						int x = sc.nextInt();
						
						switch(x) {
							case 1: {
								FacultyDao fd = new FacultyDaoImpl();
								try {
									
									List<BatchReport> list = fd.showCoursePlan(str);
									System.out.println("Faculty Name: "+list.get(0).getFacultyName());
									list.forEach(res->{
										System.out.println("Course - "+res.getCourseName()+" || Day "+res.getDayNumber()+" || Topic: "+res.getTopic());
									});
								} catch (AdminException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
							}
							
							case 2: {
								FacultyDao fd = new FacultyDaoImpl();
								System.out.println("Enter new password");
								String newPass = sc.next();
								String st = fd.updatePassword(str, password, newPass);
								System.out.println(st);
							}
						}
						
						System.out.println();
						System.out.println("You Want to Exit (y/n)");
						String s = sc.next();
						if(s.compareToIgnoreCase("Y")==0) {
							break;
						}else {
							continue;
						}
						
					}
					
				}
				
				
			} else {
				System.out.println("Thank You, Have A Great Day Ahead");
				break;
			
		}
			
	}

}
