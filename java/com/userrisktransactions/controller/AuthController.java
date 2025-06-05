package com.userrisktransactions.controller;

import com.userrisktransactions.config.JwtUtil;
import com.userrisktransactions.dto.AuthRequest;
import com.userrisktransactions.dto.UserLoginResponse;
import com.userrisktransactions.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Inside auth request");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        User user = new User();
        user.setUsername(userDetails.getUsername());
        // Set other user properties as needed

        UserLoginResponse response = new UserLoginResponse();
        response.setUser(user);
        response.setToken(token);

        return ResponseEntity.ok(response); // 200 OK

    }
}