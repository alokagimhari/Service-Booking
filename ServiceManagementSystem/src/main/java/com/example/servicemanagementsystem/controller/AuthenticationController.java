package com.example.servicemanagementsystem.controller;

import com.example.servicemanagementsystem.dto.AuthenticationRequest;
import com.example.servicemanagementsystem.dto.SignupRequestDto;
import com.example.servicemanagementsystem.dto.UserDto;
import com.example.servicemanagementsystem.entity.User;
import com.example.servicemanagementsystem.repository.UserRepo;
import com.example.servicemanagementsystem.services.auth.AuthService;
import com.example.servicemanagementsystem.services.jwt.UserDetailsImpl;
import com.example.servicemanagementsystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {


    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signUpByClient(@RequestBody SignupRequestDto signupRequestDto)
    {
        if(authService.presentByEmails(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Client already exists with the email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser = authService.signupClient(signupRequestDto);

        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signUpByCompany(@RequestBody SignupRequestDto signupRequestDto)
    {
        if(authService.presentByEmails(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Company already exists with the email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser = authService.signupCompany(signupRequestDto);

        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }


    @PostMapping({"/authenticate"})

    public void creteAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                         HttpServletResponse response) throws IOException, JSONException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e)
        {
            throw  new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        User user = userRepo.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getRole())
                .toString());


        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Access-Control-Allow-Headers","Authorization" +
                "X-PINGOTHER,ORIGIN,X-Requested-With, Content-Type,Accept, X-Custom-header");

            response.addHeader(HEADER_STRING,TOKEN_PREFIX +jwt);


    }


}
