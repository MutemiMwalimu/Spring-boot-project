package com.equisecurity.equisecurity.services;

import com.equisecurity.equisecurity.dto.JwtAuthenticationResponse;
import com.equisecurity.equisecurity.dto.SignUpRequest;
import com.equisecurity.equisecurity.dto.SigninRequest;
import com.equisecurity.equisecurity.model.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

}
