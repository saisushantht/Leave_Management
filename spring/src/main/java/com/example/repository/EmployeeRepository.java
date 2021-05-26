package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Employee;


@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee> findByUsername(String username);
	Boolean existsByUsername(String username);
	

}
