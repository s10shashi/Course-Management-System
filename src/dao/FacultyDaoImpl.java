package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BatchReport;
import exceptions.AdminException;
import utility.DButil;

public class FacultyDaoImpl implements FacultyDao {

	@Override
	public String loginFaculty(String username, String password) throws AdminException {
		// TODO Auto-generated method stub
		String str= "Faculty please login first";
		
		try(Connection conn =DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from faculty where username=? && password=?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				String name= rs.getString("username");
				//String pass =rs.getString("password");
				rs.getString("password");
				str= name;
			}else {
				throw new AdminException("Invalid user name or password");
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return str;
	};

	@Override
	public List<BatchReport> showCoursePlan(String username) throws AdminException {
		// TODO Auto-generated method stub
			List<BatchReport> list = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select b.batchId, c.courseName, f.facultyName, cp.daynumber, cp.topic,  cp.status "
					+ "from batch b, course c, faculty f, coursePlan cp "
					+ "where b.facultyId=f.facultyId AND b.courseId = c.courseId "
					+ "AND b.batchId = cp.batchId AND f.username= ? ");
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int bId = rs.getInt("batchId");
				String cName = rs.getString("courseName");
				String fname = rs.getString("facultyName");
				int dayno = rs.getInt("daynumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");
				
				BatchReport br = new BatchReport(bId,cName,fname,dayno,topic,status);
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

	@Override
	public String updatePassword(String username, String oldPass, String newPass) {
		// TODO Auto-generated method stub
		
		String str= "Update failed or wrong password";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update faculty set password=? where password=? && username=?");
			
			ps.setString(1, newPass);
			ps.setString(2, oldPass);
			ps.setString(3, username);
			
			int x= ps.executeUpdate();
			if(x>0) {
				str="Password updated sucessfully";
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return str;
	}

}
