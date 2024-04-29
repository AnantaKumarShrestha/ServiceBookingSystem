package com.morgan.project1.servicebookingsystem.restcontroller;

import com.morgan.project1.servicebookingsystem.dto.UserDto;
import com.morgan.project1.servicebookingsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserDto userSignup(@Valid @RequestBody UserDto userDto){
        return userService.userSignUp(userDto);
    }

    @PostMapping("/as")
    public String userSignupAsAdmin(@Valid @RequestBody UserDto userDto){
        return "asas";
    }

}
