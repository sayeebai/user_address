package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.AddressEntity;
import com.sayu.user_address_crud.entity.UserEntity;
import com.sayu.user_address_crud.mapper.AddressMapper;
import com.sayu.user_address_crud.mapper.UserMapper;
import com.sayu.user_address_crud.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceImplTest {
    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private AddressMapper addressMapper;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private AddressServiceImpl addressService;
    AddressDto inputAddressDto;
    AddressEntity savedAddressEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        addressService = new AddressServiceImpl(addressRepository, userService, addressMapper, userMapper);
        inputAddressDto = AddressDto.builder()
                .street("3rd, Street")
                .city("Bangalore")
                .state("Karnataka")
                .zipCode("560001")
                .country("India")
                .build();
        savedAddressEntity = AddressEntity.builder()
                .id(1L)
                .street("3rd, Street")
                .city("Bangalore")
                .state("Karnataka")
                .zipCode("560001")
                .country("India")
                .build();
    }

    @Test
    void testAddAddressInId() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        when(userService.findUserById(userId)).thenReturn(ResponseEntity.ok(userDto));
        UserEntity userEntity = new UserEntity();
        when(userMapper.mapUserDtoToEntity(userDto)).thenReturn(userEntity);
        when(userMapper.mapUserEntityToDto(userEntity)).thenReturn(userDto);
        when(addressMapper.mapAddressEntityToDto(savedAddressEntity)).thenReturn(inputAddressDto);
        when(addressMapper.mapAddressDtoToEntity(inputAddressDto)).thenReturn(savedAddressEntity);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(savedAddressEntity);
        ResponseEntity<AddressDto> response = addressService.addAddressInId(inputAddressDto);

        Assertions.assertEquals(inputAddressDto, response.getBody());
        Assertions.assertEquals(200,response.getStatusCodeValue());
        verify(addressRepository, times(1)).save(any(AddressEntity.class));
    }

    @Test
    void findAddressByBothIdTest() {
        Long userId = 1L;
        Long addressId = 1L;
        // Mocking the behavior of userService.findUserById(userId)
        UserDto userDto = UserDto.builder().build();
        userDto.setAddressDto(List.of()); // assuming savedAddressEntity is an AddressEntity
        when(userService.findUserById(userId)).thenReturn(ResponseEntity.ok(userDto));
        OngoingStubbing<ResponseEntity<AddressDto>> responseEntityOngoingStubbing;//savedAddressEntity);
        responseEntityOngoingStubbing = when(addressService.findAddressByBothId(userId, addressId)).thenReturn(ResponseEntity.ok(inputAddressDto));

        ResponseEntity<AddressDto> responseEntity = Objects.requireNonNull(addressService.findAddressByBothId(userId, addressId));
        Assertions.assertEquals(savedAddressEntity.getStreet(), responseEntity.getBody().getStreet());
        Assertions.assertEquals(savedAddressEntity.getCity(), responseEntity.getBody().getCity());
        Assertions.assertEquals(savedAddressEntity.getState(), responseEntity.getBody().getState());
        Assertions.assertEquals(savedAddressEntity.getZipCode(), responseEntity.getBody().getZipCode());
        Assertions.assertEquals(savedAddressEntity.getCountry(), responseEntity.getBody().getCountry());
    }

    @Test
    void createAddressTest() {
        when(addressRepository.save(savedAddressEntity)).
                thenReturn(savedAddressEntity);

        ResponseEntity<AddressDto> responseEntity;
        responseEntity = addressService.createAddress(inputAddressDto);

        Assertions.assertEquals(savedAddressEntity.getStreet(), responseEntity.getBody().getStreet());
        Assertions.assertEquals(savedAddressEntity.getCity(), responseEntity.getBody().getCity());
        Assertions.assertEquals(savedAddressEntity.getState(), responseEntity.getBody().getState());
        Assertions.assertEquals(savedAddressEntity.getZipCode(), responseEntity.getBody().getZipCode());
        Assertions.assertEquals(savedAddressEntity.getCountry(), responseEntity.getBody().getCountry());
    }
}
