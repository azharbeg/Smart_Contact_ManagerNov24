package com.scm.services.imple;

 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.repositeries.UserRepo;

import config.Autowired;

@Service
public class SecurityCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return userRepo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

    }


    
}
