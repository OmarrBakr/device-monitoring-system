package com.example.task3.services;

import com.example.task3.dto.ApiResponse;
import com.example.task3.dto.CreateReadingRequest;
import com.example.task3.entities.Device;
import com.example.task3.entities.Reading;
import com.example.task3.repositories.DeviceRepo;
import com.example.task3.repositories.ReadingRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Locked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReadingService {

    private final ReadingRepo readingRepo;
    private final DeviceService deviceService;
    private final AlertService alertService;

    public ResponseEntity<ApiResponse<List<Reading>>> getAllReadings() {
        List<Reading> readings = readingRepo.findAll();
        ApiResponse<List<Reading>> response = ApiResponse.success("Readings retrieved successfully", readings, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<Reading>> createReading(CreateReadingRequest request) {
        Device device = deviceService.getDeviceById(request.getDeviceId());
        Reading reading = new Reading();
        reading.setTemp(request.getTemp());
        reading.setHumidity(request.getHumidity());
        reading.setDeviceId(request.getDeviceId());
        readingRepo.save(reading);
        alertService.checkAlert(reading, device);
        ApiResponse<Reading> response = ApiResponse.success("Reading created successfully", reading, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


