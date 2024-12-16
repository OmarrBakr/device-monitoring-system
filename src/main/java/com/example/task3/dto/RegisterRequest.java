package com.example.task3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Enter a valid email address")
  private String email;

  @NotBlank(message = "Username cannot be blank")
  @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;
}