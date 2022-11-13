package dao;

import java.util.List;

import bean.BatchReport;
import exceptions.AdminException;

public interface FacultyDao {

	public String loginFaculty(String username, String password) throws AdminException;
	
	public List<BatchReport> showCoursePlan(String username) throws AdminException;
	
	public String updatePassword(String username,String oldPass, String newPass);

}
