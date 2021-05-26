package com.example.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.models.Employee;
import com.example.models.LeaveApplication;

@Repository


public interface LeaveRepository extends JpaRepository<LeaveApplication, String>{
	
	//Optional<LeaveApplication> findById(Long id);
    @Query(value = "select l.username,l.des,l.dt,l.status from leave_application as l join employee as e where l.username=e.username;",nativeQuery = true)
    List<Map<String, Object>> getAllLeave();
    
    @Query(value = "select  l.id,l.des,l.dt,l.username from leave_application as l where l.username=:username",nativeQuery = true)
    List<Map<String, Object>> getLeaveById(@PathVariable("username")String username);
    
    @Query(value = "select count(username) as no_of_leaves , username  from leave_application where username=:username",nativeQuery = true)
    List<Map<String, Object>> getUserName(@PathVariable("username")String username);
    

}
