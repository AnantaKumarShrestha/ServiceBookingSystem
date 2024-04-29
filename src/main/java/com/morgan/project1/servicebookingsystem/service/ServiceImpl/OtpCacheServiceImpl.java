package com.morgan.project1.servicebookingsystem.service.ServiceImpl;

import com.morgan.project1.servicebookingsystem.db.UserRepo;
import com.morgan.project1.servicebookingsystem.service.UserService;
import com.morgan.project1.servicebookingsystem.utils.MailUtils;
import com.morgan.project1.servicebookingsystem.utils.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class OtpCacheServiceImpl {

    @Autowired
    private OtpGenerator otpGenerator;


    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private UserRepo userRepo;


    public Boolean generateOtp(String key)
    {
        // generate otp
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1)
        {
            return  false;
        }
        mailUtils.sendEmail(key,"emailSubject","emailMessage" + otpValue);
        return true;
    }

    public Boolean validateOTP(String key, Integer otpNumber)
    {
        Integer cacheOTP = otpGenerator.getOPTByKey(key);
        if (cacheOTP!=null && cacheOTP.equals(otpNumber))
        {
            otpGenerator.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
}
