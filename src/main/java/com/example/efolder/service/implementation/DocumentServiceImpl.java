package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.DocumentIsTooBigException;
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

    private boolean checkIfFileExceedsMaxSize(Document document) {
        return document.getSize() > 10000000 ? true : false;
    }

    @Override
    public Document saveDocument(Document document) {
        if (checkIfFileExceedsMaxSize(document))
            throw new DocumentIsTooBigException();
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

    @Override
    public void delete(Long id) {
        documentRepository.findById(id)
                .orElseThrow(DocumentNotFoundException::new);
        documentRepository.deleteById(id);
    }
}
