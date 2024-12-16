package com.example.task3.controllers;

import com.example.task3.dto.ApiResponse;
import com.example.task3.entities.Device;
import com.example.task3.entities.User;
import com.example.task3.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/subscribe/{deviceId}")
    public ResponseEntity<ApiResponse<User>> subscribeUserToDevice(@PathVariable Long deviceId) {
        return userService.subscribeUserToDevice(deviceId);
    }

}
