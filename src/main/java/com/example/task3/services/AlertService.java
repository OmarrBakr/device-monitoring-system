package com.example.task3.services;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.protocol.Packet;
import com.example.task3.dto.ApiResponse;
import com.example.task3.entities.Alert;
import com.example.task3.entities.Device;
import com.example.task3.entities.Reading;
import com.example.task3.repositories.AlertRepo;
//import com.example.task3.socket.MyWebSocketHandler;
import com.example.task3.socket2.SocketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.SocketAddress;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlertService {

    private final AlertRepo alertRepo;
//    private final MyWebSocketHandler webSocketHandler;
    private final SocketService socketService;
//    private final EmailService emailService;

    public ResponseEntity<ApiResponse<List<Alert>>> getAllAlerts() {
        List<Alert> alerts = alertRepo.findAll();
        ApiResponse<List<Alert>> response = ApiResponse.success("Alerts retrieved successfully", alerts, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

//    public ResponseEntity<ApiResponse<Alert>> createAlert(CreateAlertRequest request) {
//        Device device = deviceRepo.findById(request.getDeviceId())
//                .orElseThrow(() -> new EntityNotFoundException("Device not found with id: " + request.getDeviceId()));
//        Alert alert = new Alert();
//        alert.setMessage(request.getMessage());
//        alert.setDevice(device);
//        alertRepo.save(alert);
//        ApiResponse<Alert> response = ApiResponse.success("Alert created successfully", alert, HttpStatus.CREATED.value());
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

    public void checkAlert(Reading reading, Device device) {
        boolean alertTriggered = false;
        String message = "";
        if (reading.getTemp().compareTo(device.getMinTemp()) < 0) {
            alertTriggered = true;
            message += "Temperature is below the minimum threshold.\n" +
                    "Current Temperature: " + reading.getTemp() + "\n" +
                    "Minimum Temperature: " + device.getMinTemp() + "\n";
        } else if (reading.getTemp().compareTo(device.getMaxTemp()) > 0) {
            alertTriggered = true;
            message += "Temperature is above the maximum threshold.\n" +
                    "Current Temperature: " + reading.getTemp() + "\n" +
                    "Maximum Temperature: " + device.getMaxTemp() + "\n";
        }
        if (reading.getHumidity().compareTo(device.getMinHumidity()) < 0) {
            alertTriggered = true;
            message += "Humidity is below the minimum threshold.\n" +
                    "Current Humidity: " + reading.getHumidity() + "\n" +
                    "Minimum Humidity: " + device.getMinHumidity() + "\n";
        } else if (reading.getHumidity().compareTo(device.getMaxHumidity()) > 0) {
            alertTriggered = true;
            message += "Humidity is above the maximum threshold.\n" +
                    "Current Humidity: " + reading.getHumidity() + "\n" +
                    "Maximum Humidity: " + device.getMaxHumidity() + "\n";
        }
        if (alertTriggered) {
            Alert alert = new Alert();
            alert.setMessage(message);
            alert.setDevice(device);
            alert.setTimestamp(reading.getTimestamp());
            alertRepo.save(alert);
            socketService.sendMessageToAllInRoom(device.getId().toString(),"get_alerts",message);
//            webSocketHandler.sendAlert(reading.getDeviceId().toString(), message);
//            emailService.sendEmailsToSubscribedUsers(
//                    reading.getDeviceId(),
//                    "Device " + device.getName() +" Alert",
//                    message);
        }
    }
}
