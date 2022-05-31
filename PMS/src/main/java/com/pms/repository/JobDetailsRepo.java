package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.JobDetails;

public interface JobDetailsRepo extends JpaRepository<JobDetails, Integer>
{

}
