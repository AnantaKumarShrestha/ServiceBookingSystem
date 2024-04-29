package com.morgan.project1.servicebookingsystem.restcontroller;

import com.morgan.project1.servicebookingsystem.payload.OtpRequest;
import com.morgan.project1.servicebookingsystem.payload.OtpResponse;
import com.morgan.project1.servicebookingsystem.payload.Response;
import com.morgan.project1.servicebookingsystem.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/otp")
public class OtpRestController {

    @Autowired
    private OtpService otpService;

    @PostMapping
    public Response sendOtp(@RequestBody OtpRequest otpRequest){
         return otpService.sendOtp(otpRequest);
    }
}
