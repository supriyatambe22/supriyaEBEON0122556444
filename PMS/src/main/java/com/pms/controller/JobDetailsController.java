package com.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pms.entity.CompanyDetails;
import com.pms.entity.JobDetails;
import com.pms.entity.MessagePMS;
import com.pms.service.CompanyDetailsService;
import com.pms.service.JobDetailsService;

@Controller
public class JobDetailsController 
{

	@Autowired
	JobDetailsService jobDetailsService;
	

	@Autowired
	CompanyDetailsService companyDetailsService;
		
		@GetMapping("/add-Job")
		public String addJob(Model model)
		{
			model.addAttribute("jobdetails", new JobDetails());
			return "company/add-Job";

		}

		@PostMapping("/addjob22{companyId}")
		public String addjobs(@Valid @ModelAttribute JobDetails jobdetails,BindingResult result,@PathVariable("companyId") int companyId,
				@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,Model model, HttpSession session)
		{
			System.out.println(agreement);
			System.out.println(jobdetails);
				try
				{
					
				 if(result.hasErrors())
				 {
					 System.out.println("Message 1");
					model.addAttribute("jobdetails", jobdetails);
					 return "company/add-Job";
				 }
						 
				
				 model.addAttribute("jobdetails", jobdetails);
					
					if(agreement)
					{
						 System.out.println("Message 2");
						session.setAttribute("message1", new MessagePMS("Job is Created Successfully!!!!","alert-success"));
				System.out.println("CompanyId --------------"+companyId);
					    CompanyDetails companyDetail = this.companyDetailsService.getcompanyDetailById(companyId);
					  //  jobdetails.set
					    companyDetail.getJobdetails().add(jobdetails);
					    
					    jobdetails.setCompanydetails(companyDetail);
						this.companyDetailsService.addCompanyDetails(companyDetail);
					
						//this.jobDetailsService.addJobDetails(jobdetails);
						return "company/ViewJobDetails1";
						//return "ViewJobDetails";
					}
					else
					{
						 System.out.println("Message 3");
						session.setAttribute("message1", new MessagePMS("Job is Not Created Successfully!!!!","alert-danger"));
						throw new Exception("Kindly accept the terms and conditions !");
					}
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}
				 System.out.println("Message 4");
				
				return "company/add-Job";
		}
		
		@GetMapping("/ViewJobDetails")
		public String ViewJobDetails(Model model)
		{
			List<JobDetails> jobdetails1 = this.jobDetailsService.getAllJobDetails();
			model.addAttribute("jdObj", jobdetails1);
			return "company/ViewJobDetails";
			
		}
		
		@GetMapping("/ViewJobDetails1")
		public String ViewJobDetails1(Model model)
		{
			List<JobDetails> jobdetails1 = this.jobDetailsService.getAllJobDetails();
			model.addAttribute("jdObj", jobdetails1);
			return "company/ViewJobDetails1";
			
		}
		
		@GetMapping("/ViewJob")
		public String ViewJob(Model model)
		{
			List<JobDetails> jobdetails2 = this.jobDetailsService.getAllJobDetails();
			
			//List<CompanyDetails> companydetails2 = this.companyDetailsService.getcompanyDetailByCompanyName(companyName);
			
			
			model.addAttribute("jdObj2", jobdetails2);
			return "company/ViewJob";
			
		}
		/*
		 * @GetMapping("/ViewJob{companyId}") public String ViewJob(Model
		 * model, @PathVariable("companyId") int companyId) {
		 * System.out.println("COMPANY ID ------------"+companyId); List<JobDetails>
		 * jobdetails2 = this.jobDetailsService.getAllJobDetailsBycompanyId(companyId);
		 * 
		 * //List<CompanyDetails> companydetails2 =
		 * this.companyDetailsService.getcompanyDetailByCompanyName(companyName);
		 * 
		 * 
		 * model.addAttribute("jdObj2", jobdetails2); return "company/ViewJob";
		 * 
		 * }
		 */
		
		/*
		 * @GetMapping("/UpdateJobDetails{companyId}") public String
		 * editjobForm(@PathVariable int companyId, Model model) {
		 * System.out.println("update ----------" + companyId);
		 * model.addAttribute("companydetails",
		 * companyDetailsService.getcompanyDetailById(companyId)); return
		 * "company/UpdateJobDetails";
		 * 
		 * }
		 * 
		 * @PostMapping("/saveUpdateJobDetails{companyId}") public String
		 * updateJobDetails(@ModelAttribute JobDetails jobdetails,
		 * 
		 * @PathVariable("companyId") int companyId) {
		 * 
		 * this.studentdetailsService.updateStudentDetails(studentdetails, studentId);
		 * return "redirect:/ViewStudentDetails";
		 * 
		 * }
		 * 
		 * @GetMapping("/DeleteStudentDetails{studentId}") public String
		 * deleteStudentDetails(@PathVariable("studentId") int studentId,HttpSession
		 * session) { studentdetailsService.deleteStudentDetailsBystudentId(studentId);
		 * session.setAttribute("message1",new
		 * MessagePMC("student deleted successfully","alert-success"));
		 * 
		 * return "redirect:/ViewStudentDetails";
		 * 
		 * }
		 */
}

