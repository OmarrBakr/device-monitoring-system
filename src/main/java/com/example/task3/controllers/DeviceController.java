package com.example.task3.controllers;

import com.example.task3.dto.ApiResponse;
import com.example.task3.dto.CreateDeviceRequest;
import com.example.task3.dto.UpdateDeviceRequest;
import com.example.task3.entities.Device;
import com.example.task3.services.DeviceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@AllArgsConstructor
@Validated
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Device>>> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Device>> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        return deviceService.createDevice(request);
    }

    @PatchMapping("/{deviceId}")
    public ResponseEntity<ApiResponse<Device>> updateDevice(@PathVariable Long deviceId,
                                                            @Valid @RequestBody UpdateDeviceRequest request) {
        return deviceService.updateDevice(deviceId, request);
    }

}
