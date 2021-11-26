package com.example.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.ERole;
import com.example.models.Employee;
import com.example.models.Role;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.SignupRequest;
import com.example.payload.request.TypeRequest;
import com.example.payload.response.JwtResponse;
import com.example.payload.response.MessageResponse;
import com.example.payload.response.ReturnRequest;
import com.example.repository.EmployeeRepository;
import com.example.repository.RoleRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		

		return ResponseEntity.ok(new JwtResponse(jwt,  
												 userDetails.getUsername(),
												 roles,true
												 ));
	}
	
	
	
	@RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        
        SecurityContextHolder.clearContext();
        
        
        return "logout";
    }
	////sign up 
			
	
	
	@PostMapping("/update/{username}")
	public void updateuser(@PathVariable String username,@RequestBody TypeRequest type ) {
		Employee emp=employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
		System.out.println(type.getType());
		System.out.println(emp);
		int number=Integer.parseInt(type.getNoofdays());
		
		if(type.getType().equals("casual")) {
			emp.setCasual(emp.getCasual()-number);
		}
		else if(type.getType().equals("sick")) {
			emp.setSick(emp.getSick()-number);
		}
		else {
			emp.setPrivilege(emp.getPrivilege()-number);
		}
		employeeRepository.save(emp);
	}
	
	@PostMapping("/updatemanager/{username}")
	public void updatemanager(@PathVariable String username,@RequestBody TypeRequest type ) {
		Employee emp=employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
		System.out.println(type.getType());
		System.out.println(emp);
		int number=Integer.parseInt(type.getNoofdays());
		
		if(type.getType().equals("casual")) {
			emp.setCasual(emp.getCasual()+number);
		}
		else if(type.getType().equals("sick")) {
			emp.setSick(emp.getSick()+number);
		}
		else {
			emp.setPrivilege(emp.getPrivilege()+number);
		}
		employeeRepository.save(emp);
	}
	
	@GetMapping("/count/{username}")
	public int count(@PathVariable String username) {
		Employee emp=employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		int count=emp.getCasual()+emp.getSick()+emp.getPrivilege();
		
		
		return count;
		
	}
	
	@PostMapping("/check/{username}")
	public boolean check(@PathVariable String username,@RequestBody TypeRequest type) {
		Employee emp=employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		int number=Integer.parseInt(type.getNoofdays());
		
		if(type.getType().equals("casual") && number>emp.getCasual()) {
			return true;
		}
		else if(type.getType().equals("sick") && number>emp.getSick()) {
			return true;
		}
		else if(type.getType().equals("privilege") && number>emp.getPrivilege()) {
			return true;
		}
		return false;
	}
	
	
	
	@PostMapping("/return/{username}")
	public ReturnRequest errormsg(@PathVariable String username,@RequestBody TypeRequest type) {
		Employee emp=employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		String x;
				if(type.getType().equals("casual")) {
					x=String.valueOf(emp.getCasual());
					return new ReturnRequest("You have only "+x+" Casual Leaves Left");
				}
				else if(type.getType().equals("sick")) {
					x=String.valueOf(emp.getSick());
					return new ReturnRequest("You have only "+x+" Sick Leaves Left");
				}
				else {
					x=String.valueOf(emp.getPrivilege());
					return new ReturnRequest("You have only "+x+" Privilege Leaves Left");
				}		
		
	}
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (employeeRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		

		// Create new user's account
		Employee employee = new Employee(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "mang":
					Role adminRole = roleRepository.findByName(ERole.MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "emp":
					Role modRole = roleRepository.findByName(ERole.EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					employee.setCasual(12);
					employee.setPrivilege(15);
					employee.setSick(12);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		employee.setRoles(roles);
		employeeRepository.save(employee);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}