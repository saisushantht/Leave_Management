package com.example.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.models.Employee;


@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee> findByUsername(String username);
	Boolean existsByUsername(String username);
	
	@Query(value = "select e.username,e.sick,e.casual,e.privilege from employee as e where e.username=:username",nativeQuery = true)
    List<Map<String, Object>> getUserName(@Param("username")String username);
	
	
	
}
