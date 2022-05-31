package com.pms.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class JobDetails 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	
	
	@Column(length = 50,nullable = false)
	@NotBlank(message = "Please Enter Job Role")
	@Size(min = 2, max = 35, message = "Minimum character must be 25 and Maximum character should be 35")
	private String jobRole;
	
	
	@Column(length = 50,nullable = false)
	@NotBlank(message = "Please Enter Job Description")
	@Size(min = 2, max = 35, message = "Minimum character must be 25 and Maximum character should be 35")
	private String jobDescription;
	
	
	@Column(length = 25,nullable = false)
	@NotBlank(message = "Please Enter Job Location")
	@Size(min = 3, max=10, message = "Job Location length should be 10 only")
	private String jobLocation;
	
	@Column
	private int jobPackage;

	
	@Column(length = 25,nullable = false)
	private Date jobPostdate;
	
	@JoinColumn(name = "companyName")
	
	
	@Column(length = 25,nullable = false)
	private Date jobExpirydate;

	  @ManyToOne
	 private CompanyDetails companydetails;
	  
		/*
		 * @ManyToOne private StudentDetails studentdetails;
		 */
		/*
		 * @ManyToOne private Admin admin;
		 */
	  
		@OneToMany(mappedBy = "jobdetails", cascade = CascadeType.ALL)
		List<AppliedJob> appliedjob;
	 
	
	
}


