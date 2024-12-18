package com.assessment.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoder {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "pass";
		String encodedPassword = encoder.encode(password);
		System.out.println("Password : " + password + ", Encoded pass  : " + encodedPassword);
	
	}

}
