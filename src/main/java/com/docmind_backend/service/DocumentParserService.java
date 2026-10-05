package com.docmind_backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.docmind_backend.exception.DocumentProcessingException;

import java.io.IOException;

/**
 * ParserService
 */
@Service
public class DocumentParserService {

    private final Logger logger = LoggerFactory.getLogger(DocumentParserService.class);

    public List<Document> parseDocument(MultipartFile file) {

        String fileName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "Unknown";
        String contentType = file.getContentType() != null ? file.getContentType() : "application/octet-stream";

        logger.info("Parsing document: {} with content type: {}", fileName, contentType);

        try {
            // Use Spring AI Document API to parse the document
            Resource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return fileName;
                }
            };

            if (fileName.endsWith(".pdf") || contentType.contains("pdf")) {
                return parsePdf(resource);
            } else {
                return parseGenericFile(resource);
            }

        } catch (IOException e) {
            logger.error("Error parsing document: {}", fileName, e);
            throw new DocumentProcessingException("Error parsing document: " + fileName, e);
        } catch (Exception e) {
            logger.error("Unsupported operation while parsing document: {}", fileName, e);
            throw new DocumentProcessingException("Unsupported operation while parsing document: " + fileName, e);
        }

    }

    private List<Document> parseGenericFile(Resource resource) {
        PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
        .withPageBottomMargin(10)
        .withPageTopMargin(10)
        .build();

        PagePdfDocumentReader documentReader = new PagePdfDocumentReader(resource, config);

        return documentReader.read();
    }

    private List<Document> parsePdf(Resource resource) {
        TikaDocumentReader documentReader = new TikaDocumentReader(resource);
        return documentReader.read();
    }

}
