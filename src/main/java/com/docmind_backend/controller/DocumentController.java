package com.docmind_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/v1/documents") 
@Tag (name = "Document Controller", description = "APIs for document management")
public class DocumentController  {

    @PostMapping("path")
    ResponseEntity<String> uploadDocument() {
        // Logic to retrieve the document based on the provided documentId
        return ResponseEntity.ok("Uploaded");
    }
    

}
