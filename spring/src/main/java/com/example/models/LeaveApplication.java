package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.example.models.Employee;





@Entity
@Table(name="LeaveApplication")

public class LeaveApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String username;
	
	@NotNull
	private String des;
	
	@NotNull
	private String dt;
	
	
	
	
	@NotNull
	private int status;
	
	@ManyToOne
	private Employee employee;

	public LeaveApplication() {
		super();
	}

	public LeaveApplication(long id, @NotBlank String username, @NotNull String des, @NotNull String dt,
			
			@NotNull int status, Employee employee) {
		super();
		this.id = id;
		this.username = username;
		this.des = des;
		this.dt = dt;
		
		this.status = status;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}
	
	

	public int getStatus() {
		return status;
	}

	

	public void setStatus(int status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	
	

	
	
}
