package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.DTO.CreateUserDto;
import com.kameleoon.quote.exceptions.UserAlreadyExists;
import com.kameleoon.quote.mappers.UserMapper;
import com.kameleoon.quote.repositories.UserRepository;
import com.kameleoon.quote.servicies.AuthServiceImpl;
import com.kameleoon.quote.servicies.MyUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.kameleoon.quote.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder encoder;
    @Mock
    UserMapper mapper;
    @Mock
    MyUserDetailsService userDetailsService;

    @InjectMocks
    AuthServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        CREATE_USER_DTO.setUsername("test");
    }

    @Test
    void loginFailed() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertFalse(registrationService.login("test", "password"));
    }
    @Test
    void loginSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(USER));
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(USER_DETAILS);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);

        assertTrue(registrationService.login("test", "password"));
    }

    @Test
    void registrationFailedWhenUsernameAlreadyExists() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(USER));

        UserAlreadyExists exception = assertThrows(UserAlreadyExists.class, () -> registrationService.registration(CREATE_USER_DTO));

        assertEquals("User with username 'test' already exists", exception.getMessage());
    }

    @Test
    void registrationSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(mapper.createUserDtoToUser(any(CreateUserDto.class))).thenReturn(USER);

        boolean response = registrationService.registration(CREATE_USER_DTO);
        assertTrue(response);
    }
}