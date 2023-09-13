package com.motivity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motivity.dto.AuthRequest;
import com.motivity.dto.AuthResponse;
import com.motivity.dto.RegistrationDto;
import com.motivity.entity.UserRegistration;
import com.motivity.service.UserRegistrationService;
import com.motivity.utility.JwtUtil;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private UserRegistrationService registrationService;
	
	@PostMapping("/newUser")
	public ResponseEntity<UserRegistration> registerUser(@RequestBody RegistrationDto dto){
		return new ResponseEntity<UserRegistration>(registrationService.saveUser(dto), HttpStatus.CREATED);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> getJwtToken(@RequestBody AuthRequest authRequest){
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
				);
		if(authenticate.isAuthenticated()) {
			String token = jwtUtil.generateToken(authRequest.getEmail());
			AuthResponse authResponse = new AuthResponse(authRequest.getEmail(), token);
			return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		}else {
			throw new RuntimeException("Invalid access");
		}
		
	}
	
	@GetMapping("/")
	public String getString() {
		return "hello Security";
	}
	

}
