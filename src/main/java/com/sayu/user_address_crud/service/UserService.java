package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> createUser(UserDto userDto);
    ResponseEntity<UserDto> findUserById(Long UserId);
    ResponseEntity<UserDto> findUserByUserName(String userName);

    ResponseEntity<UserDto> updateUserByUserId(Long userId, UserDto userDto);

    ResponseEntity<Void> deleteUser(Long userId);
}
