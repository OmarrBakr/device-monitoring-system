package com.example.task3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "device")
    @JsonIgnore
    private List<User> users;

    @Column(nullable = false)
    private BigDecimal maxTemp;

    @Column(nullable = false)
    private BigDecimal minTemp;

    @Column(nullable = false)
    private BigDecimal maxHumidity;

    @Column(nullable = false)
    private BigDecimal minHumidity;
}
