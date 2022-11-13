package usecase;

import java.util.Scanner;

import dao.AdminDao;
import dao.AdminDaoImpl;

public class AdminLoginUsecase {

	public static boolean login() {
		boolean result= false;
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter UserName");
		String username= sc.next();
		
		System.out.println("Enter Password");
		String password= sc.next();
		try {
			
			AdminDao ad= new AdminDaoImpl();
			ad.loginAdmin(username, password);
			result= true;
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
		
	}
}
