 package com.company.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.entities.Contact;
import com.company.entities.User;

public interface ContactRepo extends JpaRepository<Contact,Integer>
{

	
	@Query("from Contact as c where c.user.id=:userId")
	Page<Contact> contactfindById( int userId,Pageable pageable);
	
	
	
	public List<Contact> findByFnameContainingAndUser(String name,User user);
}
