package dao;

import java.util.List;

import bean.Batch;
import bean.BatchReport;
import bean.Course;
import bean.CoursePlan;
import bean.Faculty;
import exceptions.AdminException;

public interface AdminDao {
	
public String loginAdmin(String username, String password) throws AdminException;
	
	public String addNewCourse(Course course);
	
	public String updateFeeBY10Percent(int cid);
	
	public List<Course> getAllCources() throws AdminException;
	
	public String createNewBatch(Batch batch);
	
	public String updatenoOfStudentInBatch(int numberOfStudents, int batchId);
	
	public List<Batch> seeAllOnGoingBatches() throws AdminException;
	
	public String createNewFaculty(Faculty faculty);
	
	public List<Faculty> showAllFaculty() throws AdminException;
	
	public String allocateFacultyToBatch(int facId, int batchId);
	
	public String createCoursePlan(CoursePlan cp);
	
	public String updateStatusInCoursePlan(int planId, int batchId);
	
	public List<CoursePlan> showAllCoursePlan() throws AdminException;
	
	public List<BatchReport> generatebatchReport() throws AdminException;
}
