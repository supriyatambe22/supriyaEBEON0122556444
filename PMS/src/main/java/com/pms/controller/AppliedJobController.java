package com.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pms.entity.AppliedJob;
import com.pms.entity.JobDetails;
import com.pms.entity.MessagePMS;
import com.pms.entity.StudentDetails;
import com.pms.service.AppliedJobService;
import com.pms.service.CompanyDetailsService;
import com.pms.service.JobDetailsService;
import com.pms.service.StudentDetailsService;

@Controller
public class AppliedJobController 
{
	@Autowired
	private StudentDetailsService studentDetailsService;

	@Autowired
	private AppliedJobService appliedJobService;

	@Autowired
	private JobDetailsService jobDetailsService;

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@GetMapping("/applyJob{jobId}/{studentId}")
	public String applyJob(@PathVariable("jobId") int jobId,@PathVariable("studentId") int studentId, Model model,HttpSession session)
	{
		
		System.out.println("JOB ID      -----"+jobId);
		//System.out.println("STUDENT ID  -------"+studentId);
		
		JobDetails jobDetail=	  this.jobDetailsService.getJobDetailsByjobId(jobId);
		model.addAttribute("jobdetails", jobDetailsService.getJobDetailsByjobId(jobId));
		
		
//		 int studentId1 = studentDetailsService.getStudentDetailsBystudentId(studentId);
		 
		
		 StudentDetails stuDetails = this.studentDetailsService.getStudentDetailsBystudentId(studentId);
		 System.out.println("STUDENT ID      -----"+stuDetails);
		model.addAttribute("studentdetails",studentDetailsService.getStudentDetailsBystudentId(studentId));
	
		AppliedJob appliedJob = new AppliedJob();
		appliedJob.setStatus("applied");
		appliedJob.setJobdetails(jobDetail);
		appliedJob.setStudentdetails(stuDetails);
		
		jobDetail.getAppliedjob().add(appliedJob);
		stuDetails.getAppliedjob().add(appliedJob);
		//session.setAttribute("message1", new MessagePMC("Applied JOb Successfully", "alert-success"));
		//this.jobDetailsService.addJobDetails(jobDetail);
		this.studentDetailsService.addStudentDetails(stuDetails);
		session.setAttribute("message1",new MessagePMS("You have successfully applied for the job.","alert-success"));
		
	     //return "company/ViewJob"; 
		
		return "redirect:/ViewJob";
	
	}

	//<a th:href="@{/applyJobStatus{studentId}(studentId=${session.studentId})}"
	  @GetMapping("/applyJobStatus{studentId}") 
	  public String applyJobStatus(@PathVariable("studentId") int studentId, Model model) 
	  {
		  System.out.println("STUDENT ID-----------------"+studentId);
		  List<AppliedJob> appliedJob =this.appliedJobService.getAllAppliedJobByStudentId(studentId);
		  model.addAttribute("appliedjobObj", appliedJob);
		  return "student/AppliedJobStatus";
	  }
	



/*
 * @PostMapping("/applyJob{studentId}") public String
 * applyJob(@ModelAttribute("appliedjob") AppliedJob appliedjob, @PathVariable
 * int studentId) { System.out.println("Student ID -----------"+studentId);
 * System.out.println(appliedjob.getAppliedjobId()+"---------Job ID----------"+
 * appliedjob.getStatus()); Integer jobId =
 * Integer.parseInt(appliedjob.getStatus()); StudentDetails studentdetails =
 * studentDetailsService.getStudentDetailsBystudentId(studentId);
 * 
 * JobDetails jobdetails = jobDetailsService.getJobDetailsByjobId(jobId);
 * 
 * appliedjob.s
 * 
 * }
 */

/*
 * @GetMapping("JobApplicantList") public String jobApplicantList(Model model) {
 * List<AppliedJob> list = this.appliedJobService.getAllAppliedJob();
 * model.addAttribute("appliedjobObj", list); return "company/JobApplicantList";
 * 
 * }
 */

/*
  @PostMapping("/saveApplyStatus{studentId}") 
  public String saveApplyStatus(@ModelAttribute ("appliedjob") AppliedJob appliedjob, @PathVariable int studentId) 
  {
	  System.out.println("Student Id   "+studentId);
	  System.out.println(appliedjob.getAppliedjobId()+"------Job ID------"+appliedjob);
	return "student/AppliedJobStatus"; 
  }
 */
	  
/*
  @GetMapping("/studentlist") 
  public String applicantlist(Model model) 
  {
	  List<AppliedJob> appliedJob1 = this.appliedJobService.getAllAppliedJob();
	  model.addAttribute("appliedjobObj1", appliedJob1); 
	  
	  return "admin/studentlist"; 
  }
  
  @GetMapping("/applicantlist") 
  public String applicant(Model model) 
  {
	  List<JobDetails> applicant = this.jobDetailsService.getAllJobDetails();
	  model.addAttribute("applicantobj", applicant); 
	  
	  return "admin/applicantlist"; 
  }
*/
	  
	 /* @GetMapping("/studentlist")
	  public String studentlist(Model model)
	  {
		  List<AppliedJob> appliedJob1 = this.appliedJobService.getAllAppliedJobByStudentDetails();
		  model.addAttribute("appliedjobObj1", appliedJob1);
		  return "admin/studentlist";
	  }*/
	  
		/*
		 * @GetMapping("/applicantlist") public String applicantlist(Model model) {
		 * List<AppliedJob> appliedJob1 =
		 * this.appliedJobService.getAllAppliedJobByJobId();
		 * model.addAttribute("appliedjobObj", appliedJob1); return
		 * "admin/applicantlist"; }
		 */
	  
	  
	/*
	 * //<a th:href="@{/applyJobStatus{studentId}(studentId=${session.studentId})}"
	 * 
	 * @GetMapping("/applyJobStatus{studentId}") public String
	 * applyJobStatus(@PathVariable("studentId") int studentId, Model model) {
	 * System.out.println("STUDENT ID-----------------"+studentId); List<AppliedJob>
	 * appliedJob =this.appliedJobService.getAllAppliedJobByStudentId(studentId);
	 * model.addAttribute("appliedjobObj", appliedJob); return
	 * "student/AppliedJobStatus"; }
	 */
	  
	  
	
	  


}	
