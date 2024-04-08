package com.sayu.user_address_crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@AutoConfigureMockMvc
class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    private ObjectMapper objectMapper;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        addressDto = AddressDto.builder()
                .street("3rd, Street")
                .city("bengluru")
                .state("Karnataka")
                .zipCode("290396")
                .country("India")
                .build();
    }

    @Test
    void testCreateAddress() throws Exception {
        when(addressService.createAddress(addressDto)).
                thenReturn(addressDto);

        mockMvc.perform(post("/users/1/address/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAddressById() throws Exception {
        Long userId = 1L;
        Long addressId = 2L;

        when(addressService.findAddressByBothId(userId, addressId)).thenReturn(ResponseEntity.ok(addressDto));

        mockMvc.perform(get("/users/{id}/address/by-addressId/{addressId}", userId, addressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void testAddAddressInId() throws Exception {
        Long userId = 1L;
        when(addressService.addAddressInId(userId, addressDto)).thenReturn(ResponseEntity.ok(addressDto));

        mockMvc.perform(put("/users/{id}/address/add-address/", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
//                        .content("""{                        {
//                        \t"street":"3rd, Street",
//                        \t"city":"bengluru",
//                        \t"state":"Karnataka",
//                        \t"zipCode":"290396",
//                        \t"country":"India"
//                        }"""))
                .andExpect(status().isOk());
    }
}