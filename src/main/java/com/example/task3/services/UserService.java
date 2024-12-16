package com.example.task3.services;

import com.example.task3.config.JwtService;
import com.example.task3.dto.ApiResponse;
import com.example.task3.entities.Device;
import com.example.task3.entities.User;
import com.example.task3.repositories.DeviceRepo;
import com.example.task3.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final DeviceService deviceService;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userRepo.findAll();
        ApiResponse<List<User>> response = ApiResponse.success("Users retrieved successfully", users, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<User>> subscribeUserToDevice(Long deviceId) {
            User user = userRepo.findById(jwtService.extractUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + jwtService.extractUserId()));
            Device device = deviceService.getDeviceById(deviceId);
            user.setDevice(device);
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success("User subscribed to device successfully", user, HttpStatus.OK.value()));
    }
}
