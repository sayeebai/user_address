package com.sayu.user_address_crud.controller;

import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/username/{userName}")
    ResponseEntity<UserDto> getUserByName(@PathVariable("userName") String userName) {
        return userService.findUserByUserName(userName);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUserByUserId(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto) {
        return userService.updateUserByUserId(userId, userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }

}
