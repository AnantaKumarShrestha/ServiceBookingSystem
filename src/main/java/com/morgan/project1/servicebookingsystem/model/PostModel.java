package com.morgan.project1.servicebookingsystem.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String serviceName;

    private String description;

    private Double price;

    @Lob
    private Byte[] imageBytes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;
}
