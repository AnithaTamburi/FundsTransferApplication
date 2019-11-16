package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FundTransfer {
	
	private int fromAccount;
	private int toAccount;
	private Double amount;

}
