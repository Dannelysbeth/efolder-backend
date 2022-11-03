package com.example.efolder.model;

import com.example.efolder.model.enums.FileCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    protected User owner;

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

}
