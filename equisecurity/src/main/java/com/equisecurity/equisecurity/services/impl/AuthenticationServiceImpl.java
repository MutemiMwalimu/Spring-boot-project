package com.equisecurity.equisecurity.services.impl;


import com.equisecurity.equisecurity.dto.JwtAuthenticationResponse;
import com.equisecurity.equisecurity.dto.SignUpRequest;
import com.equisecurity.equisecurity.dto.SigninRequest;
import com.equisecurity.equisecurity.model.Role;
import com.equisecurity.equisecurity.model.User;
import com.equisecurity.equisecurity.repository.UserRepository;
import com.equisecurity.equisecurity.services.AuthenticationService;
import com.equisecurity.equisecurity.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public User signup(SignUpRequest signUpRequest){
        User user= new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

       return userRepository.save(user);

    }
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).
                orElseThrow(() -> new IllegalArgumentException("invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }
}
