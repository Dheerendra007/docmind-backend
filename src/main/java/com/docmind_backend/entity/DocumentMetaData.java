package com.docmind_backend.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity 
@Table(name = "document_metadata")
public class DocumentMetaData {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column (nullable = false)
    private String fileName;
    @Column (nullable = false)
    private String contentType;
    private long fileSize;

    private Integer totalPages;
    private Integer totalChunks;
    @Column (nullable = false)
    private DocumentStatus documentStatus;
    @Column (length = 1000)
    private String errorMessage;
    @Column (nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}

