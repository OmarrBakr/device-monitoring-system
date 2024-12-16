package com.example.task3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateReadingRequest {
    @NotNull(message = "Temperature cannot be null")
    private BigDecimal temp;

    @NotNull(message = "Humidity cannot be null")
    private BigDecimal humidity;

    @NotNull(message = "Device ID cannot be null")
    private Long deviceId;
}
