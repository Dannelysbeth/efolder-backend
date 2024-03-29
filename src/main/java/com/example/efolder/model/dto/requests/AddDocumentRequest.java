package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.BusinessException;
import com.example.efolder.exceptions.NotValidDocumentExtensionException;
import com.example.efolder.model.Document;
import com.example.efolder.model.User;
import com.example.efolder.model.enums.FileCategory;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Data
public class AddDocumentRequest {
    private boolean checkDocumentExtension(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).toUpperCase().endsWith(".PDF");
    }

    public Document documentRequest(MultipartFile file, User owner, String fileCategory) {
        if (checkDocumentExtension(file)) {
            try {
                return Document.builder()
                        .id(null)
                        .name(file.getOriginalFilename())
                        .content(file.getBytes())
                        .size(file.getSize())
                        .fileCategory(FileCategory.transformStringToFileCategory(fileCategory))
                        .uploadTime(new Date(System.currentTimeMillis()))
                        .owner(owner)
                        .build();
            } catch (IOException e) {
                throw new BusinessException(400, "Invalid file!");
            }
        }
        throw new NotValidDocumentExtensionException();
    }
}
