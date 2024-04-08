package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.UserEntity;
import com.sayu.user_address_crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserService userService;
    UserDto userDto;
    UserEntity userEntity;
    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .userName("sayali")
                .email("sayali@gmail")
                .dateOfBirth("29/03/2096")
                .password("sayu")
                .build();
        userEntity= UserEntity.builder()
                .id(1L)
                .userName("sayali")
                .email("sayali@gmail")
                .dateOfBirth("29/03/2096")
                .password("sayu")
                .build();
    }

    @Test
    void createUser() {
        Mockito.when(Mockito.any(UserEntity.class)).thenReturn(userEntity);
        // Invoking the method under test
        ResponseEntity<UserDto> responseEntity = userService.createUser(userDto);

        // Assertions
        assertEquals(userEntity.getUserName(), responseEntity.getBody().getUserName());
        assertEquals(userEntity.getEmail(), Objects.requireNonNull(responseEntity.getBody()).getEmail());
        assertEquals(userEntity.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(userEntity.getPassword(), responseEntity.getBody().getPassword());
    }

    @Test
    void findUserById() { // Mocking the behavior of userRepository.findById
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(userEntity));

        // Invoking the method under test
        ResponseEntity<UserDto> responseEntity = userService.findUserById(userEntity.getId());

        // Assertions
        assertEquals(userEntity.getUserName(), Objects.requireNonNull(responseEntity.getBody()).getUserName());
        assertEquals(userEntity.getEmail(), Objects.requireNonNull(responseEntity.getBody()).getEmail());
        assertEquals(userEntity.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(userEntity.getPassword(), responseEntity.getBody().getPassword());
    }

    @Test
    void findUserByUserName() {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(userEntity));

        // Invoking the method under test
        ResponseEntity<UserDto> responseEntity = userService.findUserByUserName(userEntity.getUserName());

        // Assertions
        assertEquals(userEntity.getUserName(), Objects.requireNonNull(responseEntity.getBody()).getUserName());
        assertEquals(userEntity.getEmail(), responseEntity.getBody().getEmail());
        assertEquals(userEntity.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(userEntity.getPassword(), responseEntity.getBody().getPassword());
    }

    @Test
    void updateUserByUserId() {
        // Mocking the behavior of userRepository.save
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        // Invoking the method under test
        ResponseEntity<UserDto> responseEntity = userService.updateUserByUserId(userEntity.getId(), userDto);

        // Assertions
        assertEquals(userEntity.getUserName(), Objects.requireNonNull(responseEntity.getBody()).getUserName());
        assertEquals(userEntity.getEmail(), responseEntity.getBody().getEmail());
        assertEquals(userEntity.getDateOfBirth(), responseEntity.getBody().getDateOfBirth());
        assertEquals(userEntity.getPassword(), responseEntity.getBody().getPassword());
    }

    @Test
    void deleteUser() {
        // Mocking the behavior of userRepository.deleteById
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyLong());

        // Invoking the method under test
        ResponseEntity<Void> responseEntity = userService.deleteUser(userEntity.getId());

        // Assertions
        assertEquals(204, responseEntity.getStatusCode()); // HTTP 204 - No Content
    }
}