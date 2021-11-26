package com.example.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Table(name = "employee",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
} )

@Entity
public class Employee {
	
	private int privilege;
	
	private int casual;
	
	private int sick;
	
	@Id
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	

	

	

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public int getCasual() {
		return casual;
	}

	public void setCasual(int casual) {
		this.casual = casual;
	}

	public int getSick() {
		return sick;
	}

	public void setSick(int sick) {
		this.sick = sick;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Employee() {
		super();
	}

	public Employee( @NotBlank String username, @NotBlank String password) {
		super();
		
		this.username = username;
		this.password = password;
	}
	
	
	

}
