package com.example.task3.services;

import com.example.task3.config.JwtService;
import com.example.task3.dto.*;
import com.example.task3.entities.User;
import com.example.task3.exceptions.DuplicateUsernameException;
import com.example.task3.exceptions.UnauthenticatedException;
import com.example.task3.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserRepo repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<ApiResponse<RegisterResponse>> register(RegisterRequest request) {
    if (repository.findByUsername(request.getUsername()).isPresent()) {
      throw new DuplicateUsernameException("Username is already taken");
    }
    if (repository.findByEmail(request.getEmail()).isPresent()) {
      throw new DuplicateUsernameException("Email is already taken");
    }
    var user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user, user.getId());
    RegisterResponse registerResponse = RegisterResponse.builder()
            .user(user)
            .token(jwtToken)
            .build();
    ApiResponse<RegisterResponse> apiResponse = ApiResponse.success("User registered successfully", registerResponse, HttpStatus.CREATED.value());
    return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
  }

  public ResponseEntity<ApiResponse<LoginResponse>> authenticate(LoginRequest request) {
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
      User user = repository.findByUsername(request.getUsername())
              .orElseThrow(() -> new UnauthenticatedException("Invalid username or password"));
      String jwtToken = jwtService.generateToken(user, user.getId());
      LoginResponse loginResponse = LoginResponse.builder()
              .user(user)
              .token(jwtToken)
              .build();
      ApiResponse<LoginResponse> apiResponse = ApiResponse.success("User authenticated successfully", loginResponse, HttpStatus.OK.value());
      return ResponseEntity.ok(apiResponse);
    } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
      throw new UnauthenticatedException("Invalid username or password");
    }
  }
}
