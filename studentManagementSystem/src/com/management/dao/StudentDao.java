package com.management.dao;


import com.management.exception.StudentExeption;

public interface StudentDao {
	
	public String registerCourse() throws StudentExeption;
	public void updateDetail();
	public void displayAllCourseAndDetail();
	

}
