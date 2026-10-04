package com.docmind_backend.dto;

import java.util.UUID;

import com.docmind_backend.entity.DocumentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DocumentResponseDto
 */

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class DocumentResponseDto {

    private UUID documentId;
    private String documentName;
    private String documentType;
    private long documentSize;
    private DocumentStatus status;
    private Integer chunksCreated;
    private String message;

}
