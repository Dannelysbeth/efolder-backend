package com.example.efolder.model.dto.respones;

import com.example.efolder.model.Document;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentResponse {
    private Long fileId;

    private String firstname;

    private String lastname;

    private String fileName;

    private String fileCategory;

    private long fileSize;

    private Date uploadTime;

    @Builder
    public DocumentResponse(Document document){
        this.fileId = document.getId();
        this.firstname = document.getOwner().getFirstname();
        this.lastname = document.getOwner().getLastname();
        this.fileName = document.getName();
        this.fileCategory = document.getFileCategory().toString();
        this.uploadTime = document.getUploadTime();
        this.fileSize = document.getSize();

    }
}
