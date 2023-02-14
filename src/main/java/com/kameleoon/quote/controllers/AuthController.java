package com.kameleoon.quote.controllers;

import com.kameleoon.quote.DTO.CreateUserDto;
import com.kameleoon.quote.DTO.LoginDto;
import com.kameleoon.quote.servicies.interfacies.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        if (authService.login(loginDto.getUsername(), loginDto.getPassword())) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Registration", responses = {
            @ApiResponse(responseCode = "200", description = "user is created"),
            @ApiResponse(responseCode = "404", description = "user with this username already exists, or validation is failed")
    })
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody CreateUserDto createUserDto) {
        authService.registration(createUserDto);
        return ResponseEntity.ok().build();
    }
}
