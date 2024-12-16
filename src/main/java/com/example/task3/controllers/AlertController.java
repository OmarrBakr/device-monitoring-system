package com.example.task3.controllers;

import com.example.task3.dto.ApiResponse;
import com.example.task3.dto.CreateAlertRequest;
import com.example.task3.entities.Alert;
import com.example.task3.services.AlertService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@AllArgsConstructor
@Validated
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Alert>>> getAllAlerts() {
        return alertService.getAllAlerts();
    }

//    @PostMapping
//    public ResponseEntity<ApiResponse<Alert>> createAlert(@Valid @RequestBody CreateAlertRequest request) {
//        return alertService.createAlert(request);
//    }
}
