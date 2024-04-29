package com.morgan.project1.servicebookingsystem.service.ServiceImpl;

import com.morgan.project1.servicebookingsystem.db.OtpRepo;
import com.morgan.project1.servicebookingsystem.enums.Status;
import com.morgan.project1.servicebookingsystem.model.OtpModel;
import com.morgan.project1.servicebookingsystem.payload.OtpRequest;
import com.morgan.project1.servicebookingsystem.payload.Response;
import com.morgan.project1.servicebookingsystem.service.OtpService;
import com.morgan.project1.servicebookingsystem.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private OtpRepo otpRepo;


    @Override
    public String generateOtpNumber() {
        String characters = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4;i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }

    @Override
    public Response sendOtp(OtpRequest otpRequest) {
        String otpNumber = generateOtpNumber();
        otpRepo.save(new OtpModel(otpNumber,otpRequest.getEmail(),LocalDateTime.now().plusMinutes(2)));
        mailUtils.sendEmail(otpRequest.getEmail(),"OTP",otpNumber);
        return new Response(200, Status.SUCCESS);
    }
}
