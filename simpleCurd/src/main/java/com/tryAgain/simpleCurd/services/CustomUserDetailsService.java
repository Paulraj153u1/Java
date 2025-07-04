package com.tryAgain.simpleCurd.services;

import com.tryAgain.simpleCurd.entity.UserEntity;
import com.tryAgain.simpleCurd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //fetch use from database

      UserEntity user= userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
