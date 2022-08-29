package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.DocumentNotFoundException;
import com.example.efolder.model.Document;
import com.example.efolder.model.enums.FileCategory;
import com.example.efolder.repository.DocumentRepository;
import com.example.efolder.service.definition.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document getDocument(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public List<Document> getAllDocumentsByUsernameAndFileCategory(String username, FileCategory fileCategory) {
        return documentRepository.findAllByOwner_UsernameAndAndFileCategory(username, fileCategory);
    }

    @Override
    public List<Document> getAllDocumentsByUsername(String username) {
        return documentRepository.findAllByOwner_Username(username);
    }
}
