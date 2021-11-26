package com.example.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.example.models.Employee;





@Entity
@Table(name="LeaveApplication")

@IdClass(CompositeKey.class)
public class LeaveApplication {
	
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Id
	private String username;
	
	@NotNull
	private String des;
	
	@Id
	private String dt;
	
	private String Type;
	
	private int Noofdays;
	
	
	
	@NotNull
	private String status;
	
	

	public LeaveApplication() {
		super();
	}

	public LeaveApplication(@NotBlank String username, @NotNull String des, @NotNull String dt,
			
			@NotNull String status, String Type,int noofdays) {
		super();
		this.id = id;
		this.username = username;
		this.des = des;
		this.dt = dt;
		this.Type=Type;
		this.status = status;
		this.Noofdays=noofdays;
		
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
	
	

	public String getStatus() {
		return status;
	}

	

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getNoofdays() {
		return Noofdays;
	}

	public void setNoofdays(int noofdays) {
		Noofdays = noofdays;
	}
	
	

	
	
	

	
	

	
	
}
