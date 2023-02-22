package com.example.efolder.model.dto.responses;

import com.example.efolder.model.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class DocumentResponse {
    private Long id;

    private String name;

    private String category;

    private String size;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Warsaw")
    private LocalDateTime uploadTime;

    @Builder
    public DocumentResponse(Document document) {
        this.id = document.getId();
        this.name = document.getName();
        this.category = document.getFileCategory().toString();
        this.uploadTime = document.getUploadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.size = getFileSizeFromLong(document.getSize());

    }

    private String getFileSizeFromLong(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1000));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
