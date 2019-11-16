package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDetails;
import com.google.common.base.Optional;

public interface UserDetailsRepository  extends JpaRepository<UserDetails, Integer>{
	
	  Optional<List<UserDetails>> findByMobNum(Long mobNum);
	  Optional<List<UserDetails>> findByEmail(String email);
	  Optional<List<UserDetails>> findByPasswordAndMobNum(String password,Long mobNum);
	  
	  Optional<List<UserDetails>> findByAccountNum(int accountNum);
}
