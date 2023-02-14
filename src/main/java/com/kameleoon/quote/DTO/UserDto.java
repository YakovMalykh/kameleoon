package com.kameleoon.quote.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String username;
    private String email;
    private LocalDate creationDate;
}
