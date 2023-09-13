package com.motivity.dto;

import com.motivity.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private long phoneNumber;
	private Address address;

}
