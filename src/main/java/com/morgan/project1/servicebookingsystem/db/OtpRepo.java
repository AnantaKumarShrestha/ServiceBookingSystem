package com.morgan.project1.servicebookingsystem.db;

import com.morgan.project1.servicebookingsystem.model.OtpModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OtpRepo extends JpaRepository<OtpModel, UUID> {
    OtpModel findByEmail(String email);
}
