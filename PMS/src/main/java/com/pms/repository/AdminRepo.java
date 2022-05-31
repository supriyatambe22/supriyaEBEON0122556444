package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Admin;

public interface AdminRepo  extends JpaRepository<Admin, Integer>
{
	Admin findByAdminEmailAndAdminContact(String email, String contact);
}


