package com.sayu.user_address_crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserDto {
    @NotBlank
    private String userName;
    private String password;
    private String name;
    @Email
    @NotBlank
    private String email;
    private String dateOfBirth;
    private List<AddressDto> addressDto;


}
