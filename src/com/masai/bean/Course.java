package com.masai.bean;

public class Course {

	private int courseId;
	private String courseName;
	private int fees;
	private String courseDescription;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(int courseId, String courseName, int fees, String courseDescription) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fees = fees;
		this.courseDescription = courseDescription;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", fees=" + fees + ", courseDescription="
				+ courseDescription + "]";
	}
	
	
	
	
	
}
