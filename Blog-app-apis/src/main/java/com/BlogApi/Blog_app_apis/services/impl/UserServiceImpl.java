package com.BlogApi.Blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.BlogApi.Blog_app_apis.entity.User;
import com.BlogApi.Blog_app_apis.exceptions.ResourceNotFoundException;
import com.BlogApi.Blog_app_apis.payloads.UserDto;
import com.BlogApi.Blog_app_apis.repository.UserRepo;
import com.BlogApi.Blog_app_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto CreateUser(UserDto userDto) {

		logger.info("Creating user: {}", userDto);

		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		logger.info("User created: {}", savedUser);
		return this.userToDto(savedUser);

	}

	@Override
	public UserDto UpdateUser(UserDto userDto, Integer userId) {
		 logger.info("Updating user with ID {}: {}", userId, userDto);

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		// Update the fields of the retrieved user with the fields from the incoming
		// UserDto
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());

		// Save the updated user back to the repository
		User updatedUser = this.userRepo.save(user);

		// Convert the updated user entity back to UserDto and return it
		UserDto userToDto = this.userToDto(updatedUser);
		logger.info("User updated: {}", updatedUser);
		return userDto;
	}

	@Override
	public UserDto getById(Integer userId) {
		
		logger.info("Fetching user with ID: {}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		logger.info("User fetched: {}", user);
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		logger.info("Fetching all users");
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
		logger.info("Fetched {} users", userDtos.size());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		logger.info("Deleting user with ID: {}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
		logger.info("User deleted successfully");
	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
		/*
		 * User user = new User(); user.setUserId(userDto.getUserId());
		 * user.setUserName(userDto.getUserName());
		 * user.setUserEmail(userDto.getUserEmail());
		 * user.setUserPassword(userDto.getUserPassword());
		 * user.setUserAbout(userDto.getUserAbout()); return user;
		 */
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		/*
		 * UserDto userDto = new UserDto(); userDto.setUserId(user.getUserId());
		 * userDto.setUserName(user.getUserName());
		 * userDto.setUserEmail(user.getUserEmail());
		 * userDto.setUserPassword(user.getUserPassword());
		 * userDto.setUserAbout(user.getUserAbout()); return userDto;
		 */
	}
}