package com.pms.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.StudentDetails;
import com.pms.repository.StudentDetailsRepo;
import com.pms.service.StudentDetailsService;

@Service
public class StudentDetailsServiceImplementation implements StudentDetailsService
{
	@Autowired
	StudentDetailsRepo studentDetailsRepo;

	@Override
	public StudentDetails addStudentDetails(StudentDetails studentdetails)
	{
		return  this.studentDetailsRepo.save(studentdetails);
		
	}

	@Override
	public StudentDetails checkLogin10(String email, String contact)
	{
		return studentDetailsRepo.findByStudentEmailAndStudentContact(email, contact);
	}

	@Override
	public List<StudentDetails> getAllStudentDetails() 
	{
		return studentDetailsRepo.findAll();
	}

	@Override
	public StudentDetails saveStudentDetails(StudentDetails studentdetails) 
	{
		return studentDetailsRepo.save(studentdetails);
	}

	@Override
	public StudentDetails getStudentDetailsBystudentId(int studentId) 
	{
		StudentDetails studentdetails= studentDetailsRepo.findById(studentId).get();
	
		 System.out.println(studentdetails.getStudentName()+"----------------------------Rahel");
		if(studentdetails!=null)
		{
			studentdetails = studentdetails;		
	    }
		else
		{
			throw new RuntimeException("Student not Fount !!!!!!");
		}
		return studentdetails;
	}

	@Override
	public void updateStudentDetails(StudentDetails studentdetails, int studentId) 
	{
		StudentDetails sdObj = studentDetailsRepo.findById(studentId).get();
		
		sdObj.setStudentName(studentdetails.getStudentName());
		sdObj.setStudentEmail(studentdetails.getStudentEmail());
		//sdObj.setStudentContact(studentdetails.getStudentContact());
		//sdObj.setStudentAddress(studentdetails.getStudentAddress());
		//sdObj.setStudentBirthdate(studentdetails.getStudentBirthdate());
		sdObj.setStudentDegree(studentdetails.getStudentDegree());
		sdObj.setStudentEducationBranch(studentdetails.getStudentEducationBranch());
		sdObj.setStudentPhoto(studentdetails.getStudentPhoto());
		
		this.studentDetailsRepo.save(sdObj);
	}

	@Override
	public void deleteStudentDetailsBystudentId(int studentId) 
	{
		StudentDetails studentdetails = this.studentDetailsRepo.findById(studentId).get();
		this.studentDetailsRepo.delete(studentdetails);
		
	}

	@Override
	public List<StudentDetails> listAll() {
		// TODO Auto-generated method stub
		return studentDetailsRepo.findAll();
	}

	@Override
	public StudentDetails get(int studentId) {
		// TODO Auto-generated method stub
		return studentDetailsRepo.findById(studentId).get();
	}

	@Override
	public boolean sendEmail(String subject, String message, String to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StudentDetails checkEmail(String studentEmail) 
	{
		return studentDetailsRepo.findBystudentEmail(studentEmail);
		/*
		 * return studentDetailsRepo.findByStudentEmailAndStudentContact(studentemail,
		 * studentemail);
		 */
		// TODO Auto-generated method stub
		/* return userRepo.findByuserEmail(userEmail); */
		
	}

	@Override
	public void updateStudent(StudentDetails studentdetails, int studentId) {
		// TODO Auto-generated method stub
		
		StudentDetails sdObj = studentDetailsRepo.findById(studentId).get();
		
		sdObj.setStudentName(studentdetails.getStudentName());
		sdObj.setStudentEmail(studentdetails.getStudentEmail());
		//sdObj.setStudentContact(studentdetails.getStudentContact());
		//sdObj.setStudentAddress(studentdetails.getStudentAddress());
		//sdObj.setStudentBirthdate(studentdetails.getStudentBirthdate());
		sdObj.setStudentDegree(studentdetails.getStudentDegree());
		sdObj.setStudentEducationBranch(studentdetails.getStudentEducationBranch());
		sdObj.setStudentPhoto(studentdetails.getStudentPhoto());
		
		this.studentDetailsRepo.save(sdObj);
		
	}

	}

	


	 


