package com.kameleoon.quote;

import com.kameleoon.quote.models.User;
import com.kameleoon.quote.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class KameleoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(KameleoonApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
        return args ->{
            userRepository.save(new User("user", "test@test.com", encoder.encode("password"), LocalDate.now()));
        };
    }

}
