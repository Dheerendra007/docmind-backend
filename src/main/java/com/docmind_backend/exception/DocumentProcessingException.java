package com.docmind_backend.exception;

public class DocumentProcessingException extends RuntimeException {
    public DocumentProcessingException(String message) {
        super(message);
    }

    public DocumentProcessingException() {
        super("An error occurred while processing the document.");
    }

    public DocumentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
