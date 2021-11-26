package com.example.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Employee;

import com.example.repository.EmployeeRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
//	@Transactional
//	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
//		Employee user = employeeRepository.findById(id)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + id));
//
//		return UserDetailsImpl.build(user);
//	}
//
//	//GET USER BY ID
//	 public Optional<Employee> findById(Long id) { 
//		 return employeeRepository.findById(id);
//	 }

}