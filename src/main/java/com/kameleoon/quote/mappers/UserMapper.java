package com.kameleoon.quote.mappers;

import com.kameleoon.quote.DTO.CreateUserDto;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Mapper
public abstract class UserMapper {
    @Autowired
    protected UserRepository userRepository;

    public abstract User createUserDtoToUser(CreateUserDto createUserDto);

}
