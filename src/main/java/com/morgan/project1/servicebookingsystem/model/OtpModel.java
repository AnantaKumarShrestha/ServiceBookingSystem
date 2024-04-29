package com.morgan.project1.servicebookingsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "opt")
public class OtpModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String opt;
    private String email;
    @CreationTimestamp
    private LocalDate createdAt;
    private LocalDateTime expireAt;

    public OtpModel(){}

    public OtpModel(String opt,String email,LocalDateTime expireAt){
        this.opt=opt;
        this.email=email;
        this.expireAt=expireAt;
    }

}
