package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.AddressEntity;
import com.sayu.user_address_crud.entity.UserEntity;
import com.sayu.user_address_crud.mapper.AddressMapper;
import com.sayu.user_address_crud.mapper.UserMapper;
import com.sayu.user_address_crud.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private AddressServiceImpl addressService;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,UserService userService,
                              AddressMapper addressMapper,UserMapper userMapper) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }
    @Override
    public ResponseEntity<AddressDto> addAddressInId(Long userId, AddressDto addressDto) {
        try {
            UserDto optionalUserDto = userService.findUserById(userId).getBody();
            if (optionalUserDto != null) {
                // Map AddressDto to AddressEntity
                AddressEntity addressEntity = addressMapper.mapAddressDtoToEntity(addressDto);
                //Add Adress in address repository
                addressRepository.save(addressEntity);

                UserEntity userEntity = userMapper.mapUserDtoToEntity(optionalUserDto);
                // Add the address to the user
                userEntity.getAddressEntityList().add(addressEntity);
                // Save the user entity
                userService.updateUserByUserId(userId,userMapper.mapUserEntityToDto(userEntity));
                // Return the added address DTO
                return ResponseEntity.ok(addressMapper.mapAddressEntityToDto(addressEntity));
            } else {
                // User not found, return 404 Not Found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any other unexpected errors and return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AddressDto> findAddressByBothId(Long userId, Long addressId) {
        UserDto userDto = userService.findUserById(userId).getBody();
        if (userDto != null) {
            Optional<AddressEntity> addressEntity = addressRepository.findById(addressId);
            if (addressEntity.isPresent()) {
                UserEntity userEntity = userMapper.mapUserDtoToEntity(userDto);
                List<AddressEntity> addressEntityList = userEntity.
                        getAddressEntityList();
                // check for id
                Optional<AddressEntity> optionalAddressEntity =
                        addressEntityList.stream().filter(address -> address.getId().equals(addressId))
                                .findFirst();
                if (optionalAddressEntity.isPresent()) {
                    AddressDto addressEntityToDtoDto = addressMapper.mapAddressEntityToDto(optionalAddressEntity.get());
                    return ResponseEntity.ok(addressEntityToDtoDto);
                } else return ResponseEntity.notFound().build();
            } else {
                // User not found, return 404 Not Found
                return ResponseEntity.notFound().build();
            }
        }
        return null;
    }
    @Override
    public ResponseEntity<AddressDto> createAddress(AddressDto inputAddressDto) {
        AddressEntity createAddress = addressMapper.mapAddressDtoToEntity(inputAddressDto);
        AddressDto createdAddressDto = addressMapper.mapAddressEntityToDto(addressRepository.save(createAddress));
        return ResponseEntity.ok(createdAddressDto);
    }
}

