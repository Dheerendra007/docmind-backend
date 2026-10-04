package com.docmind_backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.docmind_backend.dto.APIResponse;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        logger.warn("Resource not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(DocumentProcessingException.class)
    public ResponseEntity<APIResponse<Object>> handleDocumentProcessing(DocumentProcessingException ex) {
        logger.error("Error processing document: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Failed to process document: " + ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<APIResponse<Object>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        logger.error("File size exceeds the limit: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.builder()
                        .success(false)
                        .message("File size exceeds the limit")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation error: {}", ex.getMessage());

       Map<String, String> errors = new HashMap<>();
       for(FieldError error : ex.getBindingResult().getFieldErrors()) {
           errors.put(error.getField(), error.getDefaultMessage());
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.<Map<String, String>>builder()
                        .success(false)
                        .message("Validation failed")
                        .data(errors)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<APIResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Invalid argument: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Invalid argument: " + ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    
    //Generalized exception handler for any other exceptions not specifically handled
    @ExceptionHandler (Exception.class)
    public ResponseEntity<APIResponse<Object>> handleGenericException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.builder()
                        .success(false)
                        .message("An unexpected error occurred")
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}