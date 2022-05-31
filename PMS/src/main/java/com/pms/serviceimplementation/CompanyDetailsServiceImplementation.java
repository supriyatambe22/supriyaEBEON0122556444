package com.pms.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.CompanyDetails;
import com.pms.repository.CompanyDetailsRepo;
import com.pms.service.CompanyDetailsService;

@Service
public class CompanyDetailsServiceImplementation implements CompanyDetailsService
{

	@Autowired
	CompanyDetailsRepo companyDetailsRepo;
	
	@Override
	public CompanyDetails addCompanyDetails(CompanyDetails companydetails) 
	{
		return this.companyDetailsRepo.save(companydetails);
	}

	@Override
	public CompanyDetails checkLogin33(String email, String contact) 
	{
		return companyDetailsRepo.findByCompanyEmailAndCompanyContact(email, contact);
	}

	@Override
	public CompanyDetails getcompanyDetailById(int companyId) {
		// TODO Auto-generated method stub
		return this.companyDetailsRepo.findById(companyId).get();
	}

	@Override
	public CompanyDetails getCompanyDetailsBycompanyId(int companyId) 
	{
		CompanyDetails companydetails= companyDetailsRepo.findById(companyId).get();
		
		 System.out.println(companydetails.getCompanyName()+"----------------------------TCS");
		if(companydetails!=null)
		{
			companydetails = companydetails;		
	    }
		else
		{
			throw new RuntimeException("Company not Fount !!!!!!");
		}
		return companydetails;
	}

	@Override
	public void updateCompany(CompanyDetails companydetails, int companyId) 
	{
		
		CompanyDetails cdObj = companyDetailsRepo.findById(companyId).get();
		
		cdObj.setCompanyName(companydetails.getCompanyName());
		cdObj.setCompanyEmail(companydetails.getCompanyEmail());
		//sdObj.setStudentContact(studentdetails.getStudentContact());
		//sdObj.setStudentAddress(studentdetails.getStudentAddress());
		//sdObj.setStudentBirthdate(studentdetails.getStudentBirthdate());
		cdObj.setCompanyContact(companydetails.getCompanyContact());
		cdObj.setCompanyAddress(companydetails.getCompanyAddress());
		cdObj.setCompanyLogo(companydetails.getCompanyLogo());
		
		this.companyDetailsRepo.save(cdObj);
		
	}
	
}
