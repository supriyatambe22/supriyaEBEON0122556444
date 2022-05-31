package com.pms.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Admin;
import com.pms.repository.AdminRepo;
import com.pms.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService
{
	@Autowired
	AdminRepo adminRepo;

	@Override
	public Admin addAdmin(Admin admin) 
	{
		return this.adminRepo.save(admin);
		
	}

	@Override
	public Admin checkLogin11(String email, String contact) 
	{
		return adminRepo.findByAdminEmailAndAdminContact(email, contact);
		
	}
	

}
