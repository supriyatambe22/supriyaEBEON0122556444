package com.pms.service;

import java.util.List;

import com.pms.entity.StudentDetails;

public interface StudentDetailsService 
{
	public StudentDetails addStudentDetails(StudentDetails studentdetails);

	public StudentDetails checkLogin10(String email, String contact);
	
	public List<StudentDetails> getAllStudentDetails();
	
	public StudentDetails saveStudentDetails(StudentDetails studentdetails);
	
	public StudentDetails getStudentDetailsBystudentId(int studentId);
	
	public void updateStudentDetails(StudentDetails studentdetails , int studentId);
	
	void deleteStudentDetailsBystudentId(int studentId);

	public List<StudentDetails> listAll();

	public StudentDetails get(int studentId);

	public boolean sendEmail(String subject, String message, String to);

	public StudentDetails checkEmail(String studentEmail);

	public void updateStudent(StudentDetails studentdetails, int studentId);



	
	

	




	
	
}
