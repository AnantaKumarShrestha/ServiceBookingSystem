package com.morgan.project1.servicebookingsystem.service;


import com.morgan.project1.servicebookingsystem.payload.OtpRequest;
import com.morgan.project1.servicebookingsystem.payload.Response;

public interface OtpService {

    String generateOtpNumber();

    public Response sendOtp(OtpRequest otpRequest);

}
