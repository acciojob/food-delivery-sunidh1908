package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Converter.UserConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto = userService.getUser(id);
		UserResponse userResponse = UserConverter.DtoToResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserDto.builder().email(userDetails.getEmail()).firstName(userDetails.getFirstName()).
				lastName(userDetails.getLastName()).build();
		UserDto userDto1 = userService.createUser(userDto);
		UserResponse userResponse = UserConverter.DtoToResponse(userDto1);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserDto.builder().email(userDetails.getEmail()).firstName(userDetails.getFirstName()).
				lastName(userDetails.getLastName()).build();
		UserDto userDto1 = userService.updateUser(id,userDto);
		UserResponse userResponse = UserConverter.DtoToResponse(userDto1);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel = OperationStatusModel.builder().
				operationName(String.valueOf(RequestOperationName.DELETE)).
				operationResult(RequestOperationStatus.SUCCESS.name()).build();
		userService.deleteUser(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> userDtos = userService.getUsers();
		List<UserResponse> allUsers = new ArrayList<>();

		for (UserDto userDto : userDtos){
			allUsers.add(UserConverter.DtoToResponse(userDto));
		}
		return allUsers;
	}
	
}
