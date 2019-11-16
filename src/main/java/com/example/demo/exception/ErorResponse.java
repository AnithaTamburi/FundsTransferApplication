package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErorResponse {
	
	private String message;
	private Integer statusCode;
}
