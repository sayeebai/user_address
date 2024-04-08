package com.sayu.user_address_crud.service;

import com.sayu.user_address_crud.dto.AddressDto;
import org.springframework.http.ResponseEntity;

public interface AddressService {
        ResponseEntity<AddressDto> addAddressInId(Long userId, AddressDto addressDto) throws Exception;
        ResponseEntity<AddressDto> findAddressByBothId(Long userId, Long addressId);

        Object createAddress(AddressDto inputAddressDto);
}
