package com.motivity.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.motivity.dto.RegistrationDto;
import com.motivity.entity.UserRegistration;
import com.motivity.repository.UserRegistrationRepository;

@Service
public class UserRegistrationService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRegistrationRepository registrationRepository;
	
	public UserRegistration saveUser(RegistrationDto registerUser) {
		UserRegistration user = DtoChangingToEntity(registerUser);
		user.setPassword(encoder.encode(user.getPassword()));
		return registrationRepository.save(user);
	}

	private UserRegistration DtoChangingToEntity(RegistrationDto registerUser) {
		ModelMapper mapper = new ModelMapper();
		UserRegistration user1 = mapper.map(registerUser, UserRegistration.class);
		return user1;
	}

}
