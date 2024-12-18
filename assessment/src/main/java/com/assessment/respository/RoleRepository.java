package com.assessment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);

}
