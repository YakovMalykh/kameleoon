package com.kameleoon.quote.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserDto {
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;

}
