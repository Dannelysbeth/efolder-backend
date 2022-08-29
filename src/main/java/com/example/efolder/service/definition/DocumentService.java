package com.example.efolder.service.definition;

import com.example.efolder.model.Document;
import com.example.efolder.model.enums.FileCategory;

import java.util.List;

public interface DocumentService {
    Document saveDocument(Document document);

    Document getDocument(Long id);

    List<Document> getAllDocumentsByUsernameAndFileCategory(String username, FileCategory fileCategory);

    List<Document> getAllDocumentsByUsername(String username);

}
