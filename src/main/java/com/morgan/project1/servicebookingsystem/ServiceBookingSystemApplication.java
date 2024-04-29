package com.morgan.project1.servicebookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class ServiceBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBookingSystemApplication.class, args);
    }

}
