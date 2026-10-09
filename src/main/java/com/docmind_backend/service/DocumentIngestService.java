package com.docmind_backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import com.docmind_backend.config.AppProperties;
import com.docmind_backend.entity.DocumentMetaData;
import com.docmind_backend.entity.DocumentStatus;
import com.docmind_backend.repository.DocumentMetaDataRepo;

import ch.qos.logback.core.subst.Token;
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
        logger.info("Ingesting document: {id={},name={},pages={}}", documentMetaData.getId(), documentMetaData.getFileName(), documentMetaData.getFileSize());
        
        try{
            documentMetaData.setStatus(DocumentStatus.PROCESSING);
            documentMetaData.setTotalPages(parsedDocuments.size());

            //1.Text chunking using token text splitter

            TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(appProperties.getRegProperties().getChunkSize())
                .withMinChunkSizeChars(appProperties.getRegProperties().getMinChunkSizeChars())
                .withMinChunkLengthToEmbed(appProperties.getRegProperties().getMinChunkLengthToEmbed())
                .withMaxNumChunks(appProperties.getRegProperties().getMaxNumChunks())
                .withKeepSeparator(true)
                .build();
                System.out.println("TokenTextSplitter: " + tokenTextSplitter);
        }catch(Exception e){
            logger.error("Error while updating document status to PROCESSING: {id={},name={},pages={}}", documentMetaData.getId(), documentMetaData.getFileName(), documentMetaData.getFileSize(), e);
            return 0;
        }
        return parsedDocuments.size();
    }

}
