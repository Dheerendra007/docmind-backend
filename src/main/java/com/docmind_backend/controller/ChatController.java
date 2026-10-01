package com.docmind_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController 
@RequestMapping("/api/v1/chat")
@Tag (name = "Chat Controller", description = "APIs for chat functionality")
public class ChatController {

    @PostMapping("path")
    @Operation (summary = "Chat API", description = "Endpoint to handle chat functionality")
    public ResponseEntity<String> chat() {
        // Logic to handle chat functionality
        return ResponseEntity.ok("Chat response");
    }   
}
