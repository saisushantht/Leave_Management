package com.example.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.models.Employee;
import com.example.models.LeaveApplication;

@Repository


public interface LeaveRepository extends JpaRepository<LeaveApplication, String>{
	
	//Optional<LeaveApplication> findById(Long id);
    @Query(value = "select * from leave_application ",nativeQuery = true)
    List<Map<String, Object>> getAllLeave();
    
    @Query(value = "select  l.id,l.des,l.dt,l.username,l.status from leave_application as l where l.username=:username",nativeQuery = true)
    List<Map<String, Object>> getLeaveById(@Param("username")String username);
    
    @Query(value = "select count(username) as no_of_leaves , username  from leave_application where username=:username",nativeQuery = true)
    List<Map<String, Object>> getUserName(@Param("username")String username);
    

}
