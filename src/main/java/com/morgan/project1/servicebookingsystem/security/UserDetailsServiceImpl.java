package com.morgan.project1.servicebookingsystem.security;

import com.morgan.project1.servicebookingsystem.db.UserRepo;
import com.morgan.project1.servicebookingsystem.model.UserModel;
import com.morgan.project1.servicebookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
