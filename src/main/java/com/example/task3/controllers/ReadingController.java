package com.example.task3.controllers;

import com.example.task3.dto.ApiResponse;
import com.example.task3.dto.CreateReadingRequest;
import com.example.task3.entities.Reading;
import com.example.task3.services.ReadingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
@AllArgsConstructor
public class ReadingController {

    private final ReadingService readingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Reading>>> getAllReadings() {
        return readingService.getAllReadings();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Reading>> createReading(@Valid @RequestBody CreateReadingRequest request) {
        return readingService.createReading(request);
    }
}
