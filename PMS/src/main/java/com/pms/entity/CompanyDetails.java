package com.pms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "Please Enter Company Name")
	@Size(min = 2, max = 20, message = "Minimum character must be 2 and Maximum character should be 20")
	private String companyName;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Please Enter Comapany Email Address")
	@Email(message = "Please Enter Correct Email Address")
	private String companyEmail;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Please Enter Company Contact Number")
	@Size(min = 10, max = 10, message = "Company Contact Number length should be 10 only")
	private String companyContact;
	
	@Column(length = 25, nullable = false)
	@NotBlank(message = "Please Enter Company Address")
	@Size(min=3, max=20,message = "Please Enter Address")
	private String companyAddress;
	
	@Column(nullable = false)
	private String companyLogo;
	
	 @Column(nullable = true) 
	  private String companyAction;
	 
	
	
	  @OneToMany(mappedBy = "companydetails", cascade = CascadeType.ALL) 
	 private List<JobDetails> jobdetails;
		/*
		 * @ManyToOne private Admin admin;
		 */
	
	
	
	
	
}

