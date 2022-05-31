package com.pms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pms.entity.CompanyDetails;
import com.pms.entity.MessagePMS;
import com.pms.service.CompanyDetailsService;

@Controller
public class CompanyDetailsController 
{
	@Autowired
	CompanyDetailsService companyDetailsService;

		 
		@GetMapping("/company-signup")
		public String companySignup(Model model)
		{
			model.addAttribute("companydetails", new CompanyDetails());
			return "company/company-signup";
		}

		@PostMapping("/checkLogin33")
		public String checkLogin33(@ModelAttribute CompanyDetails companydetails,HttpSession session)
		{
			
			CompanyDetails companydetails2 = companyDetailsService.checkLogin33(companydetails.getCompanyEmail(),
						   companydetails.getCompanyContact());
			if(companydetails2!=null)
			{
				session.setAttribute("company", companydetails2);
				session.setAttribute("companyId", companydetails2.getCompanyId());
				return "company/company-dashboard";
			}
			else
			{
				return "company/company-login";
			}
		}
		
		@GetMapping("/company-login")
		public String companyLogin()
		{
			return "company/company-login";

		}


		@PostMapping("/registerCompany")
		public String registerCompany(@Valid @ModelAttribute CompanyDetails companydetails, BindingResult result, 
				@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,Model model, HttpSession session,
				 @RequestParam("CompanyLogoImage") MultipartFile file)
		{
			System.out.println(agreement);
				try
				{
					
				 if(result.hasErrors())
				 {
					model.addAttribute("companydetails", companydetails);
					 return "company/company-signup";
				 }
		
					if(agreement)
					{
						if (file.isEmpty()) 
						{
							System.out.println("File is Empty !!");
						} 
						
						else
						{
							companydetails.setCompanyLogo(file.getOriginalFilename());
							File saveFile = new ClassPathResource("static/image").getFile();
							Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
							Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
							System.out.println("File is Uploaded");

						}
						
						session.setAttribute("message1", new MessagePMS("Company Logged in Successfully !","alert-success"));
						this.companyDetailsService.addCompanyDetails(companydetails);
						return "company/company-login";
					}
					else
					{
						session.setAttribute("message1", new MessagePMS("Company is Not Logged in Successfully !!!","alert-danger"));
						throw new Exception("Kindly accept the terms and conditions !");
					}
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}

				
				return "company/company-signup";
		}
		
		// View Company Profile
		@GetMapping("/ViewCompanyProfile")
		public String companyProfile(Model model) 
		{
			return "company/ViewCompanyProfile";

		}
		

		@GetMapping("/UpdateCompany{companyId}")
		public String editcompanyForm1(@PathVariable("companyId") int companyId, Model model) {
			System.out.println("update ----------" + companyId);
			model.addAttribute("companydetails", companyDetailsService.getCompanyDetailsBycompanyId(companyId));
			return "company/UpdateCompany";								
											
		}

		@PostMapping("/saveUpdateCompany{companyId}")
		public String updateCompany(@ModelAttribute CompanyDetails companydetails,HttpSession session, 
				@PathVariable("companyId") int companyId, @RequestParam("companyUploadLogo") MultipartFile file)
				throws IOException {
			if (file.isEmpty()) {
				System.out.println("File is Empty !!");
				//companydetails.setCompanyLogo("defaultimage.png");
			} else {
				System.out.println("uploading file ---------------------");
				companydetails.setCompanyLogo(file.getOriginalFilename());
				File saveFile = new ClassPathResource("static/image").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File is Uploaded");
			}
			this.companyDetailsService.updateCompany(companydetails, companyId);
			session.setAttribute("message1", new MessagePMS("Company Details Updated Successfully", "alert-success"));
			return "company/ViewCompany";

		}
}

