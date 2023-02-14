package com.kameleoon.quote.servicies;

import com.kameleoon.quote.DTO.CreateUserDto;
import com.kameleoon.quote.exceptions.UserAlreadyExists;
import com.kameleoon.quote.mappers.UserMapper;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.repositories.UserRepository;
import com.kameleoon.quote.servicies.interfacies.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final MyUserDetailsService userDetailsService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder, UserMapper userMapper, MyUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.info("user doesn't exists");
            return false;
        } else {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String encodedPassword = userDetails.getPassword();
            return encoder.matches(password, encodedPassword);
        }
    }

    @Override
    public boolean registration(CreateUserDto createUserDto) {
        Optional<User> optionalUser = userRepository.findByUsername(createUserDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExists("User with username '" + createUserDto.getUsername() + "' already exists");
        }

        User user = userMapper.createUserDtoToUser(createUserDto);
        String encodedPassword = encoder.encode(user.getPassword());
        LocalDate creationDate = LocalDate.now();

        user.setPassword(encodedPassword);
        user.setCreationDate(creationDate);

        userRepository.save(user);

        return true;
    }
}
