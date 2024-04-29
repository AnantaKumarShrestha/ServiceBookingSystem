package com.morgan.project1.servicebookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.morgan.project1.servicebookingsystem.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID id;
    @NotEmpty
    private String fName;
    private String lName;
    @Column()
    private String phone;
    private String Address;
    @Email
    private String email;
    private String password;
    private Role role;
}
