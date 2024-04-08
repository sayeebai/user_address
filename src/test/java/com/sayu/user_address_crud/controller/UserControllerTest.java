package com.sayu.user_address_crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private UserDto userDto;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        userDto = UserDto.builder()
                .userName("sayali")
                .email("sayali@gmail")
                .dateOfBirth("29/03/2096")
                .password("sayu")
                .build();
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(UserDto.class))).thenReturn(ResponseEntity.ok(userDto));

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username").value(userDto.getUsername()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void testFindUserById() throws Exception {
        Long id = 1L;
        when(userService.findUserById(id)).
                thenReturn(ResponseEntity.ok(userDto));
        mockMvc.perform(get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value(userDto.getUsername()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void testGetUserByName() throws Exception {
        String userName = "sayali";
        when(userService.findUserByUserName(userName)).thenReturn(ResponseEntity.ok(userDto));
        mockMvc.perform(get("/users/username/{userName}", userName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value(userDto.getUsername()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));

    }

    @Test
    void testUpdateUserById() throws Exception {
        Long id = 1L;
        when(userService.updateUserByUserId(any(), any(UserDto.class))).
                thenReturn(ResponseEntity.ok(userDto));
        mockMvc.perform(put("/users/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username").value(userDto.getUsername()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void testDeleteUserById() throws Exception {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());
    }
}