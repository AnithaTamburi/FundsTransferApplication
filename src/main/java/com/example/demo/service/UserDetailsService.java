package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FundTransfer;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ResponseFundTransfer;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.dto.UserLogin;
import com.example.demo.dto.UserRegistrationForm;

@Service
public interface UserDetailsService  {
	
	public ResponseDto userRegistration(UserRegistrationForm userRegistrationForm);
	
	public ResponseLogin userLogin(UserLogin userLogin);
	
	public ResponseFundTransfer transaction(FundTransfer fundTransfer);
	

}
