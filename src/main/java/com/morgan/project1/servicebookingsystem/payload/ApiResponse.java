package com.morgan.project1.servicebookingsystem.payload;

import com.morgan.project1.servicebookingsystem.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @Column(nullable = false, length = 100)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Status status;
}
