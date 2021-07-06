package com.company.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>
{
	  @Query
	   ("select u from User u where u.email=:username")
	  public User findByUsername(@Param("username")String username);

	}