package com.pms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pms.entity.AppliedJob;



public interface AppliedJobRepo extends JpaRepository<AppliedJob, Integer>
{
														/* studentdetails_student_id */
	@Query(value = "SELECT * FROM applied_job aj WHERE aj.studentdetails_student_id = :sid",nativeQuery = true)
	public List<AppliedJob> getAppliedJobsByStudentId( @Param("sid")int sid);

	

	
}
