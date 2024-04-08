package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.UserEntity;
import com.sayu.user_address_crud.mapper.UserMapper;
import com.sayu.user_address_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    //Autowired using constructor injection
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl (UserRepository userRepository, UserMapper userMapper){
        this.userRepository= userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapUserDtoToEntity(userDto);
        userRepository.save(userEntity);
        return ResponseEntity.ok(userMapper.mapUserEntityToDto(userEntity));
    }
    @Override
    public ResponseEntity<UserDto> findUserById(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userMapper.mapUserEntityToDto(userEntity.get()));
        }
//        return userEntity.map(entity -> ResponseEntity.ok(mapUserEntityToDto(entity)))
//                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDto> findUserByUserName(String userName) {
        Optional<UserEntity> userEntity = userRepository.findByUserName(userName);
        return ResponseEntity.ok(userEntity.map(userMapper::mapUserEntityToDto).orElse(null));
    }

    @Override
    public ResponseEntity<UserDto> updateUserByUserId(Long userId, UserDto userDto) {
        if(userRepository.existsById(userId)){
            UserEntity userEntity= userMapper.mapUserDtoToEntity(userDto);
            userRepository.save(userEntity);
            return ResponseEntity.ok(userMapper.mapUserEntityToDto(userEntity));
        }
        return ResponseEntity.notFound().build();
    }
    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                // Return 204 No Content to indicate successful deletion
                return ResponseEntity.noContent().build();
            } else {
                // User not found, return 404 Not Found
                return ResponseEntity.notFound().build();
            }
    }
}
