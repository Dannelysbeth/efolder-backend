package com.example.efolder.model.dto.respones;

import com.example.efolder.model.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class DocumentResponse {
    private Long fileId;

    private String firstname;

    private String lastname;

    private String fileName;

    private String fileCategory;

    private long fileSize;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Warsaw")
    private LocalDateTime uploadTime;

    @Builder
    public DocumentResponse(Document document){
        this.fileId = document.getId();
        this.firstname = document.getOwner().getFirstname();
        this.lastname = document.getOwner().getLastname();
        this.fileName = document.getName();
        this.fileCategory = document.getFileCategory().toString();
        this.uploadTime = document.getUploadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.fileSize = document.getSize();

    }
}
