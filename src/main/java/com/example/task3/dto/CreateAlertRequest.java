package com.example.task3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAlertRequest {

    @NotBlank(message = "Message is mandatory")
    private String message;

    @NotNull(message = "Device ID is mandatory")
    private Long deviceId;
}
