package com.company;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.dao.ContactRepo;
import com.company.dao.UserRepository;
import com.company.entities.Contact;
import com.company.entities.User;


@RestController
public class SearchController
{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ContactRepo contactRepo;
	@GetMapping("/search/{query}")
	@ResponseBody
 public ResponseEntity<?> search(@PathVariable("query") String query,Principal p)
 {
	 System.out.println("guu");
	User user=	userRepository.findByUsername(p.getName());
	    
	List<Contact> contacts= this.contactRepo.findByFnameContainingAndUser(query, user);
	 
	return ResponseEntity.ok(contacts);
	 
 }
	@GetMapping("/demo")
	public String demo()
	{
		
		return"";
	}
	
	
	
	
	
	
	
	
	
}
