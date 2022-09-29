package com.management.dao;

import java.util.List;

import com.management.bean.Student;

public interface StudentDao {
	
	public String registerCourse(int roll);
	public String updateDetail(int roll);
	public List<String> displayAllCourseAndDetail();
	

}
