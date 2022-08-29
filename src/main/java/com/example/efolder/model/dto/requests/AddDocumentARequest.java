package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Document;
import com.example.efolder.model.UserInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class AddDocumentARequest {
    public Document documentRequest(MultipartFile file, UserInfo owner, String fileCategory){
        return Document.builder()
                .owner(owner)
                .uploadTime(new Date(System.currentTimeMillis()))
                .file(file)
                .fileCategory(fileCategory)
                .build();
    }

}
