package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FundTransfer;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ResponseFundTransfer;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.dto.UserLogin;
import com.example.demo.dto.UserRegistrationForm;
import com.example.demo.service.UserDetailsService;

@RestController
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/users")
	public ResponseEntity<ResponseDto> userRegistration(@RequestBody UserRegistrationForm userRegistrationForm) {
		ResponseDto response = userDetailsService.userRegistration(userRegistrationForm);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/users/login")
	public ResponseEntity<ResponseLogin> userLogin(@RequestBody UserLogin userLogin) {
		ResponseLogin responseLogin = userDetailsService.userLogin(userLogin);
		return new ResponseEntity<>(responseLogin, HttpStatus.CREATED);

	}

	@PostMapping("/users/transaction")
	public ResponseEntity<ResponseFundTransfer> transaction(@RequestBody FundTransfer fundTransfer) {

		ResponseFundTransfer responseFundTransfer = userDetailsService.transaction(fundTransfer);
		return new ResponseEntity<>(responseFundTransfer, HttpStatus.OK);
	}

}
