package com.assessment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String username);
}
