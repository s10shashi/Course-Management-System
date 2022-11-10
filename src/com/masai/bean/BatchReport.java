package com.masai.bean;

public class BatchReport {

	private int batchId;
	private String courseName;
	private String facultyName;
	private int dayNumber;
	private String topic;
	private String status;
	
	public BatchReport() {
		// TODO Auto-generated constructor stub
	}

	public BatchReport(int batchId, String courseName, String facultyName, int dayNumber, String status) {
		super();
		this.batchId = batchId;
		this.courseName = courseName;
		this.facultyName = facultyName;
		this.dayNumber = dayNumber;
		this.status = status;
	}
	
	public BatchReport(int batchId, String courseName, String facultyName, int dayNumber, String topic, String status) {
		super();
		this.batchId = batchId;
		this.courseName = courseName;
		this.facultyName = facultyName;
		this.dayNumber = dayNumber;
		this.topic = topic;
		this.status = status;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BatchReport [batchId=" + batchId + ", courseName=" + courseName + ", facultyName=" + facultyName
				+ ", dayNumber=" + dayNumber + ", status=" + status + "]";
	}
	
	
	
	
}
