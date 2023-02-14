package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.DTO.CreateUserDto;

public interface AuthService {

    boolean login(String username, String password);
    boolean registration(CreateUserDto createUserDto);
}
