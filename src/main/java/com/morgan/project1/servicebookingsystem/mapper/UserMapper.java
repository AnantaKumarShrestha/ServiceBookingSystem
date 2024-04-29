package com.morgan.project1.servicebookingsystem.mapper;

import com.morgan.project1.servicebookingsystem.dto.UserDto;
import com.morgan.project1.servicebookingsystem.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserModel userDtoIntoUserModel(UserDto userDto);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDto userModelIntoUserDto(UserModel userModel);

    List<UserDto> userModelListIntoUserListDtoList(List<UserModel> userModels);
}
