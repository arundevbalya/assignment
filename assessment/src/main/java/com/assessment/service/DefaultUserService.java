package com.assessment.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.assessment.model.User;
import com.assessment.model.UserDTO;



public interface DefaultUserService extends UserDetailsService{
	User save(UserDTO userRegisteredDTO);

}
