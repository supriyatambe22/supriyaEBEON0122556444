package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.CompanyDetails;

public interface CompanyDetailsRepo extends JpaRepository<CompanyDetails, Integer>
{
	CompanyDetails findByCompanyEmailAndCompanyContact(String email, String contact);
}
