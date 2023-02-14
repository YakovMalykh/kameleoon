package com.kameleoon.quote.mappers;

import com.kameleoon.quote.constants.Constants;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kameleoon.quote.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void setUp() {
        CREATE_USER_DTO.setUsername("test");
        CREATE_USER_DTO.setEmail("test@tast.ru");
        CREATE_USER_DTO.setPassword("password before encoding");
    }


    @Test
    void createUserDtoToUser_success() {
        User user = mapper.createUserDtoToUser(CREATE_USER_DTO);

        assertEquals("test", user.getUsername());
        assertEquals("test@tast.ru", user.getEmail());
        assertEquals("password before encoding", user.getPassword());
    }
}