package com.docmind_backend.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.docmind_backend.dto.DocumentResponseDto;
import com.docmind_backend.service.DocumentMetaDataService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/documents") 
@Tag (name = "Document Controller", description = "APIs for document management")
@RequiredArgsConstructor 
public class DocumentController  {

    private final DocumentMetaDataService documentMetaDataService;



    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
       
        DocumentResponseDto documentResponseDto = this.documentMetaDataService.uploadAndDProcess(file);
        return ResponseEntity.status(HttpStatus.CREATED).
        body(ApiResponse<DocumentResponseDto>.builder()
                .success(true)
                .message("Document uploaded and processed successfully.")
                .data(documentResponseDto)
                .timestamp(LocalDateTime.now())
                .build());
    }

}
