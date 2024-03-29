package com.example.efolder.model;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile_pictures")
public class ProfilePicture {
    @Setter
    @Column(name = "name")
    protected String name;
    @Setter
    @Column(name = "size")
    protected long size;
    @Setter
    @Column(name = "content")
    protected byte[] content;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

//    @Builder
//    public ProfilePicture(MultipartFile file, UserInfo owner){
//        if(!checkIfValidPicture(file))
//        this.id = null;
//        this.name = file.getOriginalFilename();
//        this.user = owner;
//        this.size = file.getSize();
//        try {
//            this.content = file.getBytes();
//        } catch (IOException e) {
//            this.content = null;
//            e.getCause();
//            e.printStackTrace();
//
//        }
//    }


}
