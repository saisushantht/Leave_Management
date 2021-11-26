package com.example.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.LeaveApplication;
import com.example.repository.EmployeeRepository;
import com.example.security.services.LeaveService;
import com.example.security.services.UserDetailsImpl;
import com.example.security.services.UserDetailsServiceImpl;

@RequestMapping
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class LeaveController {
	@Autowired
    private LeaveService leaveService;
	
	@Autowired
	private EmployeeRepository emp;

	@Autowired
	private AuthController authcontroller;
	
    @GetMapping("/getAll")
    public List<Map<String, Object>> allAccess() {
        return leaveService.getAll();
    }
    
    @PostMapping("/addLeave")
    public List<String> addLeave(@RequestBody LeaveApplication leaveApplication){
    	  leaveService.addLeave(leaveApplication);
    	  return List.of("Leave Application Submit");
    	
    }
    @GetMapping("/getLeaves/{username}")
    public List<Map<String,Object>> findById(@PathVariable String username){
        return leaveService.getUserLeaves(username);
    }
    
    
    
    @GetMapping("/home/{username}")
    public List<Map<String,Object>> findByUser(@PathVariable String username){
    	
    	
    	
    	return leaveService.getUser(username);
    }
    
    @GetMapping("/employee/{username}")
    public List<Map<String,Object>> findByUsername(@PathVariable String username){
    	
    	
    	
    	return emp.getUserName(username);
    }
    
	

}
