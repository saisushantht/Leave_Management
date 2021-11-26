package com.example.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.models.LeaveApplication;
import com.example.repository.LeaveRepository;

@Service


public class LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	
	public List<Map<String,Object>> getAll(){
		return leaveRepository.getAllLeave();
		
	}
	
	public LeaveApplication addLeave(LeaveApplication leaveApplication) {
		UserDetailsImpl userdetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	System.out.println(userdetails.getUsername());
    	
    	//leaveApplication.setUsername(userdetails.getUsername());
		return leaveRepository.save(leaveApplication);
		
	}
	
	public List<Map<String,Object>> getUserLeaves(String username){
		UserDetailsImpl userdetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	System.out.println(userdetails.getUsername());
    	username = userdetails.getUsername();
        return leaveRepository.getLeaveById(username);
    }
	
	public List<Map<String,Object>> getUser(String username){
		
        return leaveRepository.getUserName(username);
    }

}
