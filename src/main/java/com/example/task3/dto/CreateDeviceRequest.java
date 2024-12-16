package com.example.task3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateDeviceRequest {
    @NotNull
    private String name;

    @NotNull
    private BigDecimal maxTemp;

    @NotNull
    private BigDecimal minTemp;

    @NotNull
    private BigDecimal maxHumidity;

    @NotNull
    private BigDecimal minHumidity;
}
