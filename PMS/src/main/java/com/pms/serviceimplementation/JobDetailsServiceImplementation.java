package com.pms.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.JobDetails;
import com.pms.repository.JobDetailsRepo;
import com.pms.service.JobDetailsService;

@Service
public class JobDetailsServiceImplementation implements JobDetailsService
{
	@Autowired
	JobDetailsRepo jobDetailsRepo;

	@Override
	public JobDetails addJobDetails(JobDetails jobdetails)
	{
		return  this.jobDetailsRepo.save(jobdetails);
		
	}

	@Override
	public List<JobDetails> getAllJobDetails() 
	{
		return jobDetailsRepo.findAll();
	}

	@Override
	public JobDetails getJobDetailsByjobId(int jobId) {
		// TODO Auto-generated method stub
		
		return this.jobDetailsRepo.findById(jobId).get();
	}

	/*
	 * @Override public List<JobDetails> getAllJobDetailsBycompanyId(int companyId)
	 * { // TODO Auto-generated method stub return
	 * this.jobDetailsRepo.getAllJobDetailsBycompanyId(companyId); }
	 */



}

