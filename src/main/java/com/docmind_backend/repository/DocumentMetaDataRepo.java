package com.docmind_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docmind_backend.entity.DocumentMetaData;

public interface DocumentMetaDataRepo extends JpaRepository<DocumentMetaData, UUID> {
    // Custom query methods can be defined here if needed

}
