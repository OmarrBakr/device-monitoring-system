package com.example.task3.services;

import com.example.task3.dto.ApiResponse;
import com.example.task3.dto.CreateDeviceRequest;
import com.example.task3.dto.UpdateDeviceRequest;
import com.example.task3.entities.Device;
import com.example.task3.repositories.DeviceRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepo deviceRepo;
    private final Map<Long, Device> deviceCache = new HashMap<>();

    public ResponseEntity<ApiResponse<List<Device>>> getAllDevices() {
        List<Device> devices = deviceRepo.findAll();
        ApiResponse<List<Device>> response = ApiResponse.success("Devices retrieved successfully", devices, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<Device>> createDevice(CreateDeviceRequest request) {
        Device device = new Device();
        device.setName(request.getName());
        device.setMaxTemp(request.getMaxTemp());
        device.setMinTemp(request.getMinTemp());
        device.setMaxHumidity(request.getMaxHumidity());
        device.setMinHumidity(request.getMinHumidity());

        deviceRepo.save(device);
        deviceCache.put(device.getId(), device);

        ApiResponse<Device> response = ApiResponse.success("Device created successfully", device, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Device>> updateDevice(Long deviceId, UpdateDeviceRequest request) {
        Device existingDevice = getDeviceById(deviceId);
        if (request.getName() != null) {
            existingDevice.setName(request.getName());
        }
        if (request.getMaxTemp() != null) {
            existingDevice.setMaxTemp(request.getMaxTemp());
        }
        if (request.getMinTemp() != null) {
            existingDevice.setMinTemp(request.getMinTemp());
        }
        if (request.getMaxHumidity() != null) {
            existingDevice.setMaxHumidity(request.getMaxHumidity());
        }
        if (request.getMinHumidity() != null) {
            existingDevice.setMinHumidity(request.getMinHumidity());
        }
        deviceRepo.save(existingDevice);
        deviceCache.put(deviceId, existingDevice);
        ApiResponse<Device> response = ApiResponse.success("Device updated successfully", existingDevice, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public Device getDeviceById(Long deviceId) {
        Device device = deviceCache.get(deviceId);
        if (device == null) {
            device = deviceRepo.findById(deviceId)
                    .orElseThrow(() -> new EntityNotFoundException("Device not found with id: " + deviceId));
            deviceCache.put(deviceId, device);
        }
        return device;
    }
}
