package com.example.servicemanagementsystem.services.auth;

import com.example.servicemanagementsystem.dto.UserDto;
import com.example.servicemanagementsystem.entity.User;
import com.example.servicemanagementsystem.enums.UserRole;
import com.example.servicemanagementsystem.repository.UserRepo;
import com.example.servicemanagementsystem.dto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    public UserDto signupClient(SignupRequestDto signupRequestDto) {
        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setLastname(signupRequestDto.getLastname());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setRole(UserRole.CLIENT);

        return userRepo.save(user).getDto();

    }

    public Boolean presentByEmails(String email) {
        return userRepo.findFirstByEmail(email) != null;
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto) {
        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setRole(UserRole.COMPANY);

        return userRepo.save(user).getDto();

    }
}
