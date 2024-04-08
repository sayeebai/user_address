package com.sayu.user_address_crud.controller;

import com.sayu.user_address_crud.dto.AddressDto;
import com.sayu.user_address_crud.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}/address")
@Validated
public class AddressController {
    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    private final AddressService addressService;
    @PostMapping("/")
   ResponseEntity<AddressDto> createAddress(@PathVariable("id") Long userId,
                                            @RequestBody AddressDto addressDto) throws Exception {
        return addressService.addAddressInId(userId, addressDto);
   }
    @GetMapping("/by-addressId/{addressId}")
    ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Long userId,
                                              @PathVariable("addressId") Long addressId){
        return addressService.findAddressByBothId(userId,addressId);
    }
    @PutMapping("/add-address/")
    public ResponseEntity<AddressDto> addAddressInId(@PathVariable("id") Long userId, @RequestBody AddressDto addressDto) throws Exception {
        return addressService.addAddressInId(userId, addressDto);
    }
}
