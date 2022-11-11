package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.masai.bean.Batch;
import com.masai.bean.BatchReport;
import com.masai.bean.Course;
import com.masai.bean.CoursePlan;
import com.masai.bean.Faculty;
import com.masai.exceptions.AdminException;
import com.masai.utility.DBUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public String loginAdmin(String username, String password) throws AdminException {
		String res = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? && password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("username");
//				String pass = rs.getString("password");
				rs.getString("password");
				
				res = name;
			}else {
				throw new AdminException("Invalid Username or password.. ");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

		return res;
	}

	
	
	@Override
	public String addNewCourse(Course course) {
		String str = "Something went wrong..";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into course (courseName,fee,courseDescription) values(?,?,?)");
			
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getFees());
			ps.setString(3,course.getCourseDescription());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				str = "Course added sucessfully!";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return str;
	}
	
	
	



	@Override
	public String updateFeeBY10Percent(int cid) {
		String res = "Course not exist with this course ID...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update course set fee = fee+(fee*10/100) where courseId=?;");
			ps.setInt(1, cid);
			
			int x = ps.executeUpdate();
			if(x>0) {
				res = "Fees Updated Sucessfully..";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return res;
	}



	@Override
	public List<Course> getAllCources() throws AdminException {
		List<Course> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from course");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("courseId");
				String name = rs.getString("courseName");
				int fee = rs.getInt("fee");
				String cdesc = rs.getString("courseDescription");
				
				Course course = new Course(id,name,fee,cdesc);
				list.add(course);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());		
		
		}
		
		if(list.size()==0) {
			throw new AdminException("No Course found..");
		}
		
		return list;
	}



	@Override
	public String createNewBatch(Batch batch) {
		
		String str = "Something went wrong...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into batch (courseId,numberOfStudents,batchStartDate,duration) values (?,?,?,?)");
			
			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getNumberOfStudents());
			ps.setString(3, batch.getBatchStartDate());
			ps.setString(4, batch.getDuration());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				str = "Batch Created Sucessfully";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return str;
		
	}



	@Override
	public String updatenoOfStudentInBatch(int no, int batchId) {
		String res = "Batch does not exist...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update batch set numberOfStudents = ? where batchId=?;");
			ps.setInt(1, no);
			ps.setInt(2, batchId);
			
			int x = ps.executeUpdate();
			if(x>0) {
				res = "No. of Students updated sucessfully...";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return res;
	}



	@Override
	public List<Batch> seeAllOnGoingBatches() throws AdminException {
		List<Batch> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from batch");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int batchId = rs.getInt("batchId");
				int courseId = rs.getInt("courseId");
				int facultyId = rs.getInt("facultyId");
				int noOfStud = rs.getInt("numberOfStudents");
				String batchStartDate = rs.getString("batchStartDate");
				String duration =rs.getString("duration");
				
				Batch batch = new Batch(batchId,courseId,facultyId,noOfStud,batchStartDate,duration);
				list.add(batch);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());		
		
		}
		
		if(list.size()==0) {
			throw new AdminException("No Batch found..");
		}
		
		return list;
	}



	@Override
	public String createNewFaculty(Faculty faculty) {
		String str = "Something went wrong...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into faculty values (?,?,?,?,?,?,?)");
			
			ps.setInt(1, faculty.getFacultyId());
			ps.setString(2, faculty.getFacultyName());
			ps.setString(3, faculty.getFacultyAddress());
			ps.setString(4, faculty.getMobile());
			ps.setString(5, faculty.getEmail());
			ps.setString(6, faculty.getUsername());
			ps.setString(7, faculty.getPassword());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				str = "Faculty added sucessfully...";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return str;
	}



	@Override
	public List<Faculty> showAllFaculty() throws AdminException {
		List<Faculty> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from faculty");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int fid = rs.getInt("facultyId");
				String name = rs.getString("facultyName");
				String address =rs.getString("facultyAddress");
				String mobile =rs.getString("mobile");
				String email =rs.getString("email");
				String username =rs.getString("username");
				String password =rs.getString("password");
				
				Faculty fac = new Faculty(fid,name,address,mobile,email,username,password);
				list.add(fac);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());		
		
		}
		
		if(list.size()==0) {
			throw new AdminException("No Faculty found..");
		}
		
		return list;
	}



	@Override
	public String allocateFacultyToBatch(int facId, int batchId) {
		String res = "Batch does not exist...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update batch set facultyId = ? where batchId = ?;");
			ps.setInt(1, facId);
			ps.setInt(2, batchId);
			
			int x = ps.executeUpdate();
			if(x>0) {
				res = "Faculty allocated sucessfully to batch no "+batchId;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return res;
	}



	@Override
	public String createCoursePlan(CoursePlan cp) {
		String str = "Something went wrong...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into coursePlan values (?,?,?,?,?)");
			
			ps.setInt(1, cp.getPlanId());
			ps.setInt(2, cp.getBatchId());
			ps.setInt(3,cp.getDayNumber());
			ps.setString(4, cp.getTopic());
			ps.setString(5, cp.getStatus());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				str = "Course Plan Created Sucessfully...";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return str;
	}



	@Override
	public String updateStatusInCoursePlan(int planId, int batchId) {
		
		String res = "Course plan does not exist...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update coursePlan set status = 'completed' where planId = ? AND batchId=?");
			ps.setInt(1, planId);
			ps.setInt(2, batchId);
			
			int x = ps.executeUpdate();
			if(x>0) {
				res = "status updated sucessfully...";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return res;
	}



	@Override
	public List<CoursePlan> showAllCoursePlan() throws AdminException {
		
		List<CoursePlan> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from coursePlan");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int planId = rs.getInt("planId");
				int batchId = rs.getInt("batchId");
				int dayNo = rs.getInt("daynumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");
				
				CoursePlan cp = new CoursePlan(planId,batchId,dayNo,topic,status);
				list.add(cp);
			}
			
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new AdminException("No records found");
		}
		
		
		return list;
		
	}



	@Override
	public List<BatchReport> generatebatchReport() throws AdminException {
		List<BatchReport> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select b.batchId, c.courseName, f.facultyName, cp.daynumber, cp.status "
					+ "from batch b, course c, faculty f, coursePlan cp "
					+ "where b.facultyId=f.facultyId AND b.courseId = c.courseId AND b.batchId = cp.batchId;");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("batchId");
				String cName = rs.getString("courseName");
				String fname = rs.getString("facultyName");
				int dayno = rs.getInt("daynumber");
				String status = rs.getString("status");
				
				BatchReport br = new BatchReport(bId,cName,fname,dayno,status);
				list.add(br);
			}
			
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new AdminException("No records found");
		}
		
		
		return list;
		
	}


	
	
}
