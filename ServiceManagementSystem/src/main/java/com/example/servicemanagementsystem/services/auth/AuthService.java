package com.example.servicemanagementsystem.services.auth;

import com.example.servicemanagementsystem.dto.SignupRequestDto;
import com.example.servicemanagementsystem.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDto signupRequestDto);
    Boolean presentByEmails(String email);

    UserDto signupCompany(SignupRequestDto signupRequestDto);
}
