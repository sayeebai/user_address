package com.sayu.user_address_crud.mapper;

import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressEntity mapAddressDtoToEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setZipCode(addressDto.getZipCode());
        addressEntity.setCountry(addressDto.getCountry());
        return addressEntity;
    }
    public AddressDto mapAddressEntityToDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setState(addressDto.getState());
        addressDto.setCountry(addressEntity.getCountry());
        addressDto.setZipCode(addressEntity.getZipCode());
        return addressDto;
    }
}
