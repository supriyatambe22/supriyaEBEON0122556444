package com.pms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pms.entity.Admin;
import com.pms.entity.MessagePMS;
import com.pms.entity.StudentDetails;
import com.pms.repository.StudentDetailsRepo;
import com.pms.service.EmailService;
import com.pms.service.StudentDetailsService;

@Controller
@ControllerAdvice
public class StudentDetailsController 
{
	@Autowired
	private EmailService emailService;
	
	@Autowired
	StudentDetailsService studentdetailsService;

	@Autowired
	StudentDetailsRepo studentDetailsRepo;

	Random random = new Random(1000);

	
	 @GetMapping("/home") 
	 public String homePage() 
	 { 
		 return "home"; 
	 }
	 
	
	@GetMapping("/home11")
	public String home11() {
		return "home11";
	}

	@GetMapping("/student-signup")
	public String studentsignup(Model model) 
	{
		model.addAttribute("studentdetails", new StudentDetails());
		return "student/student-signup";

	}
	

	@GetMapping("/student-login")
	public String studentlogin() 
	{
		return "student/student-login";

	}

	
	
	
	@PostMapping("/checkLogin10")
	public String checkLogin1(@ModelAttribute StudentDetails studentdetails, HttpSession session)
	{
		
		StudentDetails studentdetails2 = studentdetailsService.checkLogin10(studentdetails.getStudentEmail(),
				studentdetails.getStudentContact());
		if(studentdetails2!=null)
		{
		    session.setAttribute("student", studentdetails2);
		    session.setAttribute("studentId", studentdetails2.getStudentId());
			return "student/student-dashboard";
		}
		else
		{
			
			return "student/student-login";
		}
	}
	
	/*
	 * @GetMapping("/student-login") public String studentlogin() { return
	 * "student/student-login";
	 * 
	 * }
	 */




	/*
	 * @PostMapping("/checkLogin1") public String checkLogin1(@ModelAttribute
	 * StudentDetails studentdetails, HttpSession session) {
	 * System.out.println("LoginPage --------------------------");
	 * 
	 * 
	 * 
	 * 
	 * //System.out.println("UserName -----------"_+studentdetails); StudentDetails
	 * studentdetails2 =
	 * studentdetailsService.checkLogin1(studentdetails.getStudentEmail(),
	 * studentdetails.getStudentContact());
	 * System.out.println("message1 ----------------------------------"); if
	 * (studentdetails2 != null) {
	 * System.out.println("message2 ----------------------------------");
	 * System.out.println("Login-------  " + studentdetails2);
	 * session.setAttribute("student", studentdetails2);
	 * session.setAttribute("studentId", studentdetails2.getStudentId()); return
	 * "student/student-dashboard"; } else {
	 * System.out.println("message3 ----------------------------------"); return
	 * "student/student-login"; } }
	 */

	  @RequestMapping("/forgot")
	  public String forgotPwd() 
	  {
		  return "forgotpassword1"; 
	  }
	  
	  @PostMapping("/send-otp") 
	  public String sendOTP(@RequestParam("studentEmail") String studentEmail, HttpSession session) 
	  { 
		  System.out.println("EMAIL "+studentEmail);
		  
		  //generating otp of 4 digit
		  
		  int otp = random.nextInt(9999);
		  
		  System.out.println("OTP "+otp);
		  
		  //write code for send otp to emial
		  
		  String subject = "OTP From PMC";
		  String message = "<h1> OTP = "+otp+" </h1> ";
		  String to = studentEmail;
		  String from = "tambesa26@gmail.com";
		  boolean flag = this.emailService.sendEmail(subject, message, to, from);
		  if(flag)
		  {
			  session.setAttribute("myotp",otp);
			  session.setAttribute("studentEmail", studentEmail);
			  return "verify_otp";
		  }
		  else
		  {
			  session.setAttribute("message", new MessagePMS("Check Your Email ID !!","alert-warning") );
			  return "forgotpassword1"; 
		  }
 
	  }
	  
	  //verify otp
	  
	  @PostMapping("/verify-otp")
	  public String verifyOtp(@RequestParam("otp")Integer otp, HttpSession session)
	  {
		  int myOtp = (int)session.getAttribute("myotp");
		  //System.out.println("Student OTP "+otp);
		  //System.out.println("Our OTP "+myOtp);
		 
		  String studentemail = (String)session.getAttribute("studentEmail");
		  if(myOtp==otp) 
		  {
			  StudentDetails studetails = this.studentdetailsService.checkEmail(studentemail);
			  if(studetails==null)
			  {
				  //send error message
				  session.setAttribute("message", new MessagePMS("Student does not exits with this Email ID !!","alert-success"));
				  return "forgotpassword1"; 
			  }
			  else
			  {
				  //send change password form
				  return "change_password";
			  }
			  
			
		  }
		  else
		  {
			  session.setAttribute("message", new MessagePMS("You have entered Wrong OTP !!!","alert-warning"));
			  return "verify_otp";
		  }
		  
	  }
	 
	  @PostMapping("/change-password")
	  public String chnagePassword(@RequestParam("studentContact") String studentContact,HttpSession session,
			  					   @ModelAttribute StudentDetails studentdetails)
	  {
		  String studentemail = (String)session.getAttribute("studentEmail");
		  StudentDetails studetails = this.studentdetailsService.checkEmail(studentemail);
		  studetails.setStudentContact(studentdetails.getStudentContact());
		  this.studentDetailsRepo.save(studetails);
		  session.setAttribute("message", new MessagePMS("Password Update Successfully","alert-primary"));
		  return "student/student-login";
		  
	  }
	  

	@GetMapping("/UpdateStudentDetails{studentId}")
	public String editstudentForm(@PathVariable int studentId, Model model) {
		System.out.println("update ----------" + studentId);
		model.addAttribute("studentdetails", studentdetailsService.getStudentDetailsBystudentId(studentId));
		return "student/UpdateStudentDetails";

	}

	@PostMapping("/saveUpdateStudentDetails{studentId}")
	public String updateStudentDetails(@ModelAttribute StudentDetails studentdetails,
			@PathVariable("studentId") int studentId, @RequestParam("studentUploadImage") MultipartFile file)
			throws IOException {
		if (file.isEmpty()) {
			System.out.println("File is Empty !!");
			studentdetails.setStudentPhoto("defaultimage.png");
		} else {
			System.out.println("uploading file ---------------------");
			studentdetails.setStudentPhoto(file.getOriginalFilename());
			File saveFile = new ClassPathResource("static/image").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File is Uploaded");
		}
		this.studentdetailsService.updateStudentDetails(studentdetails, studentId);
		return "redirect:/ViewStudentDetails";

	}

	@GetMapping("/DeleteStudentDetails{studentId}")
	public String deleteStudentDetails(@PathVariable("studentId") int studentId,HttpSession session) {
		studentdetailsService.deleteStudentDetailsBystudentId(studentId);
		session.setAttribute("message1",new MessagePMS("student deleted successfully","alert-success"));

		return "redirect:/ViewStudentDetails";

	}

	@PostMapping("/registerStudent")
	public String register(@Valid @ModelAttribute StudentDetails studentdetails, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session, @RequestParam("studentUploadImage") MultipartFile file) throws IOException

	{
		System.out.println(agreement);
		try 
		{
			
			System.out.println("studentdetauiskkas----------------------"+studentdetails);
			//model.addAttribute("studentdetails", studentdetails);
			if (result.hasErrors()) 
			{
				System.out.println("msg 1..........." + result);
				model.addAttribute("studentdetails", studentdetails);
				return "student/student-signup";
			}

			if (agreement) 
			{

				if (file.isEmpty()) 
				{
					System.out.println("File is Empty !!");
				} 
				
				else
				{
					studentdetails.setStudentPhoto(file.getOriginalFilename());
					File saveFile = new ClassPathResource("static/image").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("File is Uploaded");

				}
				System.out.println("msg 2...........");
				//model.addAttribute("studentdetails", studentdetails);
				session.setAttribute("message1", new MessagePMS("User Is Registered Successfully !", "alert-success"));
				this.studentdetailsService.addStudentDetails(studentdetails);
				return "student/student-login";
			} 
			else
			{
				System.out.println("msg 3...........");
				session.setAttribute("message1", new MessagePMS("User Is Not Registered !!!", "alert-danger"));
				throw new Exception("Kindly accept the terms and conditions !");
			}
		} 
		
		catch (Exception e) 
		{
			System.out.println(e);
		}

		//System.out.println("msg 4...........");
		return "student/student-signup";
	}

	// All Record
	@GetMapping("/ViewStudentDetails")
	public String ViewStudentDetails(Model model) {
		List<StudentDetails> studentdeatils1 = this.studentdetailsService.getAllStudentDetails();
		model.addAttribute("sdObj", studentdeatils1);
		return "student/ViewStudentDetails";

	}

	// Singal Record
	@GetMapping("/ViewStudent{studentId}")
	public String ViewStudent(@PathVariable("studentId") int studentId, Model model) {
		System.out.println("student Id-----------------------------------" + studentId);
		StudentDetails studentdeatils1 = this.studentdetailsService.getStudentDetailsBystudentId(studentId);
		model.addAttribute("studentdetails", studentdeatils1);
		return "student/ViewStudent";

	}

	// View student Profile
	@GetMapping("/ViewProfile")
	public String yourProfile(Model model) {
		return "student/ViewProfile";

	}

	@RequestMapping("/UploadStudentResume")
	public String UploadSResume(Model model) {
		return "student/UploadStudentResume";

	}

	@PostMapping("/UploadResume{studentId}")
	public String upload(@PathVariable("studentId") int studentId, Model model,
			@RequestParam("studentUploadResume") MultipartFile file, HttpSession session) throws IOException {
		StudentDetails studetails = this.studentdetailsService.getStudentDetailsBystudentId(studentId);
		if (file.isEmpty()) {
			System.out.println("File is Empty !!");
		} else

		{
			studetails.setStudentResume(file.getOriginalFilename());
			File saveFile = new ClassPathResource("static/image").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File is Uploaded");

		}
		session.setAttribute("message1", new MessagePMS("Resume Uploaded Successfully", "alert-success"));
		this.studentdetailsService.addStudentDetails(studetails);
		return "student/UploadStudentResume";

	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleResumeUploadError(RedirectAttributes ra, HttpSession session) {
		System.out.println("Caught File Upload Error");
		session.setAttribute("message", new MessagePMS("File Size Is Greater Than 1MB", "alert-danger"));
		return "student/UploadStudentResume";

	}

	@GetMapping("/SearchStudentDetails")
	public String add(Model model) 
	{
		List<StudentDetails> liststudents = studentdetailsService.listAll();
		model.addAttribute("studentdetails", new StudentDetails());
		return "student/SearchStudentDetails";
	}

	@PostMapping("/SearchStudentDetails")
	public String doSearchStudent(@ModelAttribute("studentSearchFormData") StudentDetails formData, Model model) 
	{
		StudentDetails studtl = studentdetailsService.get(formData.getStudentId());
		model.addAttribute("studentdetails", studtl);
		return "student/SearchStudentDetails";

	}
	
	@GetMapping("/CompanySearchStudentDetails")
	public String add1(Model model) 
	{
		List<StudentDetails> liststudents = studentdetailsService.listAll();
		model.addAttribute("studentdetails", new StudentDetails());
		return "student/CompanySearchStudentDetails";
	}

	@PostMapping("/CompanySearchStudentDetails")
	public String doSearchStudent1(@ModelAttribute("studentSearchFormData") StudentDetails formData, Model model) 
	{
		StudentDetails studtl = studentdetailsService.get(formData.getStudentId());
		model.addAttribute("studentdetails", studtl);
		return "student/CompanySearchStudentDetails";

	}

	@GetMapping("/addstudent")
	public String addstudent(Model model) {
		 
		
		model.addAttribute("studentdetails", new StudentDetails());
		return "student/addstudent";
		
	}
	
	@PostMapping("/studentprocess")
	public String studentProcess(@ModelAttribute StudentDetails studentdetails,@ModelAttribute Admin admin,
			@RequestParam("studentUploadImage") MultipartFile file,HttpSession session) throws IOException
	{
		//System.out.println("------------"+rollNo);
		if(file.isEmpty()) {
			System.out.println("file is empty");
			studentdetails.setStudentPhoto("defaultimage.png");
			session.setAttribute("message1",new MessagePMS("student is not added !!","alert-danger"));
		}
		else {
			studentdetails.setStudentPhoto(file.getOriginalFilename());
			File save=new ClassPathResource("static/image").getFile();
			Path path=Paths.get(save.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("file is uploaded");
		}
		//studentlogindetails.setRollNo(adminId);
		this.studentdetailsService.addStudentDetails(studentdetails);
		
		System.out.println("added to database");
		session.setAttribute("message1",new MessagePMS("student added successfully!!","alert-success"));

		
		return "student/addstudent";
	}
	
	@GetMapping("/UpdateStudent{studentId}")
	public String editstudentForm1(@PathVariable("studentId") int studentId, Model model) {
		System.out.println("update ----------" + studentId);
		model.addAttribute("studentdetails", studentdetailsService.getStudentDetailsBystudentId(studentId));
		return "student/UpdateStudent";

	}

	@PostMapping("/saveUpdateStudent{studentId}")
	public String updateStudent(@ModelAttribute StudentDetails studentdetails,HttpSession session, 
			@PathVariable("studentId") int studentId, @RequestParam("studentUploadImage") MultipartFile file)
			throws IOException {
		if (file.isEmpty()) {
			System.out.println("File is Empty !!");
			studentdetails.setStudentPhoto("defaultimage.png");
		} else {
			System.out.println("uploading file ---------------------");
			studentdetails.setStudentPhoto(file.getOriginalFilename());
			File saveFile = new ClassPathResource("static/image").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File is Uploaded");
		}
		this.studentdetailsService.updateStudent(studentdetails, studentId);
		session.setAttribute("message1", new MessagePMS("Student Details Updated Successfully", "alert-success"));
		return "redirect:/ViewStudent";

	}

	@GetMapping("/changepass")
	public String change_pass()
	{
		return "student/changePassword";
	}
	
	@PostMapping("/changepass{studentId}")
	public String changepass(@PathVariable("studentId")int studentId,@ModelAttribute StudentDetails studentdetails,HttpSession session)
	{
		StudentDetails studetail=this.studentdetailsService.getStudentDetailsBystudentId(studentId);
		studetail.setStudentContact(studentdetails.getStudentContact());
		this.studentDetailsRepo.save(studetail);
		session.setAttribute("message1",new MessagePMS("Password Changed successfully  !!!","alert-success"));
		return "student/changePassword";
	}
	
	/*
	 * @GetMapping("/show-studentdetails/{page}") public String
	 * showStudentDetails( @PathVariable("page") Integer page, Model model,
	 * Principal principal) { String studentName = principal.getName();
	 * StudentDetails studentdetails = this.studentDetailsRepo. }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
