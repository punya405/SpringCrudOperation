package com.crud.operation.service;

import com.crud.operation.entity.Employee;
import com.crud.operation.entity.User;
import com.crud.operation.exception.UserNotFound;
import com.crud.operation.jpa.UserRepository;
import com.crud.operation.model.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        ApplicationUserDetails applicationUserDetails;
        if(user!= null) {
            applicationUserDetails = new ApplicationUserDetails(user);
        }else{
            throw  new UserNotFound("User not found");
        }
        return  applicationUserDetails;
    }

    @PostConstruct
    public void init(){
        User user = new User();
        user.setUsername("admin");
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);
        user.setPassword("$2a$10$SMK9fjLGRp0SFKyDApwcdOVBAwF3GVafEzclEfIxPXP6ncbqAe0dm");
        User user1 = new User();
        user1.setUsername("ram");
        user1.setRole("ROLE_USER");
        user1.setEnabled(true);
        user1.setPassword("$2a$10$SMK9fjLGRp0SFKyDApwcdOVBAwF3GVafEzclEfIxPXP6ncbqAe0dm");
        userRepository.saveAll(Arrays.asList(user,user1));
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "pass";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println();
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);
    }
}
