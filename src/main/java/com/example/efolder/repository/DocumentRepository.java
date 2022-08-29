package com.example.efolder.repository;

import com.example.efolder.model.Document;
import com.example.efolder.model.enums.FileCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByOwner_Username(String username);
    List<Document> findAllByOwner_UsernameAndAndFileCategory(String username, FileCategory fileCategory);
}
