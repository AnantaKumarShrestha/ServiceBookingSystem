package com.morgan.project1.servicebookingsystem.service;

import com.morgan.project1.servicebookingsystem.dto.UserDto;
import com.morgan.project1.servicebookingsystem.payload.AuthenticationResponse;
import com.morgan.project1.servicebookingsystem.payload.SignInRequest;

public interface UserService {

    UserDto userSignUp(UserDto userDto);

    AuthenticationResponse userLogin(SignInRequest signInRequest);

}

