package com.motivity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motivity.entity.UserRegistration;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer>{

	Optional<UserRegistration> findByEmail(String email);
	
}
