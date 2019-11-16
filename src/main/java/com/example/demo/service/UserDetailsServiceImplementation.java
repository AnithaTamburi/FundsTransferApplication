package com.example.demo.service;

import java.util.List;
import java.util.Random;

import javax.validation.Validation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FundTransfer;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ResponseFundTransfer;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.dto.UserLogin;
import com.example.demo.dto.UserRegistrationForm;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.UserException;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.utils.Validations;
import com.google.common.base.Optional;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public ResponseDto userRegistration(UserRegistrationForm userRegistrationForm) {

		Optional<List<UserDetails>> userDetailsMobile = userDetailsRepository
				.findByMobNum(userRegistrationForm.getMobNum());
		if (userDetailsMobile.isPresent() && !userDetailsMobile.get().isEmpty())
			throw new UserException("mobile alredy existed");

		if (!Validations.mobileValid(userRegistrationForm.getMobNum() + ""))
			throw new UserException("Mobile number not valid");

		Optional<List<UserDetails>> userDetailsEmail = userDetailsRepository
				.findByEmail(userRegistrationForm.getEmail());
		if (userDetailsEmail.isPresent() && !userDetailsEmail.get().isEmpty())
			throw new UserException("email allready Exist.");

		int accountNum = Validations.generatePin();

		UserDetails userDetails = new UserDetails();
		userDetails.setAccountNum(accountNum);

		BeanUtils.copyProperties(userRegistrationForm, userDetails);

		userDetailsRepository.save(userDetails);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Added successfully");
		responseDto.setStatusCode(HttpStatus.CREATED.value());

		return responseDto;
	}

	@Override
	public ResponseLogin userLogin(UserLogin userLogin) {

		Optional<List<UserDetails>> loginDetails = userDetailsRepository
				.findByPasswordAndMobNum(userLogin.getPassword(), userLogin.getMobNum());

		if (!loginDetails.isPresent())
			throw new UserException("no user found.");

		ResponseLogin responseLogin = new ResponseLogin();
		responseLogin.setUserId(loginDetails.get().get(0).getUserId());
		responseLogin.setMessage("Login Successfully");
		responseLogin.setStatusCode(HttpStatus.CREATED.value());
		return responseLogin;

	}

	@Override
	public ResponseFundTransfer transaction(FundTransfer fundTransfer) {
		Optional<List<UserDetails>> userDetailsFromAccount = userDetailsRepository
				.findByAccountNum(fundTransfer.getFromAccount());
		if (!userDetailsFromAccount.isPresent() || userDetailsFromAccount.get().isEmpty())
			throw new UserException("account number not found");

		Optional<List<UserDetails>> userDetailsToAccount = userDetailsRepository
				.findByAccountNum(fundTransfer.getToAccount());
		if (!userDetailsToAccount.isPresent() || userDetailsToAccount.get().isEmpty())
			throw new UserException("account number not found");

		if (userDetailsFromAccount.get().get(0).getBalance() < fundTransfer.getAmount())
			throw new UserException("insufficiant balance");

		userDetailsFromAccount.get().get(0)
				.setBalance((userDetailsFromAccount.get().get(0).getBalance() - fundTransfer.getAmount()));

		userDetailsToAccount.get().get(0)
				.setBalance((userDetailsToAccount.get().get(0).getBalance() + fundTransfer.getAmount()));

		userDetailsRepository.save(userDetailsFromAccount.get().get(0));
		userDetailsRepository.save(userDetailsToAccount.get().get(0));

		// enter TransactionHistory table

		TransactionHistory transactionHistory = new TransactionHistory();
		BeanUtils.copyProperties(fundTransfer, transactionHistory);
		transactionHistoryRepository.save(transactionHistory);

		ResponseFundTransfer responseFundTransfer = new ResponseFundTransfer();
		responseFundTransfer.setMessage("transaction succsess");
		responseFundTransfer.setStatusCode(HttpStatus.CREATED.value());
		return responseFundTransfer;
	}

}
