package com.morgan.project1.servicebookingsystem.db;

import com.morgan.project1.servicebookingsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserModel, UUID> {
    UserModel findFirstByEmail(String email);
    Optional<UserModel> findByEmail(String email);

    UserModel findByEmailAndPassword(String email, String password);

    //String findEmailByUsername(String );
}
