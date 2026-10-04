package com.docmind_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.docmind_backend.dto.DocumentResponseDto;

@Service 
public class DocumentMetaDataService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentMetaDataService.class);

    public DocumentResponseDto uploadAndDProcess(MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadAndDProcess'");
    }

}
