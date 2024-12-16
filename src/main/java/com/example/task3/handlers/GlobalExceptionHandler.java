package com.example.task3.handlers;

import com.example.task3.dto.ApiResponse;
import com.example.task3.exceptions.DuplicateUsernameException;
import com.example.task3.exceptions.UnauthenticatedException;
import com.example.task3.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.failure("Validation errors", HttpStatus.BAD_REQUEST.value());
        errorResponse.setData(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNoHandlerFound(NoHandlerFoundException ex) {
        ApiResponse<String> errorResponse = ApiResponse.failure("Invalid route", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateUsernameException(
            DuplicateUsernameException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthenticatedException(
            UnauthenticatedException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(
            UnauthorizedException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure("No request body provided", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure("Only JSON body accepted", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleExpiredJwtException(
            ExpiredJwtException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure("JWT token has expired. Please login again.", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<String>> handleSignatureException(
            SignatureException ex, WebRequest request) {
        ApiResponse<String> errorResponse = ApiResponse.failure("JWT signature does not match. Please ensure the token is valid.", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
