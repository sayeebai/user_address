package com.sayu.user_address_crud.mapper;

import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.dto.UserDto;
import com.sayu.user_address_crud.entity.AddressEntity;
import com.sayu.user_address_crud.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final AddressMapper addressMapper;
    @Autowired
    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }
            public UserEntity mapUserDtoToEntity(UserDto userDto) {
                 UserEntity userEntity = new UserEntity();
                userEntity.setName(userDto.getName());
                userEntity.setUserName(userDto.getUserName());
                userEntity.setPassword(userDto.getPassword());
                userEntity.setEmail(userDto.getEmail());
                userEntity.setDateOfBirth(userDto.getDateOfBirth());

                if (userDto.getAddressDto() != null) {
                    List<AddressEntity> addressEntity =
                            //addressService.mapAddressDtoToEntity((AddressDto) userDto.getAddressDto(userEntity.getAddressEntityList()));
                            userDto.getAddressDto().stream()
                                    .map(addressMapper::mapAddressDtoToEntity)
                                    .collect(Collectors.toList());
                    userEntity.setAddressEntityList(addressEntity);        }
                return userEntity;
            }
            public UserDto mapUserEntityToDto(UserEntity userEntity) {
                UserDto userDto = new UserDto();
                userDto.setName(userEntity.getName());
                userDto.setUserName(userEntity.getUserName());
                userDto.setEmail(userEntity.getEmail());
                userDto.setPassword(userEntity.getPassword());
                userDto.setDateOfBirth(userEntity.getDateOfBirth());
    //        userDto.setAddressDto(userEntity.getAddressEntityList());
    //        userDto.setAddressDto(mapAddressEntityListToDto(userEntity.getAddressEntityList()));
    //        userDto.setAddressDto(userEntity.getAddressEntityList());
                List<AddressDto> addressDtoList = userEntity.getAddressEntityList().stream()
                        .map(addressMapper::mapAddressEntityToDto)
                        .collect(Collectors.toList());
                userDto.setAddressDto(addressDtoList);
                return userDto;
            }
    }



