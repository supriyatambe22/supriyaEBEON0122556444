package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Admin 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "Please Enter Admin Name")
	@Size(min = 4, max = 24, message = "Minimum character must be 4 and Maximum character should be 24")
	private String adminName;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Please Enter Admin Email Address")
	@Email(message = "Please Enter Correct Email Address")
	private String adminEmail;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Please Enter Admin Contact Number")
	@Size(min = 10, max = 10, message = "Admin Contact Number length should be 10 only")
	private String adminContact;
	
	
	/*
	 * @OneToMany(mappedBy = "admin") private List<StudentDetails> studentdetails;
	 * 
	 * @OneToMany(mappedBy = "admin") private List<CompanyDetails> companydetails;
	 * 
	 * @OneToMany(mappedBy = "admin") private List<JobDetails> jobdetails;
	 */
	
	
}

