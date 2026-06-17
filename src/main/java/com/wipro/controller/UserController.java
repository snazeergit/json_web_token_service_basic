package com.wipro.controller;
import com.wipro.dto.AuthRequest;
import com.wipro.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    // LOGIN API (POST)
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(request.getUsername());
    }

    // GET API (Protected)
    @GetMapping("/hello")
    public String hello() {
        return "Hello, JWT Secured World!";
    }
}