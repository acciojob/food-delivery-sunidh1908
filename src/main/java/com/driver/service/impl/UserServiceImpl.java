package com.driver.service.impl;

import com.driver.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = UserEntity.builder().id(user.getId()).userId(user.getUserId()).
                firstName(user.getFirstName()).lastName(user.getLastName()).
                email(user.getEmail()).build();
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto userDto = UserConverter.EntityToDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = UserConverter.EntityToDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = user;
        userDto.setId(userEntity.getId());
        userRepository.updateUser(userEntity.getId(), userEntity.getUserId(),
                userEntity.getFirstName(),userEntity.getLastName(),userEntity.getEmail());

        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        List<UserDto> allUsers = new ArrayList<>();
        for(UserEntity users : userEntityList){
            allUsers.add(UserConverter.EntityToDto(users));
        }
        return allUsers;
    }
}