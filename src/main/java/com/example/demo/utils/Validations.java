package com.example.demo.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.exception.UserException;


public class Validations {
	
	public static int generatePin()  {
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		  
		int num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
		num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
		throw new UserException("Unable to generate PIN at this time..");
		}
		}
		return num;
					
	}
	
	public static boolean mobileValid(String s) 
    { 
        
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
        Matcher m = p.matcher(s); 
        return (m.find() && m.group().equals(s)); 
    } 
}