package com.BlogApi.Blog_app_apis.services;


import java.util.List;

import com.BlogApi.Blog_app_apis.payloads.UserDto;


public interface UserService {

	
	UserDto CreateUser(UserDto user);
	
	UserDto UpdateUser(UserDto userDto,Integer userId);
	
	UserDto getById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
