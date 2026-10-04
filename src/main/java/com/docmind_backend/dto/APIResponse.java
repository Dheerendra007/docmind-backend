package com.docmind_backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder 
public class APIResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

}
