package com.morgan.project1.servicebookingsystem.service.ServiceImpl;

import com.morgan.project1.servicebookingsystem.db.UserRepo;
import com.morgan.project1.servicebookingsystem.dto.UserDto;
import com.morgan.project1.servicebookingsystem.enums.Role;
import com.morgan.project1.servicebookingsystem.mapper.UserMapper;
import com.morgan.project1.servicebookingsystem.model.UserModel;
import com.morgan.project1.servicebookingsystem.payload.AuthenticationResponse;
import com.morgan.project1.servicebookingsystem.payload.SignInRequest;
import com.morgan.project1.servicebookingsystem.security.JwtUtils;
import com.morgan.project1.servicebookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserDto userSignUp(UserDto userDto) {
        userDto.setRole(Role.USER);
        UserModel user = userRepo.save(UserMapper.INSTANCE.userDtoIntoUserModel(userDto));
        return UserMapper.INSTANCE.userModelIntoUserDto(user);
    }

    @Override
    public AuthenticationResponse userLogin(SignInRequest signInRequest) {
        UserModel user = userRepo.findByEmailAndPassword(signInRequest.getEmail(), signInRequest.getPassword());
        String jwt = jwtUtils.createToken(user);
        String refresh = jwtUtils.refreshToken(jwt);
        return new AuthenticationResponse(jwt,refresh);
    }


}
