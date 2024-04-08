package com.sayu.user_address_crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressDto {

    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    @Length(max=6)
    private String zipCode;
    @NotBlank
    private String country;
}
