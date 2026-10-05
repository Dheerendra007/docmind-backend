package com.docmind_backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.docmind_backend.dto.DocumentResponseDto;
import com.docmind_backend.entity.DocumentMetaData;
import com.docmind_backend.entity.DocumentStatus;
import com.docmind_backend.repository.DocumentMetaDataRepo;

@Service 
public class DocumentMetaDataService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentMetaDataService.class);
    private final DocumentMetaDataRepo documentMetaDataRepo;
    private final JdbcTemplate jdbcTemplate;
    private final DocumentParserService parseserService;
    private final DocumentIngestService ingestService;

    //Method to handle document upload and processing
    public DocumentResponseDto uploadAndDProcess(MultipartFile file) {
        String fileName = file.getOriginalFilename()!=null ? file.getOriginalFilename() : "Unknown";
        String contentType = file.getContentType()!=null ? file.getContentType() : "application/octet-stream";

        //Document meta data create
        DocumentMetaData documentMetaData = DocumentMetaData.builder()
                .fileName(fileName)
                .contentType(contentType)
                .status(DocumentStatus.PROCESSING)
                .fileSize(file.getSize())
                .build();
        // Save the document meta data to the database
        documentMetaData = documentMetaDataRepo.save(documentMetaData);

        //parse the file
        List<Document> parsedDocuments = parseserService.parseDocument(file);

        //ingest service
        int chunksIngested = ingestService.ingest(documentMetaData, parsedDocuments);

        return DocumentResponseDto.builder()
                .documentId(documentMetaData.getId())
                .fileName(documentMetaData.getFileName())
                .contentType(documentMetaData.getContentType())
                .fileSize(documentMetaData.getFileSize())
                .totalPages(documentMetaData.getTotalPages())
                .totalChunks(chunksIngested)
                .status(documentMetaData.getStatus())
                .errorMessage(documentMetaData.getErrorMessage())
                .message("Document uploaded and processed successfully.")
                .build();
        
    }

}
