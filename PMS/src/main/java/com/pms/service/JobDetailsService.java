package com.pms.service;

import java.util.List;

import com.pms.entity.JobDetails;

public interface JobDetailsService 
{
	public JobDetails addJobDetails(JobDetails jobdetails);
	
	public List<JobDetails> getAllJobDetails();

	public JobDetails getJobDetailsByjobId(int jobId);

	
}


