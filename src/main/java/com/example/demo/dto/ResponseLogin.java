package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ResponseLogin {
	
	private int userId;
	private String message;
	private Integer statusCode;

}
