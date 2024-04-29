package com.morgan.project1.servicebookingsystem.dto;


import jakarta.mail.Multipart;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class PostDto {


    private UUID id;

    private String serviceName;

    private String description;

    private Double price;

    private MultipartFile image;

    private Byte[] imageBytes;

    private UUID userId;

    private String companyName;
}
