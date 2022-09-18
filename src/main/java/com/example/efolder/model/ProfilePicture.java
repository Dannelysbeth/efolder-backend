package com.example.efolder.model;

import com.example.efolder.model.enums.FileCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
