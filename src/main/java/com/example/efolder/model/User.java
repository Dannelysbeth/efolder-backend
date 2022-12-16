package com.example.efolder.model;

import com.example.efolder.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    protected Long id;

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

    @Column(nullable = false)
    protected String username;

    @Setter
    @Column(nullable = false)
    protected String password;

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
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinTable(name="users_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private Collection<Role> roles = new ArrayDeque<>();

    public User(String firstname, String lastname, String username, String password, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Builder
    public User(String username, String password, Address address,
                Employment employment,
                ProfilePicture profilePicture,
                Collection<Team> teams,
                Collection<Employment> hrPeoplePull,
                Collection<Document> documents,
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
        this.documents = documents;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.email = email;
    }

    public void addRole(Role role){
        for(Role r: this.roles){
            if(r.getRoleName().equals(role.getRoleName()))
                return;
        }
        this.roles.add(role);
    }


}
