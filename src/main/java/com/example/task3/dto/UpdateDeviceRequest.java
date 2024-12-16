package com.example.task3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateDeviceRequest {

    private String name;

    private BigDecimal maxTemp;

    private BigDecimal minTemp;

    private BigDecimal maxHumidity;

    private BigDecimal minHumidity;

}
