package com.example.task3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean success;
    private int statusCode;

    public static <T> ApiResponse<T> success(String message, T data, int statusCode) {
        return new ApiResponse<>(message, data, true, statusCode);
    }

    public static <T> ApiResponse<T> failure(String message, int statusCode) {
        return new ApiResponse<>(message, null, false, statusCode);
    }
}
