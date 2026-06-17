package com.wipro.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {

        // Hardcoded user for simplicity
        return new User("admin",
                new BCryptPasswordEncoder().encode("password"),
                Collections.emptyList());
    }
}