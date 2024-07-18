package com.BlogApi.Blog_app_apis.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApi.Blog_app_apis.payloads.ApiResponse;
import com.BlogApi.Blog_app_apis.payloads.UserDto;
import com.BlogApi.Blog_app_apis.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user with data: {}", userDto);
        UserDto createdUserDto = this.userService.CreateUser(userDto);
        logger.info("User created: {}", createdUserDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        logger.info("Updating user with ID: {} and data: {}", userId, userDto);
        UserDto updatedUser = this.userService.UpdateUser(userDto, userId);
        logger.info("User updated: {}", updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        logger.info("Deleting user with ID: {}", userId);
        this.userService.deleteUser(userId);
        logger.info("User with ID: {} deleted", userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    // Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        logger.info("Fetching all users");
        List<UserDto> users = this.userService.getAllUsers();
        logger.info("Fetched {} users", users.size());
        return ResponseEntity.ok(users);
    }

    // Get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        logger.info("Fetching user with ID: {}", userId);
        UserDto user = this.userService.getById(userId);
        logger.info("Fetched user: {}", user);
        return ResponseEntity.ok(user);
    }
}
