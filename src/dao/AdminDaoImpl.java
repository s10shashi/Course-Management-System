package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Batch;
import bean.BatchReport;
import bean.Course;
import bean.CoursePlan;
import bean.Faculty;
import exceptions.AdminException;
import utility.DButil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public String loginAdmin(String username, String password) throws AdminException {
		// TODO Auto-generated method stub
		
		String res= null;
		
	try(	Connection conn= DButil.provideConnection()){
		
		PreparedStatement ps= conn.prepareStatement("select* from admin where username=? && password=?;");
		
		ps.setString(1, username);
		ps.setString(2,	password);
		
		ResultSet rs= ps.executeQuery();
		if( rs.next()) {
			String name= rs.getString("username");
			//String pass= rs.getString("password");
			rs.getString("password");
			res= name;
		}else {
			throw new AdminException("Invalid Username or Password");
		}
		
	}
	catch(SQLException e) {
		System.out.println(e.getMessage());
	}
	
	
		return res;
	}

	@Override
	public String addNewCourse(Course course) {
		// TODO Auto-generated method stub
		String str="Something Went Wrong ";
		try(Connection conn= DButil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("insert into course (courseName,fee,courseDescription)values(?,?,?);");
			
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getFees());
			ps.setString(3, course.getCourseDescription());
			
			int x= ps.executeUpdate();
			
			if(x>0) {
				str="Course Added Sucessfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return str;
	}

	@Override
	public String updateFeeBY10Percent(int cid) {
		// TODO Auto-generated method stub
		String str="Course not exits with provided courseID";
		
		try(Connection conn = DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("update course set fee= fee*1.1 where courseId=?;");
			
			ps.setInt(1, cid);
			
			int x= ps.executeUpdate();
			
			if( x>0) {
				str="Courese Fee Updated Sucessfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return str;
	}

	@Override
	public List<Course> getAllCources() throws AdminException {
		// TODO Auto-generated method stub
		List<Course>list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("Select * from course;");
			
			ResultSet re= ps.executeQuery();
			
			while(re.next()) {
				int id= re.getInt("courseId");
				String name= re.getString("courseName");
				int fee= re.getInt("fee");
				String cdesc= re.getString("courseDescription");
				
				Course course= new Course(id, name, fee, cdesc);
				list.add(course);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new AdminException("No Course Found In DataBase");
		}
		
		
		
		return list;
	}

	@Override
	public String createNewBatch(Batch batch) {
		// TODO Auto-generated method stub
		
		String str= "Batch not created";
		
		try(Connection conn= DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("insert into batch(courseId,numberOfStudents,batchStartDate,duration) value(?,?,?,?);");
			
			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getNumberOfStudents());
			ps.setString(3, batch.getBatchStartDate());
			ps.setString(4, batch.getDuration());
			
			int x= ps.executeUpdate();
			if(x>0) {
				str="Batch Created Sucessfully";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return str;
	}

	
	
	@Override
	public String updatenoOfStudentInBatch(int numberOfStudents, int batchId) {
		// TODO Auto-generated method stub
		
		String str= "Batch Does Not Exist";
		
		
			
		try(Connection conn= DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("update batch set numberOfStudents=? where batchId=?;");

			ps.setInt(1, numberOfStudents);
			ps.setInt(2, batchId);
			
			int x= ps.executeUpdate();
			if(x>0) {
				str= "No. of Students updates Sucessfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		return str;
	}

	@Override
	public List<Batch> seeAllOnGoingBatches() throws AdminException {
		// TODO Auto-generated method stub
		
		List<Batch> list= new ArrayList<>();
		
		try(Connection conn= DButil.provideConnection()) {
			 PreparedStatement ps= conn.prepareStatement("select * from batch;");
			 
			 ResultSet rs= ps.executeQuery();
			 while( rs.next()) {
				 int batchId= rs.getInt("batchId");
				 int courseId= rs.getInt("courseId");
				 int facultyid= rs.getInt("facultyId");
				 int noOfStud= rs.getInt("numberOfStudents");
				 String batchStartDate= rs.getString("batchStartDate");
				 String duration= rs.getString("duration");
				 
				 Batch b= new Batch(batchId, courseId, facultyid, noOfStud, batchStartDate, duration);
				 
						 list.add(b);
			 }
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		if( list.size()==0) {
			throw new AdminException("NO Data Founf in DataBase");
		}
		
		return list;
	}

	@Override
	public String createNewFaculty(Faculty faculty) {
		// TODO Auto-generated method stub
		String str= "Something went Wrong";
		
		try(Connection conn= DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("insert into faculty value(?,?,?,?,?,?,?);");
			
			ps.setInt(1, faculty.getFacultyId());
			ps.setString(2, faculty.getFacultyName());
			ps.setString(3, faculty.getFacultyAddress());
			ps.setString(4, faculty.getMobile());
			ps.setString(5, faculty.getEmail());
			ps.setString(6, faculty.getUsername());
			ps.setString(7, faculty.getPassword());
			int x= ps.executeUpdate();
			
			if(x>0) {
				str="Faculty added Sucessfully";
				
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return str;
	}

	@Override
	public List<Faculty> showAllFaculty() throws AdminException {
		// TODO Auto-generated method stub
		List<Faculty> list= new ArrayList<>();
		
		try(Connection conn= DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("Select * from Faculty;");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int facultyId= rs.getInt("facultyId");
				String name= rs.getString("facultyName");
				String address= rs.getString("facultyaddress");
				String mobile= rs.getString("mobile");
				String email= rs.getString("email");
				String username= rs.getString("username");
				String password= rs.getString("password");
				
				
				Faculty f= new Faculty(facultyId, name, address, mobile, email, username, password);
				
				list.add(f);
				
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		if(list.size()==0) {
			throw new AdminException("No Data in DataBase");
		}
		
		
		return list;
	}

	@Override
	public String allocateFacultyToBatch(int facId, int batchId) {
		// TODO Auto-generated method stub
		
		String str= "Something Went Wrong";
		
		try (Connection conn= DButil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("update batch set facultyId=? where batchId=?;");
			
			ps.setInt(1, facId);
			ps.setInt(2, batchId);
			int x= ps.executeUpdate();
			
			if(x>0) {
				str="Faculty Sucessfully to Following Batch";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return str;
	}

	@Override
	public String createCoursePlan(CoursePlan cp) {
		// TODO Auto-generated method stub
		
		String str= "Something Went Wrong";
		
		try (Connection conn= DButil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("insert into coursePlan values (?,?,?,?,?);");
			
			ps.setInt(1, cp.getPlanId());
			ps.setInt(2, cp.getBatchId());
			ps.setInt(3, cp.getDayNumber());
			ps.setString(4, cp.getTopic());
			ps.setString(5, cp.getStatus());
			
			int x= ps.executeUpdate();
			
			if(x>0) {
				str=" Course Plan Added Sucessfully";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return str;
	}

	@Override
	public String updateStatusInCoursePlan(int planId, int batchId) {
		// TODO Auto-generated method stub
		String str= "Course plan Does Not Exist";
		
		try (Connection conn= DButil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("update coursePlan set status = 'Complete' where planId=? AND batchId=?;");
			
			ps.setInt(1, planId);
			ps.setInt(2, batchId);
			int x= ps.executeUpdate();
			
			if(x>0) {
				str="Status updated Sucessfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return str;
	}

	@Override
	public List<CoursePlan> showAllCoursePlan() throws AdminException {
		// TODO Auto-generated method stub
		List<CoursePlan>list = new ArrayList<>();
		
		try(Connection conn= DButil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from CoursePlan");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int planId= rs.getInt("planId");
				int batchId= rs.getInt("batchId");
				int days= rs.getInt("daynumber");
				String topic = rs.getString("topic");
				String status= rs.getString("status");
				
				CoursePlan cp = new CoursePlan(planId, batchId, days, topic, status);
				list.add(cp);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new AdminException("No Data Found");
		}
		return list;
	}

	@Override
	public List<BatchReport> generatebatchReport() throws AdminException {
		// TODO Auto-generated method stub
		List<BatchReport>list= new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("select b.batchId, c.courseName, f.facultyName, cp.daynumber, cp.status "
					+ "from batch b, course c, faculty f, coursePlan cp "
					+ "where b.facultyId=f.facultyId AND b.courseId = c.courseId AND b.batchId = cp.batchId;");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int batchId= rs.getInt("batchId");
				String cName= rs.getString("courseName");
				String fName= rs.getString("facultyName");
				int dayNumber= rs.getInt("daynumber");
				String topic= rs.getString("topic");
				String status= rs.getString("status");
				
				BatchReport br = new BatchReport(batchId, cName, fName, dayNumber,topic ,status);
				
				list.add(br);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		
		if( list.size()==0) {
			throw new AdminException("No Data Record Found");
		}
		
		return list;
	}

}
