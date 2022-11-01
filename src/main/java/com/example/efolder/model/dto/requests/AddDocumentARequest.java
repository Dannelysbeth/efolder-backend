package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.NotValidDocumentExtensionException;
import com.example.efolder.model.Document;
import com.example.efolder.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;

@Data
public class AddDocumentARequest {
    private boolean checkDocumentExtension(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).toUpperCase().endsWith(".PDF")
                || Objects.requireNonNull(file.getOriginalFilename()).toUpperCase().endsWith(".DOC")
                || Objects.requireNonNull(file.getOriginalFilename()).toUpperCase().endsWith(".DOCX")
                || Objects.requireNonNull(file.getOriginalFilename()).toUpperCase().endsWith(".ODT");
    }
    public Document documentRequest(MultipartFile file, User owner, String fileCategory){
        if(checkDocumentExtension(file))
            return Document.builder()
                    .owner(owner)
                    .uploadTime(new Date(System.currentTimeMillis()))
                    .file(file)
                    .fileCategory(fileCategory)
                    .build();
        throw new NotValidDocumentExtensionException();
    }
}
