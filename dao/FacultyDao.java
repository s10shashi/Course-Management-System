package com.masai.dao;

import java.util.List;

import com.masai.bean.BatchReport;
import com.masai.exceptions.AdminException;

public interface FacultyDao {

	public String loginFaculty(String username, String password) throws AdminException;
	
	public List<BatchReport> showCoursePlan(String username) throws AdminException;
	
	public String updatePassword(String username,String oldPass, String newPass);
	
}
