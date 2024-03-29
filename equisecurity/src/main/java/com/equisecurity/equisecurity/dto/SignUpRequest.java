package com.equisecurity.equisecurity.dto;

import com.equisecurity.equisecurity.model.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;
    private  String lastName;
    private String email;
    private String password;
    private Role role;
}
