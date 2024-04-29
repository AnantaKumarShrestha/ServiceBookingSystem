package com.morgan.project1.servicebookingsystem.service.ServiceImpl;

import com.morgan.project1.servicebookingsystem.db.UserRepo;
import com.morgan.project1.servicebookingsystem.dto.UserDto;
import com.morgan.project1.servicebookingsystem.enums.Role;
import com.morgan.project1.servicebookingsystem.mapper.UserMapper;
import com.morgan.project1.servicebookingsystem.model.UserModel;
import com.morgan.project1.servicebookingsystem.service.CompanyService;
import com.morgan.project1.servicebookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDto companySignUp(UserDto userDto) {
        userDto.setRole(Role.COMPANY);
        UserModel user = userRepo.save(UserMapper.INSTANCE.userDtoIntoUserModel(userDto));
        return UserMapper.INSTANCE.userModelIntoUserDto(user);
    }
}
