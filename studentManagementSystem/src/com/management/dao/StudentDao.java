package com.management.dao;


import com.management.bean.*;

public interface StudentDao {
	
	public String registerStudent(Student student);
	public String updateDetail();
	
	public List<String> displayAllCourseAndDetail();
	

}
