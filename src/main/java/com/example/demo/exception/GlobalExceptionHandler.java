package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

	

		@ExceptionHandler(UserException.class)
		public ResponseEntity<ErorResponse> userExceptionHandler(UserException ex, WebRequest request) {

			ErorResponse errorResponse=new ErorResponse();
			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

		}
		


		@ExceptionHandler(Exception.class)
		public ResponseEntity<ErorResponse> globelHandler(Exception ex, WebRequest request) {

			ErorResponse errorResponse=new ErorResponse();
			errorResponse.setMessage(ex.getMessage());
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

		}

		
}
