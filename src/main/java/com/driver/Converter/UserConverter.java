package com.driver.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.shared.dto.UserDto;

public class UserConverter {

    public static UserDto EntityToDto(UserEntity userEntity){
        return UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).
                firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).
                email(userEntity.getEmail()).build();
    }
}
