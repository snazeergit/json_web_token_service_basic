package com.wipro.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserService implements UserDetailsService {

    private final String expectedUsername = "admin";
    private final String hashedPassword;

    public CustomUserService() {
        this.hashedPassword = new BCryptPasswordEncoder().encode("password");
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        if (!expectedUsername.equals(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new User(expectedUsername,
                hashedPassword,
                Collections.emptyList());
    }
}