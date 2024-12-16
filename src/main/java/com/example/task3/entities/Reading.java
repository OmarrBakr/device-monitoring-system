package com.example.task3.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "readings")
@AllArgsConstructor
@NoArgsConstructor
public class Reading {

    @Id
    private String id;

    private BigDecimal temp;

    private BigDecimal humidity;

    private Long deviceId;

    private LocalDateTime timestamp = LocalDateTime.now();
}
