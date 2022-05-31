package com.pms.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.AppliedJob;
import com.pms.repository.AppliedJobRepo;
import com.pms.service.AppliedJobService;

@Service
public class AppliedJobServiceImplementation implements AppliedJobService
{
	@Autowired
	AppliedJobRepo appliedJobRepo;

	@Override
	public List<AppliedJob> getAllAppliedJob() {
		// TODO Auto-generated method stub
		return appliedJobRepo.findAll();
	}

	@Override
	public void applyJob(AppliedJob appliedJob) {
		// TODO Auto-generated method stub
		this.appliedJobRepo.save(appliedJob);
		
	}

	@Override
	public List<AppliedJob> getAllAppliedJobByappliedjobId(int appliedjobId) {
		// TODO Auto-generated method stub
		return appliedJobRepo.findAll();
	}

	@Override
	public List<AppliedJob> getAllAppliedJobByStudentId(int studentId) 
	{
		return appliedJobRepo.getAppliedJobsByStudentId(studentId);

	}

	@Override
	public List<AppliedJob> getAllAppliedJobByStudentDetails() {
		// TODO Auto-generated method stub
		return appliedJobRepo.findAll();
	}

	@Override
	public List<AppliedJob> getAllAppliedJobByJobDetails() {
		// TODO Auto-generated method stub
		return appliedJobRepo.findAll();
	}




}
