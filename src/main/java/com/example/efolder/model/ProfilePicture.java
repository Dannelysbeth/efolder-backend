package com.example.efolder.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="profile_pictures")
public class ProfilePicture {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Setter
    @Column(name = "name")
    protected String name;

    @Setter
    @Column(name = "size")
    protected long size;

    @Setter
    @Column(name = "content")
    protected byte[] content;

    @Builder
    public ProfilePicture(MultipartFile file, UserInfo owner){
        this.id = null;
        this.name = file.getOriginalFilename();
        this.user = owner;
        this.size = file.getSize();
        try {
            this.content = file.getBytes();
        } catch (IOException e) {
            this.content = null;
            e.getCause();
            e.printStackTrace();

        }
    }

}