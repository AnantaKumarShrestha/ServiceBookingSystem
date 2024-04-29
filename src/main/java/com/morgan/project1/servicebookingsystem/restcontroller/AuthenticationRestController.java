package com.morgan.project1.servicebookingsystem.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.morgan.project1.servicebookingsystem.db.UserRepo;
import com.morgan.project1.servicebookingsystem.model.UserModel;
import com.morgan.project1.servicebookingsystem.payload.AuthenticationResponse;
import com.morgan.project1.servicebookingsystem.payload.SignInRequest;
import com.morgan.project1.servicebookingsystem.security.JwtUtils;
import com.morgan.project1.servicebookingsystem.service.CompanyService;
import com.morgan.project1.servicebookingsystem.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AuthenticationRestController {


    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";


    @PostMapping("/authentication")
    public AuthenticationResponse signIn(@RequestBody SignInRequest signIn) {
        return userService.userLogin(signIn);

    }
}
