package com.management.dao;

import com.management.exception.StudentExeption;

public interface AdministratorDao {
	
	public String addNewCourse() throws StudentExeption;
	
	public String updateFeesOfCourse() throws StudentExeption;
	
	public String deleteCoursefrombatch() throws StudentExeption;
	
	public void searchAboutCourse() throws StudentExeption;
	
	public void createBatch();
	
	public void alocateBatchForStudent();
	
	public void updateSeatUnderBatch();
	
	public void viewAllStudentOfEveryBatch();

}
