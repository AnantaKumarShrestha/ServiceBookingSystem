package com.morgan.project1.servicebookingsystem.payload;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;

}
