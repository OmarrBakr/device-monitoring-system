package com.example.task3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "alerts")
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
