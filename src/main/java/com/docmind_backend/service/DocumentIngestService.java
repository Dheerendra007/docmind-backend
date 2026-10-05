package com.docmind_backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import com.docmind_backend.config.AppProperties;
import com.docmind_backend.entity.DocumentMetaData;
import com.docmind_backend.repository.DocumentMetaDataRepo;

import lombok.RequiredArgsConstructor;

/**
 * IngestService
 */
@Service 
@RequiredArgsConstructor 

public class DocumentIngestService {

    private final Logger logger = LoggerFactory.getLogger(DocumentIngestService.class);
    private final VectorStore vectorStore;
    private final DocumentMetaDataRepo documentMetaDataRepo;
    private final AppProperties appProperties;
    
    public int ingest(DocumentMetaData documentMetaData, List<Document> parsedDocuments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ingest'");
    }

}
