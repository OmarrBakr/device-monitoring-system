package com.example.task3.controllers;

import com.example.task3.dto.*;
import com.example.task3.entities.User;
import com.example.task3.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(
            @Valid @RequestBody LoginRequest request) {
        return authService.authenticate(request);
    }

}
