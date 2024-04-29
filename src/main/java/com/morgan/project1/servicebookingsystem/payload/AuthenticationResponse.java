package com.morgan.project1.servicebookingsystem.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

    private String token;
    private String refreshToken;

    public AuthenticationResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public AuthenticationResponse() {
    }


}
