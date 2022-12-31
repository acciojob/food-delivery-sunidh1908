package com.driver.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserDto EntityToDto(UserEntity userEntity){
        return UserDto.builder().id(userEntity.getId()).userId(userEntity.getUserId()).
                firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).
                email(userEntity.getEmail()).build();
    }

    public static UserResponse DtoToResponse(UserDto userDto){
        return UserResponse.builder().userId(userDto.getUserId()).email(userDto.getEmail()).
                firstName(userDto.getFirstName()).lastName(userDto.getLastName()).build();
    }
}
