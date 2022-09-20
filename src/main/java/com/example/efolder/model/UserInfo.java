package com.example.efolder.model;

import com.example.efolder.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_infos")
@PrimaryKeyJoinColumn(name = "id")
public class UserInfo extends User {

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Address address;

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Employment employment;

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProfilePicture profilePicture;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamLeader")
    private Collection<Team> teams = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private Collection<Document> documents = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hrManager")
    private Collection<Employment> hrPeoplePull = new ArrayList<>();

    @Setter
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Setter
    @Column(name = "middle_name")
    private String middleName;

    @Setter
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Setter
    @Column(name = "birthdate")
    private Date birthdate;

    @Setter
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public UserInfo(String username, String password, Address address,
                    Employment employment,
                    ProfilePicture profilePicture,
                    Collection<Team> teams,
                    Collection<Employment> hrPeoplePull,
                    String firstname,
                    String middleName,
                    String lastname,
                    String email,
                    Date birthdate,
                    Gender gender) {
        this.username = username;
        this.password = password;
        this.employment = employment;
        this.address = address;
        this.profilePicture = profilePicture;
        this.teams = teams;
        this.hrPeoplePull = hrPeoplePull;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
    }

}

