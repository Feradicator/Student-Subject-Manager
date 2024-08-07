package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Admin;
import com.app.model.User;
import com.app.repository.AdminRepository;
import com.app.repository.UserRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository studentRepository;

    public Admin createStudent(Admin admin) {
        return studentRepository.save(admin);
    }

  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        Admin user=studentRepository.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found with email"+username);

        }
        List<GrantedAuthority>authorities=new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}
}
