//package com.example.efolder.model;
//
//import com.example.efolder.model.enums.Gender;
//import lombok.*;
//import org.hibernate.annotations.Polymorphism;
//import org.hibernate.annotations.PolymorphismType;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.*;
//
//import static java.util.Arrays.stream;
//
//
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
////@Inheritance(strategy = InheritanceType.JOINED)
////@Polymorphism(type = PolymorphismType.EXPLICIT)
////@Builder
//@Entity
//@Table(name = "users")
//public class User /*implements UserDetails*/ {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", nullable = false)
//    protected Long id;
//
//    @Setter
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private Address address;
//
//    @Setter
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private Employment employment;
//
//    @Setter
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private ProfilePicture profilePicture;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamLeader")
//    private Collection<Team> teams = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
//    private Collection<Document> documents = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hrManager")
//    private Collection<Employment> hrPeoplePull = new ArrayList<>();
//
//    @Column(nullable = false)
//    protected String username;
//
//    @Setter
//    @Column(nullable = false)
//    protected String password;
//
//    @Setter
//    @Column(name = "firstname", nullable = false)
//    private String firstname;
//
//    @Setter
//    @Column(name = "middle_name")
//    private String middleName;
//
//    @Setter
//    @Column(name = "lastname", nullable = false)
//    private String lastname;
//
//    @Setter
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Setter
//    @Column(name = "birthdate")
//    private Date birthdate;
//
//    @Setter
//    @Column(name = "gender")
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
////    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(name="users_roles",
//            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
//            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
//    )
//    private Set<Role> roles = new HashSet<>();;
//
//    public User(String firstname, String lastname, String username, String password, String email){
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
//
//    @Builder
//    public User(String username, String password, Address address,
//                    Employment employment,
//                    ProfilePicture profilePicture,
//                    Collection<Team> teams,
//                    Collection<Employment> hrPeoplePull,
//                    Collection<Document> documents,
//                    String firstname,
//                    String middleName,
//                    String lastname,
//                    String email,
//                    Date birthdate,
//                    Gender gender) {
//        this.username = username;
//        this.password = password;
//        this.employment = employment;
//        this.address = address;
//        this.profilePicture = profilePicture;
//        this.teams = teams;
//        this.hrPeoplePull = hrPeoplePull;
//        this.documents = documents;
//        this.firstname = firstname;
//        this.middleName = middleName;
//        this.lastname = lastname;
//        this.email = email;
//        this.birthdate = birthdate;
//        this.gender = gender;
//    }
//
//    public void addRole(Role role){
//        for(Role r: this.roles){
//            if(r.getRoleName().equals(role.getRoleName()))
//               return;
//        }
//        this.roles.add(role);
//    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        Collection<GrantedAuthority> authorities = new ArrayList<>();
////        stream(roles.toArray()).forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
////        return authorities;
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return false;
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        return false;
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return false;
////    }
////
////    @Override
////    public boolean isEnabled() {
////        return false;
////    }
//
//
//}
package com.example.efolder.model;

import com.example.efolder.model.enums.Gender;
import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
//@Polymorphism(type = PolymorphismType.EXPLICIT)
//@Builder
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

    @Setter
    @Column(name = "birthdate")
    private Date birthdate;

    @Setter
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinTable(name="users_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private Set<Role> roles = new HashSet<>();

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
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public void addRole(Role role){
        for(Role r: this.roles){
            if(r.getRoleName().equals(role.getRoleName()))
                return;
        }
        this.roles.add(role);
    }


}
