package com.motivity.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.motivity.entity.UserRegistration;
import com.motivity.repository.UserRegistrationRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRegistrationRepository registrationRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserRegistration> user = registrationRepository.findByEmail(email);
		return user.map(CustomUserDetails::new)
				.orElseThrow( () -> new RuntimeException("Email is not found"));
	}

}
