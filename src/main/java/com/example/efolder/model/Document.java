package com.example.efolder.model;

import com.example.efolder.model.enums.FileCategory;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

import static com.example.efolder.model.enums.FileCategory.transformStringToFileCategory;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    protected UserInfo owner;

    @Setter
    @Column(name = "name")
    protected String name;

    @Setter
    @Column(name = "size")
    protected long size;

    @Setter
    @Column(name = "upload_time")
    protected Date uploadTime;

    @Setter
    @Column(name = "content")
    protected byte[] content;

    @Setter
    @Column(name = "file_category")
    @Enumerated(EnumType.STRING)
    protected FileCategory fileCategory;

    @Builder
    public Document(MultipartFile file, UserInfo owner, Date uploadTime, String fileCategory){
        this.id = null;
        this.name = file.getOriginalFilename();
        this.owner = owner;
        this.size = file.getSize();
        this.uploadTime = uploadTime;
        this.fileCategory = transformStringToFileCategory(fileCategory);
        try {
            this.content = file.getBytes();
        } catch (IOException e) {
            this.content = null;
            e.getCause();
            e.printStackTrace();

        }
    }
}
