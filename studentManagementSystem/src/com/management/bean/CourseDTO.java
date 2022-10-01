package com.management.bean;

public class CourseDTO {

	private int courseId;
	private String courseName;
	private int fees;

	private String batchName;
	private int avalSeat;

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

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getAvalSeat() {
		return avalSeat;
	}

	public void setAvalSeat(int avalSeat) {
		this.avalSeat = avalSeat;
	}

	public CourseDTO(int courseId, String courseName, int fees, String batchName, int avalSeat) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fees = fees;
		this.batchName = batchName;
		this.avalSeat = avalSeat;
	}

	public CourseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CourseDTO [courseId=" + courseId + ", courseName=" + courseName + ", fees=" + fees + ", batchName="
				+ batchName + ", avalSeat=" + avalSeat + "]";
	}

}
