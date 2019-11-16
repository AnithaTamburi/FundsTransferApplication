package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class UserRegistrationForm {
	
	private String userName;
	private String password;
	private String Address;
	private Long mobNum;
	private String email;
	private double balance;

}
