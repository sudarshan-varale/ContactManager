package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.company.dao.UserRepository;
import com.company.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
  {
	 User user=  userRepository.findByUsername(username);
	 if(user==null)
	 {
        throw new UsernameNotFoundException("User Could not found!!"); 
        
	 }
	   CaustomUserDetails cs=new CaustomUserDetails(user);
		return cs;
	}

}
